package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.OfferDto;
import gp.riham_aisha.back_end.dto.OfferWithProducts;
import gp.riham_aisha.back_end.model.Offer;

import java.util.List;

public interface OfferService {
    List<OfferDto> getAllPublicOffers();
    List<OfferDto> getOffersForStore(Long storeId);
    OfferWithProducts getOfferById(Long offerId);
    OfferDto addOffer(OfferDto offerDto, Boolean isPublic);
    OfferDto updateOffer(Long id, OfferDto offerDto);
    void addProductsToOffer(Long offerId, Long ... productId);
    void removeProductsFromOffer(Long offerId, Long ... productId);
    void deleteOffer(Long offerId);
}
