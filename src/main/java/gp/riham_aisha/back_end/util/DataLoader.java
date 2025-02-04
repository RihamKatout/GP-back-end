package gp.riham_aisha.back_end.util;


import gp.riham_aisha.back_end.dto.AddReviewDto;
import gp.riham_aisha.back_end.dto.OfferDto;
import gp.riham_aisha.back_end.dto.auth.RegistrationRequest;
import gp.riham_aisha.back_end.dto.product.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.enums.AttributeType;
import gp.riham_aisha.back_end.model.product_and_configuration.Choice;
import gp.riham_aisha.back_end.model.product_and_configuration.ConfigurationAttributes;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;
import gp.riham_aisha.back_end.service.*;
import gp.riham_aisha.back_end.util.migrations.ProductsLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDB(AdminService adminService, CategoryService categoryService, AuthenticationService authenticationService,
                             StoreService storeService, ProductService productService, OfferService offerService, ReviewService reviewService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            addUsers(adminService, authenticationService);
            addStoreCategories(categoryService);
            addStores(storeService);
            addProductCategories(categoryService);
            addProducts(productService);
            addOffers(offerService);
            addReviews(reviewService);
        };
    }

    private void addUsers(AdminService adminService, AuthenticationService authenticationService) {
        String password = "pass123456";
        // add users
        authenticationService.register(new RegistrationRequest("rihamkatout", "Riham", "Katout", "rihamkatm@gmail.com", password, "0599119482"));
        authenticationService.register(new RegistrationRequest("aisha_ishtayeh", "Aisha", "Ishtayeh", "aishaishtayeh112@gmail.com", password, "0568468298"));
        authenticationService.register(new RegistrationRequest("siwar_katout", "Siwar", "Katout", "siwar@gp.com", password, "0987654321"));
        authenticationService.register(new RegistrationRequest("reem_ishtayeh", "Reem", "Ishtayeh", "reem5sh.sh@gmail.com", password, "0987764321"));
        authenticationService.register(new RegistrationRequest("samaa_yasin", "Samaa", "Yasin", "samaa@gp.com", password, "0987794321"));
        // add main admin
        adminService.addNewAdmin(1L);
        adminService.addNewAdmin(2L);
        // add support
        adminService.addNewSupport(5L);
    }

    private void addStoreCategories(CategoryService categoryService) {
        categoryService.addNewStoreCategory("General", null);
        categoryService.addNewStoreCategory("Sweets", "https://drive.google.com/thumbnail?id=1eUBDdHDLWdXbU7mTpF0Y7VvBcsnEEDL8");
        categoryService.addNewStoreCategory("Jewelry", "https://drive.google.com/thumbnail?id=1NHs5LnU9AINY08IPaVAPBO2Exb8cHqrc");
        categoryService.addNewStoreCategory("Toys", "https://drive.google.com/thumbnail?id=1e6WlsCPcCctzMkpPqU6lZxL5ZZIirTzx");
        categoryService.addNewStoreCategory("Home Decor", "https://drive.google.com/thumbnail?id=1KA8EF6JMU9ft42r6akR6DTZTgWPjUDnS");
    }

    private void addStores(StoreService storeService) {
        storeService.addNewStore(new AddStoreDto("Siwar Store", "Siwar is the most beautiful girl in the world", "https://drive.google.com/thumbnail?id=16So_Oj6UWipKH5IsFdfdkTmEe_hnQhLU", "https://drive.google.com/thumbnail?id=1hbWRAunkWE1PY1nkBRW9SvdMF9CDY02I", 4L));
        storeService.addNewStore(new AddStoreDto("Riham Store", "Home Living & Furnishings", "https://drive.google.com/thumbnail?id=1eoqtVploNwJAvSBFqkmkMwYCJGJCUvbq", "https://drive.google.com/thumbnail?id=1jeLoNjH56fe1pr0SAo8PyEE9hXALfHC2", 5L));
        storeService.addNewStore(new AddStoreDto("Sweet Touches", "They have the best cookies ever", "https://drive.google.com/thumbnail?id=1dZkmsrJjKo8k5D6pAKtrHmiJE60AGjQo", "https://drive.google.com/thumbnail?id=19rM_-AC-T-mMOYNBbI95m5q3QKsWDCrk", 2L));
    }

    private void addProductCategories(CategoryService categoryService) {
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Cake", 2L, "https://drive.google.com/thumbnail?id=1gMy5DYxIIIB5QSX0y4HTRwb-sL55INpc"));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Cookies", 2L, "https://drive.google.com/thumbnail?id=1UAqkCZeAj7rplnrs3AI3DfmJgm_D6kk7"));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Chocolate", 2L, "https://drive.google.com/thumbnail?id=1m60ogeEJRYfQUf-c5UmVA5iM6_ATMHcH"));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Macarons", 2L, "https://drive.google.com/thumbnail?id=13xEgjolFzPAZ-r34yZsGnHbPi5veN4Gh"));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Ring", 3L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Necklace", 3L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Earring", 3L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Watches", 3L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Dolls", 4L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Plush Toys", 4L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Wall Art", 5L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Vases", 5L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Rugs", 5L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Candles", 5L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Mirrors", 5L, null));
        categoryService.addNewProductCategory(new ProductCategoryDTO(null, "Clocks", 5L, null));
    }

    private void addProducts(ProductService productService) {


        // flower pot
        Product customizableFlowerPot = new Product(null, "Flower Pot",
                "Beautiful flower pot, you can customize the color, size, and material to suit your needs!",
                "https://drive.google.com/thumbnail?id=17kU1y14U9miNE2KLq3iA6R4mLthDP_RQ", 20.0, 3, 5,
                true, true, null, false, false, null, 0, null, null, null, null, null, null);
        ConfigurationAttributes potColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Black", 1.5), new Choice("Green", 2.0), new Choice("Blue", 2.5)), null);
        ConfigurationAttributes potSizeAttributes = new ConfigurationAttributes(null, "Size", AttributeType.SIZE, List.of(new Choice("Small", 0.0), new Choice("Medium", 3.0), new Choice("Large", 5.0)), null);
        ConfigurationAttributes potMaterialAttributes = new ConfigurationAttributes(null, "Material", AttributeType.OTHER, List.of(new Choice("Ceramic", 0.0), new Choice("Plastic", 1.0), new Choice("Clay", 2.0), new Choice("Metal", 3.0)), null);
        ProductConfiguration potConfigurations = new ProductConfiguration(null, "Pot Options", false, 0.0, null, List.of(potColorAttributes, potSizeAttributes, potMaterialAttributes));
        ConfigurationAttributes flowerColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Pink", 1.5), new Choice("Green", 2.0), new Choice("Yellow", 2.5)), null);
        ConfigurationAttributes flowerTypeAttributes = new ConfigurationAttributes(null, "Type", AttributeType.OTHER, List.of(new Choice("Peonies", 0.0), new Choice("Rose", 1.0), new Choice("Alstroemeria", 2.0), new Choice("Chrysanthemum", 3.0)), null);
        ProductConfiguration flowerConfigurations = new ProductConfiguration(null, "Flower Options", true, 2.0, null, List.of(flowerColorAttributes, flowerTypeAttributes));
        productService.addProduct(new ProductManagementDto(customizableFlowerPot, 2L, List.of(potConfigurations, flowerConfigurations), 12L));
        new ProductsLoader(productService).loadProducts();
    }

    private void addOffers(OfferService offerService) {
        offerService.addOffer(new OfferDto(null, true, "Summer Sale", "Get 20% off on all products",
                "https://drive.google.com/thumbnail?id=1txV0kYd63v2Kc29wi0BtdRzMYCzgGcaa", 20.0, null, null), true);
        offerService.addProductsToOffer(1L, 1L, 13L, 16L, 7L, 24L);


        offerService.addOffer(new OfferDto(null, true, "Ramadan Kareem", "25% discount on traditional and spiritual gifts",
                "https://drive.google.com/thumbnail?id=1M6jH3kk3jdGopbzvA0dtKDtlUpaGp1cC", 25.0, LocalDateTime.parse("2025-04-30T23:59:59"), 1L), false);
        offerService.addProductsToOffer(2L, 29L, 30L, 32L, 33L, 34L, 35L, 36L, 39L);

        offerService.addOffer(new OfferDto(null, true, "Eid Al-Fitr Celebration", "30% discount on Eid gift sets and sweets",
                "https://drive.google.com/thumbnail?id=1Jvc36U1E1642Zv76vOU10YwqljKMpIm2", 30.0, LocalDateTime.parse("2025-05-03T23:59:59"), null), true);
        offerService.addProductsToOffer(3L, 22L, 40L, 41L, 37L);

        offerService.addOffer(new OfferDto(null, true, "Spring Sale", "10% discount on handmade decor and crafts",
                "https://drive.google.com/thumbnail?id=1_24zHJAy-7_2pHc7K3De3yMCZoRK1rMa", 10.0, LocalDateTime.parse("2025-03-21T23:59:59"), null), true);
        offerService.addProductsToOffer(4L, 4L, 17L, 18L, 28L, 31L);
    }

    private void addReviews(ReviewService reviewService) {
        // ---------- store reviews
        reviewService.addReview(new AddReviewDto(
                "The store has a friendly staff and great customer service.",
                4, false, null, 1L));
        reviewService.addReview(new AddReviewDto(
                "The store needs better communication channels. Slow response to inquiries.",
                2, false, null, 1L));
        reviewService.addReview(new AddReviewDto(
                "The store has limited product options. Needs improvement.",
                3, false, null, 2L));
        reviewService.addReview(new AddReviewDto(
                "The store offered a great discount on my purchase. Happy customer!",
                5, false, null, 3L));
        reviewService.addReview(new AddReviewDto(
                "The store needs to work on faster shipping times.",
                2, false, null, 1L));
        reviewService.addReview(new AddReviewDto(
                "The store website is user-friendly and easy to navigate.",
                4, false, null, 2L));
        reviewService.addReview(new AddReviewDto(
                "The store staff was rude when I contacted customer support.",
                1, false, null, 3L));
        reviewService.addReview(new AddReviewDto(
                "Excellent store experience. Will definitely shop again!",
                5, false, null, 1L));
        reviewService.addReview(new AddReviewDto(
                "Fast delivery and secure packaging. Highly recommend this store!",
                5, false, null, 2L));
        reviewService.addReview(new AddReviewDto(
                "Helpful staff and quick issue resolution. Great store!",
                4, false, null, 3L));

        // ---------- product reviews
        reviewService.addReview(new AddReviewDto(
                "The product broke after a week of use. Disappointing quality.",
                1, true, 1L, null));
        reviewService.addReview(new AddReviewDto(
                "Amazing product! High quality and matches the description perfectly.",
                5, true, 2L, null));
        reviewService.addReview(new AddReviewDto(
                "The product looks good but lacks durability.",
                3, true, 3L, null));
        reviewService.addReview(new AddReviewDto(
                "This product exceeded my expectations. Fantastic value for money.",
                5, true, 1L, null));
            reviewService.addReview(new AddReviewDto(
                "Stylish and well-designed product, but expensive.",
                4, true, 1L, null));
        reviewService.addReview(new AddReviewDto(
                "High-performance product. Excellent for professional use.",
                5, true, 1L, null));
        reviewService.addReview(new AddReviewDto(
                "Received a defective product, but the return process was easy.",
                3, true, 2L, null));
        reviewService.addReview(new AddReviewDto(
                "Absolutely love this product! I’ll be purchasing more soon.",
                5, true, 2L, null));
        reviewService.addReview(new AddReviewDto(
                "Good product overall, but it arrived with minor scratches.",
                3, true, 3L, null));
        reviewService.addReview(new AddReviewDto(
                "This product is a game changer! Highly recommended.",
                5, true, 3L, null));
    }
}
