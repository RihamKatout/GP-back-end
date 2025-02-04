package gp.riham_aisha.back_end.dto;

import gp.riham_aisha.back_end.dto.product.ProductWithStoreDto;
import gp.riham_aisha.back_end.model.Offer;

import java.util.List;

public record OfferWithProducts(Offer offer, List<ProductWithStoreDto> products) {
}
