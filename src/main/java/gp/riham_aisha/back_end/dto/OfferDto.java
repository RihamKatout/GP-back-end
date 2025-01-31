package gp.riham_aisha.back_end.dto;

import gp.riham_aisha.back_end.model.Offer;
import jakarta.annotation.Nullable;

import java.sql.Timestamp;

public record OfferDto(Long id,
                       Boolean publicOffer,
                       String title,
                       String description,
                       String imageurl,
                       Double discount,
                       Timestamp endDate,
                       @Nullable
                       Long storeId) {
    public static OfferDto fromOffer(Offer offer) {
        return new OfferDto(offer.getId(), offer.getPublicOffer(), offer.getTitle(),
                offer.getDescription(), offer.getImageurl(), offer.getDiscount(),
                offer.getEndDate(), offer.getStore() == null ? null : offer.getStore().getId());
    }
}
