package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.model.Offer;

import java.util.List;

public interface OfferService {
    List<Offer> getAllPublicOffers();
    List<Offer> getOffersForStore(Long storeId);
    Offer getOfferById(Long offerId);
    Offer addOffer(Offer offer);
    Offer updateOffer(Offer offer);
    Offer addProductsToOffer(Long offerId, Long ... productId);
    void deleteOffer(Long offerId);
}
