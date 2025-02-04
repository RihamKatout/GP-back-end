package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.dto.OfferDto;
import gp.riham_aisha.back_end.dto.OfferWithProducts;
import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.dto.product.SearchProductParameters;
import gp.riham_aisha.back_end.model.Offer;
import gp.riham_aisha.back_end.model.Store;
import gp.riham_aisha.back_end.repository.OfferRepository;
import gp.riham_aisha.back_end.service.OfferService;
import gp.riham_aisha.back_end.service.ProductService;
import gp.riham_aisha.back_end.service.StoreService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final StoreService storeService;
    private final ProductService productService;

    @Override
    public List<OfferDto> getAllPublicOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(OfferDto::fromOffer).toList();
    }

    @Override
    public List<OfferDto> getOffersForStore(Long storeId) {
        List<Offer> offers = offerRepository.findAllByStoreId(storeId);
        return offers.stream().map(OfferDto::fromOffer).toList();
    }

    @Override
    public OfferWithProducts getOfferById(Long offerId) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(
                () -> new EntityNotFoundException("Offer with id " + offerId + " not found"));
        Pageable pageable = PageRequest.of(0, 100000);
        Page<ProductWithStoreDto> products = productService.searchProducts(new SearchProductParameters
                (null, null, null, null, null, null, null,
                        null, null, null, null, null, offerId), pageable);
        List<ProductWithStoreDto> finalProducts = products.stream().toList();
        return new OfferWithProducts(offer, finalProducts);
    }

    @Transactional
    @Override
    public OfferDto addOffer(OfferDto offerDto, Boolean isPublic) {
        Offer offer = new Offer(offerDto);
        offer.setPublicOffer(isPublic);
        if (Boolean.TRUE.equals(isPublic)) {
            offerRepository.save(offer);
            offerDto = OfferDto.fromOffer(offerRepository.save(offer));
            log.info("Public offer with id {} added successfully by {}", offerDto.id(), AuthUtil.getCurrentUser());
            return offerDto;
        }
        Store store = storeService.getStore(offerDto.storeId());
        AuthUtil.validateStoreOwner(store);
        offer.setStore(store);
        offerDto = OfferDto.fromOffer(offerRepository.save(offer));
        log.info("Offer with id {} added successfully by {}", offerDto.id(), AuthUtil.getCurrentUser());
        return offerDto;
    }

    @Transactional
    @Override
    public OfferDto updateOffer(Long id, OfferDto offerDto) {
        Offer offer = getOfferById(id).offer();
        if(Boolean.FALSE.equals(offerDto.publicOffer()))
            AuthUtil.validateStoreOwner(offer.getStore());
        offer.update(offerDto);
        offerDto = OfferDto.fromOffer(offerRepository.save(offer));
        log.info("Offer with id {} updated successfully by {}", offerDto.id(), AuthUtil.getCurrentUser());
        return offerDto;
    }

    @Transactional
    @Override
    public void addProductsToOffer(Long offerId, Long... productId) {
        Offer offer = getOfferById(offerId).offer();
        for (Long id : productId) {
            productService.addProductToOffer(id, offer);
        }
        log.info("Products with ids {} added to offer with id {} by {}", productId, offerId, AuthUtil.getCurrentUser());
    }

    @Transactional
    @Override
    public void removeProductsFromOffer(Long offerId, Long... productId) {
        Offer offer = getOfferById(offerId).offer();
        for (Long id : productId) {
            productService.removeProductFromOffer(id, offer);
        }
        log.info("Products with ids {} removed from offer with id {} by {}", productId, offerId, AuthUtil.getCurrentUser());
    }

    @Override
    public void deleteOffer(Long offerId) {
        getOfferById(offerId);
        offerRepository.deleteById(offerId);
        log.info("Offer with id {} deleted successfully by {}", offerId, AuthUtil.getCurrentUser());
    }
}
