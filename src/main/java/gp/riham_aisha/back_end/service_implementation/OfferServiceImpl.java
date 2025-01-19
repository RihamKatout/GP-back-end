package gp.riham_aisha.back_end.service_implementation;

import gp.riham_aisha.back_end.model.Offer;
import gp.riham_aisha.back_end.repository.OfferRepository;
import gp.riham_aisha.back_end.service.OfferService;
import gp.riham_aisha.back_end.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Override
    public List<Offer> getAllPublicOffers() {
        return offerRepository.findByPublicOffer(true);
    }

    @Override
    public List<Offer> getOffersForStore(Long storeId) {
        return offerRepository.findAllByStoreId(storeId);
    }

    @Override
    public Offer getOfferById(Long offerId) {
        return offerRepository.findById(offerId).orElseThrow(
                () -> new EntityNotFoundException("Offer with id " + offerId + " not found"));
    }

    @Override
    public Offer addOffer(Offer offer) {
        if (offer.getPublicOffer()) {
            if (AuthUtil.isCurrentUserSupport()) {
                offerRepository.save(offer);
                return offer;
            }
            throw new SecurityException("You are not authorized to add a public offer");
        }
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer addProductsToOffer(Long offerId, Long... productId) {
        return null;
    }

    @Override
    public void deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }
}
