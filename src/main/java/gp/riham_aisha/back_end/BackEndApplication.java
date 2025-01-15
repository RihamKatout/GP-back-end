package gp.riham_aisha.back_end;

import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.ProductDto;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.dto.StoreDto;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.enums.Size;
import gp.riham_aisha.back_end.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class BackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner initDB(AdminService adminService, CategoryService categoryService, AuthenticationService authenticationService,
                             StoreService storeService, ProductService productService) {
        return args -> {
            log.info("---------- The application has started on port 1218 ----------");
            String password = "pass123456";
            // add main admin
            adminService.addNewAdmin(new RegistrationRequest("rihamkatout", "Riham", "Katout",
                    "rihamkatm@gmail.com", password, "0599119482"));
            // add store categories
            categoryService.addNewStoreCategory("General", null);
            categoryService.addNewStoreCategory("Sweets", "https://drive.google.com/thumbnail?id=1eUBDdHDLWdXbU7mTpF0Y7VvBcsnEEDL8");
            categoryService.addNewStoreCategory("Jewelry", "https://drive.google.com/thumbnail?id=1NHs5LnU9AINY08IPaVAPBO2Exb8cHqrc");
            categoryService.addNewStoreCategory("Toys", "https://drive.google.com/thumbnail?id=1e6WlsCPcCctzMkpPqU6lZxL5ZZIirTzx");
            categoryService.addNewStoreCategory("Home Decor", "https://drive.google.com/thumbnail?id=1KA8EF6JMU9ft42r6akR6DTZTgWPjUDnS");

            // add users
            authenticationService.register(new RegistrationRequest("siwar_katout", "Siwar", "Katout",
                    "siwar@gp.com", password, "0987654321"), Set.of(Role.CUSTOMER));
            authenticationService.register(new RegistrationRequest("reem_ishtayeh", "Reem", "Ishtayeh",
                    "reem@gp.com", password, "0987764321"), Set.of(Role.CUSTOMER));

            // add stores
            storeService.addNewStore(new StoreDto("Siwar Store", "Siwar is the most beautiful girl in the world", null, null, 1L, 4L));
            storeService.addNewStore(new StoreDto("Riham Store", "bla bla bla bla", null, null, 1L, 5L));
            storeService.addNewStore(new StoreDto("Sweet Touches", "They have the best cookies ever", "https://drive.google.com/thumbnail?id=1dZkmsrJjKo8k5D6pAKtrHmiJE60AGjQo", null, 1L, 2L));

            // add product categories
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

            // add products
            // cake
            productService.addProduct(new ProductDto("Customizable Chocolate Cake", "Delicious chocolate cake", 15.0, 50,
                    "https://drive.google.com/thumbnail?id=1M4iDO8UnNY3dfdD8RvY7256H7V8it5sB",
                    true, true, null, 1L, 3L, null, Map.of(Size.S, 15.0, Size.M, 20.0, Size.L, 25.0)));

            // cookies
            productService.addProduct(new ProductDto("Classic cookies", "The best cookies ever", 1.0, 100,
                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
                    true, false, null, 2L, 3L, List.of("red", "brown", "#caa179"), Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));
            productService.addProduct(new ProductDto("Classic cookies not available", "The best cookies ever", 2.0, 100,
                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
                    false, false, null, 2L, 3L, null, Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));
            productService.addProduct(new ProductDto("Classic cookies with 3d model customizable", "The best cookies ever", 3.0, 100,
                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
                    true, true, "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L", 2L, 3L, null, Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));

            // chocolate
            productService.addProduct(new ProductDto("Vanilla Chocolate", "Creamy vanilla chocolate", 5.0, 200,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, false, null, 3L, 3L, List.of("white"), Map.of(Size.S, 5.0, Size.M, 7.0, Size.L, 10.0)));

            // dolls
            productService.addProduct(new ProductDto("Doll", "Beautiful doll", 10.0, 150,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 9L, 1L, List.of("pink", "blue", "yellow", "green"), null));

            productService.addProduct(new ProductDto("Plush Toy", "Beautiful plush toy", 6.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, false, null, 10L, 1L, List.of("pink"), Map.of(Size.S, 6.0, Size.M, 8.0, Size.L, 10.0)));

            // home decor
            productService.addProduct(new ProductDto("Wall Art", "Beautiful art", 20.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 11L, 2L, List.of("pink", "blue", "yellow", "green", "brown"), null));

            productService.addProduct(new ProductDto("Vase", "Beautiful pink vase", 20.0, 50,
                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
                    true, true, null, 12L, 2L, List.of("pink"), null));

        };
    }
}
