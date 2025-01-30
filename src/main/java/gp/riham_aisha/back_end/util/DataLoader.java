package gp.riham_aisha.back_end.util;

import gp.riham_aisha.back_end.dto.store.AddStoreDto;
import gp.riham_aisha.back_end.dto.product.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.auth.RegistrationRequest;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.enums.AttributeType;
import gp.riham_aisha.back_end.model.product_and_configuration.Choice;
import gp.riham_aisha.back_end.model.product_and_configuration.ConfigurationAttributes;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;
import gp.riham_aisha.back_end.service.*;
import gp.riham_aisha.back_end.util.migrations.ProductsLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Configuration
public class DataLoader {
    @Autowired
    private DataSource dataSource;

    @Bean
    CommandLineRunner initDB(AdminService adminService, CategoryService categoryService, AuthenticationService authenticationService, StoreService storeService, ProductService productService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            addUsers(adminService, authenticationService);
            addStoreCategories(categoryService);
            addStores(storeService);
            addProductCategories(categoryService);
            addProducts(productService);
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
        storeService.addNewStore(new AddStoreDto("Siwar Store", "Siwar is the most beautiful girl in the world", null, null, 4L));
        storeService.addNewStore(new AddStoreDto("Riham Store", "bla bla bla bla", null, null, 5L));
        storeService.addNewStore(new AddStoreDto("Sweet Touches", "They have the best cookies ever", "https://drive.google.com/thumbnail?id=1dZkmsrJjKo8k5D6pAKtrHmiJE60AGjQo", null, 2L));
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
                true, true, null, false, false, null, 0, null, null, null, null);
        ConfigurationAttributes potColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Black", 1.5), new Choice("Green", 2.0), new Choice("Blue", 2.5)), null);
        ConfigurationAttributes potSizeAttributes = new ConfigurationAttributes(null, "Size", AttributeType.SIZE, List.of(new Choice("Small", 0.0), new Choice("Medium", 3.0), new Choice("Large", 5.0)), null);
        ConfigurationAttributes potMaterialAttributes = new ConfigurationAttributes(null, "Material", AttributeType.OTHER, List.of(new Choice("Ceramic", 0.0), new Choice("Plastic", 1.0), new Choice("Clay", 2.0), new Choice("Metal", 3.0)), null);
        ProductConfiguration potConfigurations = new ProductConfiguration(null, "Pot Options", false, 0.0, null, List.of(potColorAttributes, potSizeAttributes, potMaterialAttributes));
        ConfigurationAttributes flowerColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Pink", 1.5), new Choice("Green", 2.0), new Choice("Yellow", 2.5)), null);
        ConfigurationAttributes flowerTypeAttributes = new ConfigurationAttributes(null, "Type", AttributeType.OTHER, List.of(new Choice("Peonies", 0.0), new Choice("Rose", 1.0), new Choice("Alstroemeria", 2.0), new Choice("Chrysanthemum", 3.0)), null);
        ProductConfiguration flowerConfigurations = new ProductConfiguration(null, "Flower Options", true, 2.0, null, List.of(flowerColorAttributes, flowerTypeAttributes));
        productService.addProduct(new ProductManagementDto(customizableFlowerPot, 2L, List.of(potConfigurations, flowerConfigurations), 12L));
        new ProductsLoader(productService).loadProducts();

//            // dolls
//            productService.addProduct(new ProductDto("Doll", "Beautiful doll", 10.0, 150,
//                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
//                    true, true, null, 9L, 100, 1L, List.of("pink", "blue", "yellow", "green"), null));
//
//            productService.addProduct(new ProductDto("Plush Toy", "Beautiful plush toy", 6.0, 50,
//                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
//                    true, false, null, 10L, 60, 1L, List.of("pink"), Map.of(Size.S, 6.0, Size.M, 8.0, Size.L, 10.0)));
//
//            // home decor
//            productService.addProduct(new ProductDto("Wall Art", "Beautiful art", 20.0, 50,
//                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
//                    true, true, null, 11L, 50, 2L, List.of("pink", "blue", "yellow", "green", "brown"), null));
//
//            productService.addProduct(new ProductDto("Vase", "Beautiful pink vase", 20.0, 50,
//                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
//                    true, true, null, 12L, 75, 2L, List.of("pink"), null));
    }
}
