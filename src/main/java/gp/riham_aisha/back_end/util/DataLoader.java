package gp.riham_aisha.back_end.util;

import gp.riham_aisha.back_end.dto.AddStoreDto;
import gp.riham_aisha.back_end.dto.ProductCategoryDTO;
import gp.riham_aisha.back_end.dto.RegistrationRequest;
import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.enums.AttributeType;
import gp.riham_aisha.back_end.enums.Role;
import gp.riham_aisha.back_end.model.product_and_configuration.Choice;
import gp.riham_aisha.back_end.model.product_and_configuration.ConfigurationAttributes;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;
import gp.riham_aisha.back_end.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Slf4j
@Configuration
public class DataLoader {
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
        // add main admin
        adminService.addNewAdmin(new RegistrationRequest("rihamkatout", "Riham", "Katout", "rihamkatm@gmail.com", password, "0599119482"));
        // add users
        authenticationService.register(new RegistrationRequest("siwar_katout", "Siwar", "Katout", "siwar@gp.com", password, "0987654321"), Set.of(Role.CUSTOMER));
        authenticationService.register(new RegistrationRequest("reem_ishtayeh", "Reem", "Ishtayeh", "reem@gp.com", password, "0987764321"), Set.of(Role.CUSTOMER));

    }

    private void addStoreCategories(CategoryService categoryService) {
        categoryService.addNewStoreCategory("General", null);
        categoryService.addNewStoreCategory("Sweets", "https://drive.google.com/thumbnail?id=1eUBDdHDLWdXbU7mTpF0Y7VvBcsnEEDL8");
        categoryService.addNewStoreCategory("Jewelry", "https://drive.google.com/thumbnail?id=1NHs5LnU9AINY08IPaVAPBO2Exb8cHqrc");
        categoryService.addNewStoreCategory("Toys", "https://drive.google.com/thumbnail?id=1e6WlsCPcCctzMkpPqU6lZxL5ZZIirTzx");
        categoryService.addNewStoreCategory("Home Decor", "https://drive.google.com/thumbnail?id=1KA8EF6JMU9ft42r6akR6DTZTgWPjUDnS");
    }

    private void addStores(StoreService storeService) {
        storeService.addNewStore(new AddStoreDto("Siwar Store", "Siwar is the most beautiful girl in the world", null, null, 1L, 4L));
        storeService.addNewStore(new AddStoreDto("Riham Store", "bla bla bla bla", null, null, 1L, 5L));
        storeService.addNewStore(new AddStoreDto("Sweet Touches", "They have the best cookies ever", "https://drive.google.com/thumbnail?id=1dZkmsrJjKo8k5D6pAKtrHmiJE60AGjQo", null, 1L, 2L));
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
        // cake
        Product customizableCake = new Product(null, "3d cake", "Delicious cake, you can design it, choose topping and filling as you want!", "https://drive.google.com/thumbnail?id=1M4iDO8UnNY3dfdD8RvY7256H7V8it5sB", 15.0, 0, 0, false, true, null, true, false, null, 0, null, null, null);

        ConfigurationAttributes toppingAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)), null);
        ConfigurationAttributes toppingFlavor = new ConfigurationAttributes(null, "Flavor", AttributeType.OTHER, List.of(new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Chocolate ", 2.5)), null);
        ConfigurationAttributes cakeAttributes = new ConfigurationAttributes(null, "Cake size", AttributeType.SIZE, List.of(new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)), null);
        ProductConfiguration cakeTopping = new ProductConfiguration(null, "Topping", false, 0.0, null, List.of(toppingAttributes, toppingFlavor));
        ProductConfiguration cakeFilling = new ProductConfiguration(null, "Filling", false, 0.0, null, List.of(toppingAttributes));
        ProductConfiguration cakeSize = new ProductConfiguration(null, "Size", false, 0.0, null, List.of(cakeAttributes));
        productService.addProduct(new ProductManagementDto(customizableCake, 3L, List.of(cakeTopping, cakeFilling, cakeSize), 1L));


        //Cramel Cake
        Product caramelCake = new Product(
                null,
                "Caramel Cake",
                "Delicious caramel cake, customizable with toppings and fillings of your choice!",
                "https://drive.google.com/thumbnail?id=1wQUTz2ePmjsXlTsfAitl95e201v4_ZUs",
                10.0, // Same price as requested
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for toppings and fillings
        ConfigurationAttributes toppingAttributes1 = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor1 = new ConfigurationAttributes(
                null, "Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Caramel", 2.5) // Added "Caramel" as a flavor option
                ),
                null
        );

        ConfigurationAttributes cakeAttributes1 = new ConfigurationAttributes(
                null, "Cake size",
                AttributeType.SIZE,
                List.of(
                        new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)
                ),
                null
        );

// Configurations for toppings, fillings, and sizes
        ProductConfiguration cakeTopping1 = new ProductConfiguration(
                null, "Topping", false, 0.0, null,
                List.of(toppingAttributes1, toppingFlavor1)
        );

        ProductConfiguration cakeFilling1 = new ProductConfiguration(
                null, "Filling", false, 0.0, null,
                List.of(toppingAttributes1)
        );

        ProductConfiguration cakeSize1 = new ProductConfiguration(
                null, "Size", false, 0.0, null,
                List.of(cakeAttributes1)
        );

// Add the new product
        productService.addProduct(
                new ProductManagementDto(
                        caramelCake,
                        3L,
                        List.of(cakeTopping1, cakeFilling1, cakeSize1),
                        1L
                )
        );

        //chocolate cake
        Product chocolateCake = new Product(
                null,
                "Chocolate Cake",
                "Delicious chocolate cake, customizable with toppings and fillings of your choice!",
                "https://drive.google.com/thumbnail?id=1ifr8sYbwVXtpVDLw6d2FLHgKaisvBCR_",
                10.0, // Updated price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for toppings and fillings
        ConfigurationAttributes toppingAttributes2 = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor2 = new ConfigurationAttributes(
                null, "Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Chocolate", 2.5) // Ensure spelling is consistent
                ),
                null
        );

        ConfigurationAttributes cakeAttributes2 = new ConfigurationAttributes(
                null, "Cake size",
                AttributeType.SIZE,
                List.of(
                        new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)
                ),
                null
        );

// Configurations for toppings, fillings, and sizes
        ProductConfiguration cakeTopping2 = new ProductConfiguration(
                null, "Topping", false, 0.0, null,
                List.of(toppingAttributes2, toppingFlavor2)
        );

        ProductConfiguration cakeFilling2 = new ProductConfiguration(
                null, "Filling", false, 0.0, null,
                List.of(toppingAttributes2)
        );

        ProductConfiguration cakeSize2 = new ProductConfiguration(
                null, "Size", false, 0.0, null,
                List.of(cakeAttributes2)
        );


        productService.addProduct(
                new ProductManagementDto(
                        chocolateCake,
                        3L,
                        List.of(cakeTopping2, cakeFilling2, cakeSize2),
                        1L
                )
        );

        /// Vanilla Cake
        Product vanillaCake = new Product(
                null,
                "Vanilla Cake",
                "Delicious vanilla cake, customizable with toppings and fillings of your choice!",
                "https://drive.google.com/thumbnail?id=1e86D7GYG83cKD8V4TvCsZjhsg3aXHksM",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for toppings and fillings
        ConfigurationAttributes toppingAttributes3 = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor3 = new ConfigurationAttributes(
                null, "Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Vanilla", 2.0) // Added "Vanilla" as a flavor option
                ),
                null
        );

        ConfigurationAttributes cakeAttributes3 = new ConfigurationAttributes(
                null, "Cake size",
                AttributeType.SIZE,
                List.of(
                        new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)
                ),
                null
        );

// Configurations for toppings, fillings, and sizes
        ProductConfiguration cakeTopping3 = new ProductConfiguration(
                null, "Topping", false, 0.0, null,
                List.of(toppingAttributes, toppingFlavor)
        );

        ProductConfiguration cakeFilling3 = new ProductConfiguration(
                null, "Filling", false, 0.0, null,
                List.of(toppingAttributes)
        );

        ProductConfiguration cakeSize3 = new ProductConfiguration(
                null, "Size", false, 0.0, null,
                List.of(cakeAttributes)
        );

// Add the new product
        productService.addProduct(
                new ProductManagementDto(
                        vanillaCake,
                        3L,
                        List.of(cakeTopping3, cakeFilling3, cakeSize3),
                        1L
                )
        );

     ///Strawberry Cake

        Product strawberryCake = new Product(
                null,
                "Strawberry Cake",
                "Delicious strawberry cake, customizable with toppings and fillings of your choice!",
                "https://drive.google.com/thumbnail?id=1WwNh_nY9vKiLl7gJIfoijEpO3WbHaCHN",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for toppings and fillings
        ConfigurationAttributes toppingAttributes4 = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor4 = new ConfigurationAttributes(
                null, "Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Strawberry", 2.0) // Added "Strawberry" as a flavor option
                ),
                null
        );

        ConfigurationAttributes cakeAttributes4 = new ConfigurationAttributes(
                null, "Cake size",
                AttributeType.SIZE,
                List.of(
                        new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)
                ),
                null
        );

// Configurations for toppings, fillings, and sizes
        ProductConfiguration cakeTopping4 = new ProductConfiguration(
                null, "Topping", false, 0.0, null,
                List.of(toppingAttributes4, toppingFlavor4)
        );

        ProductConfiguration cakeFilling4 = new ProductConfiguration(
                null, "Filling", false, 0.0, null,
                List.of(toppingAttributes4)
        );

        ProductConfiguration cakeSize4 = new ProductConfiguration(
                null, "Size", false, 0.0, null,
                List.of(cakeAttributes4)
        );

// Add the new product
        productService.addProduct(
                new ProductManagementDto(
                        strawberryCake,
                        3L,
                        List.of(cakeTopping4, cakeFilling4, cakeSize4),
                        1L
                )
        );
//Blueberry Cake
        Product blueberryCake = new Product(
                null,
                "Blueberry Cake",
                "Delicious blueberry cake, customizable with toppings and fillings of your choice!",
                "https://drive.google.com/thumbnail?id=1rlLWbEAKAgguWR1ipYdNxNPu1z6F3zqG",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for toppings and fillings
        ConfigurationAttributes toppingAttributes5 = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor5 = new ConfigurationAttributes(
                null, "Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Blueberry", 2.5) // Added "Blueberry" as a flavor option
                ),
                null
        );

        ConfigurationAttributes cakeAttributes5 = new ConfigurationAttributes(
                null, "Cake size",
                AttributeType.SIZE,
                List.of(
                        new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)
                ),
                null
        );

// Configurations for toppings, fillings, and sizes
        ProductConfiguration cakeTopping5 = new ProductConfiguration(
                null, "Topping", false, 0.0, null,
                List.of(toppingAttributes5, toppingFlavor5)
        );

        ProductConfiguration cakeFilling5 = new ProductConfiguration(
                null, "Filling", false, 0.0, null,
                List.of(toppingAttributes5)
        );

        ProductConfiguration cakeSize5 = new ProductConfiguration(
                null, "Size", false, 0.0, null,
                List.of(cakeAttributes5)
        );

// Add the new product
        productService.addProduct(
                new ProductManagementDto(
                        blueberryCake,
                        3L,
                        List.of(cakeTopping5, cakeFilling5, cakeSize5),
                        1L
                )
        );
//Custom strawberry cake
        Product customStrawberryCake = new Product(
                null,
                "Custom Strawberry Cake",
                "A delicious strawberry cake that you can fully customize! Choose your toppings, fillings, and size to make it your own.",
                "https://drive.google.com/thumbnail?id=1ZULduXcVOXpfnU0Fc6m6ZgwMArwhQzJo",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for customizable options
        ConfigurationAttributes toppingAttributes6 = new ConfigurationAttributes(
                null, "Topping Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Pink", 0.5), new Choice("White", 1.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor6 = new ConfigurationAttributes(
                null, "Topping Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Strawberry Frosting", 2.0), new Choice("Fresh Strawberries", 1.5), new Choice("Strawberry Syrup", 2.5)
                ),
                null
        );

        ConfigurationAttributes cakeAttributes6 = new ConfigurationAttributes(
                null, "Cake Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 4.0), new Choice("Large (L)", 7.0)
                ),
                null
        );

// Configurations for the custom cake
        ProductConfiguration cakeTopping6 = new ProductConfiguration(
                null, "Topping", true, 0.0, null,
                List.of(toppingAttributes6, toppingFlavor6)
        );

        ProductConfiguration cakeFilling6 = new ProductConfiguration(
                null, "Filling", true, 0.0, null,
                List.of(
                        new ConfigurationAttributes(
                                null, "Filling Flavor",
                                AttributeType.OTHER,
                                List.of(
                                        new Choice("Strawberry Jam", 2.0), new Choice("Vanilla Cream", 2.5), new Choice("Chocolate Ganache", 3.0)
                                ),
                                null
                        )
                )
        );

        ProductConfiguration cakeSize6 = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(cakeAttributes6)
        );

// Add the custom strawberry cake
        productService.addProduct(
                new ProductManagementDto(
                        customStrawberryCake,
                        3L,
                        List.of(cakeTopping6, cakeFilling6, cakeSize6),
                        1L
                )
        );

//custom cherry cake
        Product customCherryCake = new Product(
                null,
                "Custom Cherry Cake",
                "A delightful cherry cake that you can fully customize! Choose your toppings, fillings, and size to create your perfect dessert.",
                "https://drive.google.com/thumbnail?id=1OtZweS2S7qufl5l9839_x2K2UkWGFGE_",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for customizable options
        ConfigurationAttributes toppingAttributes7 = new ConfigurationAttributes(
                null, "Topping Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Red", 0.0), new Choice("Dark Red", 0.5), new Choice("White", 1.0)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor7 = new ConfigurationAttributes(
                null, "Topping Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Cherry Glaze", 2.0), new Choice("Fresh Cherries", 1.5), new Choice("Cherry Syrup", 2.5)
                ),
                null
        );

        ConfigurationAttributes cakeAttributes7 = new ConfigurationAttributes(
                null, "Cake Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 4.0), new Choice("Large (L)", 7.0)
                ),
                null
        );

// Configurations for the custom cherry cake
        ProductConfiguration cakeTopping7 = new ProductConfiguration(
                null, "Topping", true, 0.0, null,
                List.of(toppingAttributes7, toppingFlavor7)
        );

        ProductConfiguration cakeFilling7 = new ProductConfiguration(
                null, "Filling", true, 0.0, null,
                List.of(
                        new ConfigurationAttributes(
                                null, "Filling Flavor",
                                AttributeType.OTHER,
                                List.of(
                                        new Choice("Cherry Jam", 2.0), new Choice("Vanilla Cream", 2.5), new Choice("Chocolate Ganache", 3.0)
                                ),
                                null
                        )
                )
        );

        ProductConfiguration cakeSize7 = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(cakeAttributes7)
        );

// Add the custom cherry cake
        productService.addProduct(
                new ProductManagementDto(
                        customCherryCake,
                        3L,
                        List.of(cakeTopping7, cakeFilling7, cakeSize7),
                        1L
                )
        );

//custom lemon cake

        Product customLemonCake = new Product(
                null,
                "Custom Lemon Cake",
                "A zesty and refreshing lemon cake that you can fully customize! Add your favorite toppings, fillings, and choose the perfect size.",
                "https://drive.google.com/thumbnail?id=14o3N0M5mhhavcbRGNYmTn2O0rOE5t3_S",
                10.0, // Same price
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for customizable options
        ConfigurationAttributes toppingAttributes8 = new ConfigurationAttributes(
                null, "Topping Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("Yellow", 0.0), new Choice("White", 1.0), new Choice("Green", 0.5)
                ),
                null
        );

        ConfigurationAttributes toppingFlavor8 = new ConfigurationAttributes(
                null, "Topping Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Lemon Glaze", 2.0), new Choice("Candied Lemon Slices", 1.5), new Choice("Lemon Zest", 1.0)
                ),
                null
        );

        ConfigurationAttributes cakeAttributes8 = new ConfigurationAttributes(
                null, "Cake Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 4.0), new Choice("Large (L)", 7.0)
                ),
                null
        );

// Configurations for the custom lemon cake
        ProductConfiguration cakeTopping8 = new ProductConfiguration(
                null, "Topping", true, 0.0, null,
                List.of(toppingAttributes8, toppingFlavor8)
        );

        ProductConfiguration cakeFilling8 = new ProductConfiguration(
                null, "Filling", true, 0.0, null,
                List.of(
                        new ConfigurationAttributes(
                                null, "Filling Flavor",
                                AttributeType.OTHER,
                                List.of(
                                        new Choice("Lemon Curd", 2.5), new Choice("Vanilla Cream", 2.0), new Choice("Honey Glaze", 1.5)
                                ),
                                null
                        )
                )
        );

        ProductConfiguration cakeSize8 = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(cakeAttributes8)
        );

// Add the custom lemon cake
        productService.addProduct(
                new ProductManagementDto(
                        customLemonCake,
                        3L,
                        List.of(cakeTopping8, cakeFilling8, cakeSize8),
                        1L
                )
        );


        //Cookies

        Product customCookies = new Product(
                null,
                "Custom Cookies",
                "Delicious cookies made just the way you like them! Customize with your favorite flavors, toppings, and sizes.",
                "https://drive.google.com/thumbnail?id=1HNXDbz6bDqkyBR-WBKN-87gV00wiL0gS",
                5.0, // Set a default price for cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );


        ConfigurationAttributes cookieBaseFlavor = new ConfigurationAttributes(
                null, "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Classic Chocolate Chip", 0.0), new Choice("Peanut Butter", 0.5), new Choice("Oatmeal Raisin", 0.5), new Choice("Sugar Cookie", 0.0)
                ),
                null
        );

        ConfigurationAttributes cookieToppingOptions = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Sprinkles", 0.5), new Choice("Chocolate Chips", 0.0), new Choice("Nuts", 1.0), new Choice("Dried Fruits", 1.5)
                ),
                null
        );

        ConfigurationAttributes cookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );


        ProductConfiguration cookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(cookieBaseFlavor)
        );

        ProductConfiguration cookieToppings = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(cookieToppingOptions)
        );

        ProductConfiguration cookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(cookieSizeOptions)
        );


        productService.addProduct(
                new ProductManagementDto(
                        customCookies,
                        3L,
                        List.of(cookieBase, cookieToppings, cookieSize),
                        2L
                )
        );
//Dark Cookies

        Product darkCookies = new Product(
                null,
                "Dark Cookies",
                "Rich and decadent dark cookies with customizable flavors, toppings, and sizes to match your cravings.",
                "https://drive.google.com/thumbnail?id=1oKsdMLu-NII0fL8VCB_KiXaBRPB7PNUI",
                6.0, // Set a slightly higher price for premium dark cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for dark cookie customization
        ConfigurationAttributes darkCookieBaseFlavor = new ConfigurationAttributes(
                null, "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Dark Chocolate", 0.0), new Choice("Double Chocolate", 0.5), new Choice("Dark Cocoa & Sea Salt", 0.7), new Choice("Espresso Infused", 1.0)
                ),
                null
        );

        ConfigurationAttributes darkCookieToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Dark Chocolate Chips", 0.0), new Choice("Caramel Drizzle", 0.7), new Choice("Crushed Almonds", 1.0), new Choice("Dried Cranberries", 1.2)
                ),
                null
        );

        ConfigurationAttributes darkCookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for the dark cookies
        ProductConfiguration darkCookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(darkCookieBaseFlavor)
        );

        ProductConfiguration darkCookieToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(darkCookieToppings)
        );

        ProductConfiguration darkCookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(darkCookieSizeOptions)
        );

// Add the dark cookies
        productService.addProduct(
                new ProductManagementDto(
                        darkCookies,
                        3L,
                        List.of(darkCookieBase, darkCookieToppingsConfig, darkCookieSize),
                        2L
                )
        );

//Red velvet Cookies

        Product redVelvetCookies = new Product(
                null,
                "Red Velvet Cookies",
                "Indulge in soft and decadent red velvet cookies with customizable flavors, toppings, and sizes. Perfect for those with a sweet tooth!",
                "https://drive.google.com/thumbnail?id=1rS3GsOTgt0F7JN3pZsQRjexpF99pCFyL",
                6.0, // Price for red velvet cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for red velvet cookie customization
        ConfigurationAttributes redVelvetCookieBaseFlavor = new ConfigurationAttributes(
                null, "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Classic Red Velvet", 0.0), new Choice("Red Velvet with White Chocolate", 0.5), new Choice("Red Velvet with Cream Cheese Swirl", 1.0)
                ),
                null
        );

        ConfigurationAttributes redVelvetCookieToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Cream Cheese Frosting", 1.0), new Choice("White Chocolate Chips", 0.5), new Choice("Crushed Walnuts", 0.0), new Choice("Red Sprinkles", 0.5)
                ),
                null
        );

        ConfigurationAttributes redVelvetCookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for the red velvet cookies
        ProductConfiguration redVelvetCookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(redVelvetCookieBaseFlavor)
        );

        ProductConfiguration redVelvetCookieToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(redVelvetCookieToppings)
        );

        ProductConfiguration redVelvetCookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(redVelvetCookieSizeOptions)
        );

// Add the red velvet cookies
        productService.addProduct(
                new ProductManagementDto(
                        redVelvetCookies,
                        3L,
                        List.of(redVelvetCookieBase, redVelvetCookieToppingsConfig, redVelvetCookieSize),
                        2L
                )
        );

        //Custom White cookies

        Product customWhiteCookies = new Product(
                null,
                "Custom White Cookies",
                "Delicate and sweet white cookies that you can fully customize with your favorite flavors, toppings, and sizes.",
                "https://drive.google.com/thumbnail?id=1xyxA5eR8cnPJmsPpnAtNg-4pnLlBIXHL",
                5.0, // Price for white cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for white cookie customization
        ConfigurationAttributes whiteCookieBaseFlavor = new ConfigurationAttributes(
                null,
                "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Classic Vanilla", 0.0), new Choice("White Chocolate Macadamia", 1.0), new Choice("Coconut White", 0.5), new Choice("Lemon White", 0.7)
                ),
                null
        );

        ConfigurationAttributes whiteCookieToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("White Chocolate Chips", 0.0), new Choice("Shredded Coconut", 0.7), new Choice("Crushed Almonds", 1.0), new Choice("Sprinkles", 0.3)
                ),
                null
        );

        ConfigurationAttributes whiteCookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for the white cookies
        ProductConfiguration whiteCookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(whiteCookieBaseFlavor)
        );

        ProductConfiguration whiteCookieToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(whiteCookieToppings)
        );

        ProductConfiguration whiteCookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(whiteCookieSizeOptions)
        );

// Add the custom white cookies
        productService.addProduct(
                new ProductManagementDto(
                        customWhiteCookies,
                        3L,
                        List.of(whiteCookieBase, whiteCookieToppingsConfig, whiteCookieSize),
                        2L
                )
        );

        //custom lotus cookies

        Product customLotusCookies = new Product(
                null,
                "Custom Lotus Cookies",
                "Enjoy the rich and caramelized flavor of lotus cookies, fully customizable with your choice of flavors, toppings, and sizes.",
                "https://drive.google.com/thumbnail?id=17LNcwNRrl4zZb0ZiaoipK_05w8Abw4xe",
                5.5, // Slightly higher price for Lotus cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Lotus cookie customization
        ConfigurationAttributes lotusCookieBaseFlavor = new ConfigurationAttributes(
                null, "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Classic Lotus", 0.0), new Choice("Lotus with Caramel", 1.0), new Choice("Lotus with Chocolate Drizzle", 1.2), new Choice("Lotus with Coffee Infusion", 1.5)
                ),
                null
        );

        ConfigurationAttributes lotusCookieToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Lotus Biscuit Crumbles", 0.0), new Choice("Caramel Drizzle", 1.0), new Choice("Chocolate Chips", 0.7), new Choice("Roasted Pecans", 1.0)
                ),
                null
        );

        ConfigurationAttributes lotusCookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for the Lotus cookies
        ProductConfiguration lotusCookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(lotusCookieBaseFlavor)
        );

        ProductConfiguration lotusCookieToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(lotusCookieToppings)
        );

        ProductConfiguration lotusCookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(lotusCookieSizeOptions)
        );

// Add the custom Lotus cookies
        productService.addProduct(
                new ProductManagementDto(
                        customLotusCookies,
                        3L,
                        List.of(lotusCookieBase, lotusCookieToppingsConfig, lotusCookieSize),
                        2L
                )
        );

//custom chocolate cookies
        Product customChocolateNutsCookies = new Product(
                null,
                "Custom Chocolate Cookies",
                "Rich and chewy chocolate cookies with nuts, customizable with your choice of nuts, flavors, and sizes.",
                "https://drive.google.com/thumbnail?id=1J3TyBQLXfQ6h80gP1vMcfU4S7JZk29Yu",
                6.0, // Price for chocolate with nuts cookies
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Chocolate with Nuts cookie customization
        ConfigurationAttributes chocoNutsCookieBaseFlavor = new ConfigurationAttributes(
                null, "Base Flavor",
                AttributeType.OTHER,
                List.of(
                        new Choice("Classic Chocolate", 0.0), new Choice("Dark Chocolate", 1.0), new Choice("Milk Chocolate", 0.5), new Choice("Double Chocolate", 1.5)
                ),
                null
        );

        ConfigurationAttributes chocoNutsCookieNuts = new ConfigurationAttributes(
                null, "Nuts",
                AttributeType.OTHER,
                List.of(
                        new Choice("Almonds", 0.5), new Choice("Walnuts", 0.7), new Choice("Pecans", 1.0), new Choice("Hazelnuts", 0.0)
                ),
                null
        );

        ConfigurationAttributes chocoNutsCookieToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Chocolate Chips", 0.5), new Choice("Sea Salt", 0.3), new Choice("Caramel Drizzle", 1.0), new Choice("Shredded Coconut", 0.7)
                ),
                null
        );

        ConfigurationAttributes chocoNutsCookieSizeOptions = new ConfigurationAttributes(
                null, "Cookie Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for Chocolate with Nuts cookies
        ProductConfiguration chocoNutsCookieBase = new ProductConfiguration(
                null, "Base Flavor", true, 0.0, null,
                List.of(chocoNutsCookieBaseFlavor)
        );

        ProductConfiguration chocoNutsCookieNutsConfig = new ProductConfiguration(
                null, "Nuts", true, 0.0, null,
                List.of(chocoNutsCookieNuts)
        );

        ProductConfiguration chocoNutsCookieToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(chocoNutsCookieToppings)
        );

        ProductConfiguration chocoNutsCookieSize = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(chocoNutsCookieSizeOptions)
        );

// Add the custom Chocolate with Nuts cookies
        productService.addProduct(
                new ProductManagementDto(
                        customChocolateNutsCookies,
                        3L,
                        List.of(chocoNutsCookieBase, chocoNutsCookieNutsConfig, chocoNutsCookieToppingsConfig, chocoNutsCookieSize),
                        2L
                )
        );

//chocolate milk bar
        Product milkChocolateBar = new Product(
                null,
                "Milk Chocolate Bar",
                "Smooth and creamy milk chocolate bar with options to customize toppings, fillings, and sizes to suit your taste.",
                "https://drive.google.com/thumbnail?id=1q1O0pkj0ikXE7yNqhuYQTyA9xyLoaoPS",
                3.5, // Base price for the Milk Chocolate Bar
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Milk Chocolate Bar customization
        ConfigurationAttributes chocolateBarToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Crushed Almonds", 0.5), new Choice("Nothing", 0.0), new Choice("Sea Salt", 0.3), new Choice("Crispy Rice", 0.4)
                ),
                null
        );

        ConfigurationAttributes chocolateBarFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Peanut Butter", 1.0), new Choice("Caramel", 1.2), new Choice("Hazelnut Cream", 1.5), new Choice("Nothing", 0.0)
                ),
                null
        );

        ConfigurationAttributes chocolateBarSizeOptions = new ConfigurationAttributes(
                null, "Bar Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for Milk Chocolate Bar
        ProductConfiguration chocolateBarToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(chocolateBarToppings)
        );

        ProductConfiguration chocolateBarFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(chocolateBarFillings)
        );

        ProductConfiguration chocolateBarSizeConfig = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(chocolateBarSizeOptions)
        );

// Add the Milk Chocolate Bar
        productService.addProduct(
                new ProductManagementDto(
                        milkChocolateBar,
                        3L,
                        List.of(chocolateBarToppingsConfig, chocolateBarFillingsConfig, chocolateBarSizeConfig),
                        3L
                )
        );
// Dark Chocolate

        Product darkChocolateBar = new Product(
                null,
                "Dark Chocolate Bar",
                "Rich and indulgent dark chocolate bar with customizable options for toppings, fillings, and sizes, perfect for chocolate lovers.",
                "https://drive.google.com/thumbnail?id=1uwcypjWqij0BdBolJAdEwvw_7s7uhIfU",
                4.0, // Base price for the Dark Chocolate Bar
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Dark Chocolate Bar customization
        ConfigurationAttributes darkChocolateToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Crushed Almonds", 0.5), new Choice("Sea Salt", 0.3), new Choice("Nothing", 0.0), new Choice("Shredded Coconut", 0.4)
                ),
                null
        );

        ConfigurationAttributes darkChocolateFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Hazelnut Cream", 1.5), new Choice("Peanut Butter", 1.0), new Choice("Caramel", 1.2), new Choice("Nothing", 0.0)
                ),
                null
        );

        ConfigurationAttributes darkChocolateSizeOptions = new ConfigurationAttributes(
                null, "Bar Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)
                ),
                null
        );

// Configurations for Dark Chocolate Bar
        ProductConfiguration darkChocolateToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(darkChocolateToppings)
        );

        ProductConfiguration darkChocolateFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(darkChocolateFillings)
        );

        ProductConfiguration darkChocolateSizeConfig = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(darkChocolateSizeOptions)
        );

// Add the Dark Chocolate Bar
        productService.addProduct(
                new ProductManagementDto(
                        darkChocolateBar,
                        3L,
                        List.of(darkChocolateToppingsConfig, darkChocolateFillingsConfig, darkChocolateSizeConfig),
                        3L
                )
        );

        // Custom Dubai chocolate
        Product dubaiChocolate = new Product(
                null,
                "Dubai Chocolate",
                "Luxurious and rich chocolate inspired by the flavors of Dubai, with customizable options for toppings, fillings, and sizes.",
                "https://drive.google.com/thumbnail?id=1zkeCZiYdtuWjhoCVdrQW8zdlILGUxynO",
                5.0, // Base price for the Dubai Chocolate
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Dubai Chocolate customization
        ConfigurationAttributes dubaiChocolateToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Gold Flakes", 0.0), new Choice("Pistachios", 1.0), new Choice("Rose Petals", 0.8), new Choice("Coconut Shreds", 0.5)
                ),
                null
        );

        ConfigurationAttributes dubaiChocolateFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Date Paste", 1.5), new Choice("Caramel", 1.2), new Choice("Almond Butter", 1.8), new Choice("Pistachio ", 0.0)
                ),
                null
        );

        ConfigurationAttributes dubaiChocolateSizeOptions = new ConfigurationAttributes(
                null, "Bar Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.5), new Choice("Large (L)", 3.0)
                ),
                null
        );

// Configurations for Dubai Chocolate
        ProductConfiguration dubaiChocolateToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(dubaiChocolateToppings)
        );

        ProductConfiguration dubaiChocolateFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(dubaiChocolateFillings)
        );

        ProductConfiguration dubaiChocolateSizeConfig = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(dubaiChocolateSizeOptions)
        );

// Add the Dubai Chocolate
        productService.addProduct(
                new ProductManagementDto(
                        dubaiChocolate,
                        3L,
                        List.of(dubaiChocolateToppingsConfig, dubaiChocolateFillingsConfig, dubaiChocolateSizeConfig),
                        3L
                )
        );

// custom Nuts Chocolate
        Product customNutsChocolate = new Product(
                null,
                "Custom Nuts Chocolate",
                "A rich and creamy chocolate bar with customizable nut toppings, fillings, and sizes for a personalized nutty experience.",
                "https://drive.google.com/thumbnail?id=1wMkUsYT9rYmEuDn2hURE2J9CPhbuxYhV",
                4.5, // Base price for the Custom Nuts Chocolate
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Custom Nuts Chocolate customization
        ConfigurationAttributes nutsChocolateToppings = new ConfigurationAttributes(
                null, "Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Almonds & Hazelnuts", 0.0), new Choice("Pecans", 1.5), new Choice("Walnuts", 1.3)
                ),
                null
        );

        ConfigurationAttributes nutsChocolateFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Peanut Butter", 1.0), new Choice("Almond Butter", 1.5), new Choice("Hazelnut Cream", 1.8), new Choice("Caramel", 0.0)
                ),
                null
        );

        ConfigurationAttributes nutsChocolateSizeOptions = new ConfigurationAttributes(
                null, "Bar Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.5), new Choice("Large (L)", 2.5)
                ),
                null
        );

// Configurations for Custom Nuts Chocolate
        ProductConfiguration nutsChocolateToppingsConfig = new ProductConfiguration(
                null, "Toppings", true, 0.0, null,
                List.of(nutsChocolateToppings)
        );

        ProductConfiguration nutsChocolateFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(nutsChocolateFillings)
        );

        ProductConfiguration nutsChocolateSizeConfig = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(nutsChocolateSizeOptions)
        );

// Add the Custom Nuts Chocolate
        productService.addProduct(
                new ProductManagementDto(
                        customNutsChocolate,
                        3L,
                        List.of(nutsChocolateToppingsConfig, nutsChocolateFillingsConfig, nutsChocolateSizeConfig),
                        3L
                )
        );
//Strawberry Macaroni
        Product strawberryMacaroni = new Product(
                null,
                "Strawberry Macaroni",
                "Delicious strawberry-flavored macaroni with customizable options for shape, color, and fillings to make it uniquely yours.",
                "https://drive.google.com/thumbnail?id=1kjw3Z9jXiAJuBOWH_S-IGFn59F746d0F",
                3.0, // Base price for Strawberry Macaroni
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Strawberry Macaroni customization
        ConfigurationAttributes macaroniShapes = new ConfigurationAttributes(
                null, "Shapes",
                AttributeType.OTHER,
                List.of(
                        new Choice("Heart", 0.5), new Choice("Star", 0.7), new Choice("Round", 0.0), new Choice("Shell", 0.6)
                ),
                null
        );

        ConfigurationAttributes macaroniColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("Pink", 0.0), new Choice("Red", 0.3), new Choice("White", 0.2)
                ),
                null
        );

        ConfigurationAttributes macaroniFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Strawberry Jam", 0.0), new Choice("Cream Cheese", 1.2), new Choice("Chocolate Ganache", 1.5)

                ),
                null
        );

// Configurations for Strawberry Macaroni
        ProductConfiguration macaroniShapesConfig = new ProductConfiguration(
                null, "Shapes", true, 0.0, null,
                List.of(macaroniShapes)
        );

        ProductConfiguration macaroniColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(macaroniColors)
        );

        ProductConfiguration macaroniFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(macaroniFillings)
        );

// Add the Strawberry Macaroni
        productService.addProduct(
                new ProductManagementDto(
                        strawberryMacaroni,
                        3L,
                        List.of(macaroniShapesConfig, macaroniColorsConfig, macaroniFillingsConfig),
                        4L
                )
        );

        //lemone Macaroni

        Product lemonMacaroni = new Product(
                null,
                "Lemon Macaroni",
                "Zesty and refreshing lemon-flavored macaroni with customizable options for shape, color, and fillings to match your taste.",
                "https://drive.google.com/thumbnail?id=12Y_s7uX4ftGCGEt6wJw0MHW207BbZnJ6",
                3.5, // Base price for Lemon Macaroni
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Lemon Macaroni customization
        ConfigurationAttributes lemonMacaroniShapes = new ConfigurationAttributes(
                null, "Shapes",
                AttributeType.OTHER,
                List.of(
                        new Choice("Star", 0.5), new Choice("Round", 0.0), new Choice("Shell", 0.6), new Choice("Leaf", 0.8)
                ),
                null
        );

        ConfigurationAttributes lemonMacaroniColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("Yellow", 0.0), new Choice("Light Yellow", 0.3), new Choice("Golden", 0.5)
                ),
                null
        );

        ConfigurationAttributes lemonMacaroniFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Lemon Curd", 0.0), new Choice("Vanilla Cream", 1.5), new Choice("White Chocolate Ganache", 1.8)

                ),
                null
        );

// Configurations for Lemon Macaroni
        ProductConfiguration lemonMacaroniShapesConfig = new ProductConfiguration(
                null, "Shapes", true, 0.0, null,
                List.of(lemonMacaroniShapes)
        );

        ProductConfiguration lemonMacaroniColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(lemonMacaroniColors)
        );

        ProductConfiguration lemonMacaroniFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(lemonMacaroniFillings)
        );

// Add the Lemon Macaroni
        productService.addProduct(
                new ProductManagementDto(
                        lemonMacaroni,
                        3L,
                        List.of(lemonMacaroniShapesConfig, lemonMacaroniColorsConfig, lemonMacaroniFillingsConfig),
                        4L
                )
        );
//Chocolate Macaroni

        Product chocolateMacaroni = new Product(
                null,
                "Chocolate Macaroni",
                "Rich and indulgent chocolate-flavored macaroni with customizable options for shape, color, and fillings to make it extra special.",
                "https://drive.google.com/thumbnail?id=1xUEuiC-DnzzwlMzj_zykrFcdWR7oiE0n",
                4.0, // Base price for Chocolate Macaroni
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Chocolate Macaroni customization
        ConfigurationAttributes chocolateMacaroniShapes = new ConfigurationAttributes(
                null, "Shapes",
                AttributeType.OTHER,
                List.of(
                        new Choice("Heart", 0.5), new Choice("Round", 0.0), new Choice("Shell", 0.6), new Choice("Square", 0.8)
                ),
                null
        );

        ConfigurationAttributes chocolateMacaroniColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("Dark Brown", 0.0), new Choice("Milk Brown", 0.3), new Choice("Light Brown", 0.5)
                ),
                null
        );

        ConfigurationAttributes chocolateMacaroniFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Chocolate Ganache", 0.0), new Choice("Caramel", 1.5), new Choice("Hazelnut Cream", 1.8)

                ),
                null
        );

// Configurations for Chocolate Macaroni
        ProductConfiguration chocolateMacaroniShapesConfig = new ProductConfiguration(
                null, "Shapes", true, 0.0, null,
                List.of(chocolateMacaroniShapes)
        );

        ProductConfiguration chocolateMacaroniColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(chocolateMacaroniColors)
        );

        ProductConfiguration chocolateMacaroniFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(chocolateMacaroniFillings)
        );

// Add the Chocolate Macaroni
        productService.addProduct(
                new ProductManagementDto(
                        chocolateMacaroni,
                        3L,
                        List.of(chocolateMacaroniShapesConfig, chocolateMacaroniColorsConfig, chocolateMacaroniFillingsConfig),
                        4L
                )
        );
//custom strawberry Macaroni

        Product customHeartStrawberryMacaroni = new Product(
                null,
                "Custom Heart Strawberry Macaroni",
                "A special heart-shaped strawberry-flavored macaroni with customizable options for color and fillings, perfect for unique occasions.",
                "https://drive.google.com/thumbnail?id=17YDUlNKO-3YawpAjznQ_rSxkFdMmeU15",
                4.5, // Base price for Custom Heart Strawberry Macaroni
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Custom Heart Strawberry Macaroni customization
        ConfigurationAttributes customHeartMacaroniColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("Pink", 0.0), new Choice("Red", 0.3), new Choice("White", 0.5)
                ),
                null
        );

        ConfigurationAttributes customHeartMacaroniFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Strawberry Jam", 0.0), new Choice("Vanilla Cream", 1.2), new Choice("Chocolate Ganache", 1.5)

                ),
                null
        );

// Attributes specifically for Heart Shape (fixed shape for this product)
        ConfigurationAttributes fixedHeartShape = new ConfigurationAttributes(
                null, "Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Heart", 0.0) // No extra charge since the shape is fixed
                ),
                null
        );

// Configurations for Custom Heart Strawberry Macaroni
        ProductConfiguration customHeartMacaroniColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(customHeartMacaroniColors)
        );

        ProductConfiguration customHeartMacaroniFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(customHeartMacaroniFillings)
        );

        ProductConfiguration customHeartMacaroniShapeConfig = new ProductConfiguration(
                null, "Shape", false, 0.0, null,
                List.of(fixedHeartShape) // Fixed heart shape
        );

// Add the Custom Heart Strawberry Macaroni
        productService.addProduct(
                new ProductManagementDto(
                        customHeartStrawberryMacaroni,
                        3L,
                        List.of(customHeartMacaroniShapeConfig, customHeartMacaroniColorsConfig, customHeartMacaroniFillingsConfig),
                        4L
                )
        );

        //custom cloud macaroni

        Product customCloudMacaroni = new Product(
                null,
                "Custom Cloud Macaroni",
                "Fluffy cloud-shaped macaroni with customizable options for color and fillings, giving you a soft and airy treat that’s perfect for any occasion.",
                "https://drive.google.com/thumbnail?id=1hAHzU7Sw9ZViNJsYDcw3V1Z2Sx5pW032",
                4.0, // Base price for Custom Cloud Macaroni
                0, 0, false, true, null, true, false, null, 0, null, null, null
        );

// Attributes for Custom Cloud Macaroni customization
        ConfigurationAttributes cloudMacaroniColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("White", 0.0), new Choice("Sky Blue", 0.3), new Choice("Light Pink", 0.4)
                ),
                null
        );

        ConfigurationAttributes cloudMacaroniFillings = new ConfigurationAttributes(
                null, "Fillings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Cotton Candy", 0.0), new Choice("Vanilla Cream", 1.2), new Choice("Whipped Cream", 1.5)

                ),
                null
        );

// Attributes specifically for Cloud Shape (fixed shape for this product)
        ConfigurationAttributes fixedCloudShape = new ConfigurationAttributes(
                null, "Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Cloud", 0.0) // No extra charge since the shape is fixed
                ),
                null
        );

// Configurations for Custom Cloud Macaroni
        ProductConfiguration cloudMacaroniColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(cloudMacaroniColors)
        );

        ProductConfiguration cloudMacaroniFillingsConfig = new ProductConfiguration(
                null, "Fillings", true, 0.0, null,
                List.of(cloudMacaroniFillings)
        );

        ProductConfiguration cloudMacaroniShapeConfig = new ProductConfiguration(
                null, "Shape", false, 0.0, null,
                List.of(fixedCloudShape) // Fixed cloud shape
        );

// Add the Custom Cloud Macaroni
        productService.addProduct(
                new ProductManagementDto(
                        customCloudMacaroni,
                        3L,
                        List.of(cloudMacaroniShapeConfig, cloudMacaroniColorsConfig, cloudMacaroniFillingsConfig),
                        4L
                )
        );



        // flower pot
        Product customizableFlowerPot = new Product(null, "Flower Pot",
                "Beautiful flower pot, you can customize the color, size, and material to suit your needs!",
                "https://drive.google.com/thumbnail?id=17kU1y14U9miNE2KLq3iA6R4mLthDP_RQ", 20.0, 3, 5,
                true, true, null, false, false, null, 0, null, null, null);
        // pot
        ConfigurationAttributes potColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Black", 1.5), new Choice("Green", 2.0), new Choice("Blue", 2.5)), null);
        ConfigurationAttributes potSizeAttributes = new ConfigurationAttributes(null, "Size", AttributeType.SIZE, List.of(new Choice("Small", 0.0), new Choice("Medium", 3.0), new Choice("Large", 5.0)), null);
        ConfigurationAttributes potMaterialAttributes = new ConfigurationAttributes(null, "Material", AttributeType.OTHER, List.of(new Choice("Ceramic", 0.0), new Choice("Plastic", 1.0), new Choice("Clay", 2.0), new Choice("Metal", 3.0)), null);
        ProductConfiguration potConfigurations = new ProductConfiguration(null, "Pot Options", false, 0.0, null, List.of(potColorAttributes, potSizeAttributes, potMaterialAttributes));
        // flower
        ConfigurationAttributes flowerColorAttributes = new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Pink", 1.5), new Choice("Green", 2.0), new Choice("Yellow", 2.5)), null);
        ConfigurationAttributes flowerTypeAttributes = new ConfigurationAttributes(null, "Type", AttributeType.OTHER, List.of(new Choice("Peonies", 0.0), new Choice("Rose", 1.0), new Choice("Alstroemeria", 2.0), new Choice("Chrysanthemum", 3.0)), null);
        ProductConfiguration flowerConfigurations = new ProductConfiguration(null, "Flower Options", true, 2.0, null, List.of(flowerColorAttributes, flowerTypeAttributes));
        productService.addProduct(new ProductManagementDto(customizableFlowerPot, 2L, List.of(potConfigurations, flowerConfigurations), 12L));

//            // cookies
//            productService.addProduct(new ProductDto("Classic cookies", "The best cookies ever", 1.0, 100,
//                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
//                    true, false, null, 2L, 50, 3L, List.of("red", "brown", "#caa179"), Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));
//            productService.addProduct(new ProductDto("Classic cookies not available", "The best cookies ever", 2.0, 100,
//                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
//                    false, false, null, 2L, 120, 3L, null, Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));
//            productService.addProduct(new ProductDto("Classic cookies with 3d model customizable", "The best cookies ever", 3.0, 100,
//                    "https://drive.google.com/thumbnail?id=1LtKw1BnmKyf6oG-sabBHydIIewVJmHcM",
//                    true, true, "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L", 2L, 120, 3L, null, Map.of(Size.S, 1.0, Size.M, 1.5, Size.L, 2.0)));

        // chocolate
//            productService.addProduct(new ProductDto("Vanilla Chocolate", "Creamy vanilla chocolate", 5.0, 200,
//                    "https://drive.google.com/thumbnail?id=1BK2xFWIPilz8qoY5OXvyiI2j0pYv3d9L",
//                    true, false, null, 3L, 100, 3L, List.of("white"), Map.of(Size.S, 5.0, Size.M, 7.0, Size.L, 10.0)));
//
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
