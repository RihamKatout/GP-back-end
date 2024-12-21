package gp.riham_aisha.back_end;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.dto.StoreDto;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@Slf4j
@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner initDB(AdminService adminService, CategoryService storeCategoryService, AuthenticationService authenticationService,
                             StoreService storeService, ProductService productService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            String password = "pass123456";
            // add main admin
            adminService.addNewAdmin(new RegistrationRequest("rihamkatout", "Riham", "Katout",
                    "rihamkatm@gmail.com", password, "0599119482"));
            // add store categories
            storeCategoryService.addNewStoreCategory("General", null);
            storeCategoryService.addNewStoreCategory("Sweets", "https://drive.google.com/thumbnail?id=1eUBDdHDLWdXbU7mTpF0Y7VvBcsnEEDL8");
            storeCategoryService.addNewStoreCategory("Jewelry", "https://drive.google.com/thumbnail?id=1NHs5LnU9AINY08IPaVAPBO2Exb8cHqrc");
            storeCategoryService.addNewStoreCategory("Toys", "https://drive.google.com/thumbnail?id=1e6WlsCPcCctzMkpPqU6lZxL5ZZIirTzx");
            storeCategoryService.addNewStoreCategory("Home Decor", "https://drive.google.com/thumbnail?id=1KA8EF6JMU9ft42r6akR6DTZTgWPjUDnS");

            // add users
            authenticationService.register(new RegistrationRequest("siwar_katout", "Siwar", "Katout",
                    "siwar@gp.com", password, "0987654321"), Set.of(Role.CUSTOMER));
            authenticationService.register(new RegistrationRequest("reem_ishtayeh", "Reem", "Ishtayeh",
                    "reem@gp.com", password, "0987764321"), Set.of(Role.CUSTOMER));

            // add stores
            storeService.addNewStore(new StoreDto("Siwar Store", "Siwar is the most beautiful girl in the world", null, null, 2L, 4L));
            storeService.addNewStore(new StoreDto("Riham Store", "bla bla bla bla", null, null, 1L, 5L));
            storeService.addNewStore(new StoreDto("Sweet Touches", "They have the best cookies ever", null, null, 2L, 4L));

            // add product categories
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Cake", 2L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Cookies", 2L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Chocolate", 2L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Macarons", 2L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Ring", 3L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Necklace", 3L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Earring", 3L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Watches", 3L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Dolls", 4L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Plush Toys", 4L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Wall Art", 5L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Vases", 5L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Rugs", 5L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Candles", 5L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Mirrors", 5L));
            storeCategoryService.addNewProductCategory(new ProductCategoryDTO(null, "Clocks", 5L));

            // add products
            // cake
            productService.addProduct(new ProductDto("Chocolate Cake", "Delicious chocolate cake", 15.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 1L, 3L));

            // cookies
            productService.addProduct(new ProductDto("Classic cookies", "The best cookies ever", 1.0, 100,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, false, null, 2L, 3L));

            // chocolate
            productService.addProduct(new ProductDto("Vanilla Chocolate", "Creamy vanilla chocolate", 5.0, 200,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, false, null, 3L, 3L));

            // dolls
            productService.addProduct(new ProductDto("Doll", "Beautiful doll", 10.0, 150,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 9L, 1L));

            productService.addProduct(new ProductDto("Plush Toy", "Beautiful plush toy", 6.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, false, null, 10L, 1L));

            // home decor
            productService.addProduct(new ProductDto("Wall Art", "Beautiful art", 20.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 11L, 2L));

            productService.addProduct(new ProductDto("Vase", "Beautiful pink vase", 20.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 12L, 2L));

        };
    }
}
