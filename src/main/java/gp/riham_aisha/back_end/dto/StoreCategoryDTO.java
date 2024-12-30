package gp.riham_aisha.back_end.dto;

import gp.riham_aisha.back_end.model.ProductCategory;

import java.util.Set;

public record StoreCategoryDTO(Long id, String name, String imageurl, Set<ProductCategory> productCategories) {
}
