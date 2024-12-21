package gp.riham_aisha.back_end.service.specification;

import gp.riham_aisha.back_end.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    private ProductSpecification() {
    }

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? null :
                criteriaBuilder.equal(root.get("productCategory").get("id"), categoryId);
    }

    public static Specification<Product> hasStore(Long storeId) {
        return (root, query, criteriaBuilder) -> storeId == null ? null :
                criteriaBuilder.equal(root.get("store").get("id"), storeId);
    }

    public static Specification<Product> hasStoreCategory(Long storeCategoryId) {
        return (root, query, criteriaBuilder) -> storeCategoryId == null ? null :
                criteriaBuilder.equal(root.get("store").get("storeCategory").get("id"), storeCategoryId);
    }

    public static Specification<Product> isAvailable(Boolean isAvailable) {
        return (root, query, criteriaBuilder) -> isAvailable == null ? null :
                criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    public static Specification<Product> hasMinPrice(Double price) {
        return (root, query, criteriaBuilder) -> price == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasMaxPrice(Double price) {
        return (root, query, criteriaBuilder) -> price == null ? null :
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> hasMinRating(Double rating) {
        return (root, query, criteriaBuilder) -> rating == null ? null :
                criteriaBuilder.greaterThanOrEqualTo(root.get("rating"), rating);
    }

    public static Specification<Product> hasId(Long id) {
        return (root, query, criteriaBuilder) -> id == null ? null :
                criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Product> hasKeyWord(String keyWord) {
        return (root, query, criteriaBuilder) -> keyWord == null ? null :
                criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyWord.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + keyWord.toLowerCase() + "%")
                );
    }
}
