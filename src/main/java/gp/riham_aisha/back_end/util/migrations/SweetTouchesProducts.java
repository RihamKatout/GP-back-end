package gp.riham_aisha.back_end.util.migrations;

import gp.riham_aisha.back_end.dto.product.ProductManagementDto;
import gp.riham_aisha.back_end.enums.AttributeType;
import gp.riham_aisha.back_end.model.product_and_configuration.Choice;
import gp.riham_aisha.back_end.model.product_and_configuration.ConfigurationAttributes;
import gp.riham_aisha.back_end.model.product_and_configuration.Product;
import gp.riham_aisha.back_end.model.product_and_configuration.ProductConfiguration;
import gp.riham_aisha.back_end.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SweetTouchesProducts {
    private final ProductService productService;

    public void loadProducts() {
        cake();
        cookies();
        chocolate();
        macaroni();
    }

    //------------------- Cake -------------------
    private ConfigurationAttributes getCakeAttribute(int i) {
        if (i == 0)
            return new ConfigurationAttributes(null, "Color", AttributeType.COLOR, List.of(new Choice("Red", 0.0), new Choice("Blue", 1.0), new Choice("Green", 12.0)), null);
        else if (i == 1)
            return new ConfigurationAttributes(null, "Flavor", AttributeType.OTHER, List.of(new Choice("Frosting", 2.0), new Choice("Fruit", 1.0), new Choice("Nuts", 12.0), new Choice("Chocolate", 2.5)), null);
        return new ConfigurationAttributes(null, "Cake size", AttributeType.SIZE, List.of(new Choice("S", 0.0), new Choice("M", 4.0), new Choice("L", 7.0)), null);
    }

    private ProductConfiguration getCakeConfiguration(int i) {
        if (i == 0)
            return new ProductConfiguration(null, "Topping", false, 0.0, null, List.of(getCakeAttribute(0), getCakeAttribute(1)));
        else if (i == 1)
            return new ProductConfiguration(null, "Filling", false, 0.0, null, List.of(getCakeAttribute(0)));
        return new ProductConfiguration(null, "Size", false, 0.0, null, List.of(getCakeAttribute(2)));
    }

    private void cake() {
        // 3d cake
        Product customizableCake = new Product(null, "3d cake", "Delicious cake, you can design it, choose topping and filling as you want!", "https://drive.google.com/thumbnail?id=1M4iDO8UnNY3dfdD8RvY7256H7V8it5sB", 15.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(customizableCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Caramel Cake
        Product caramelCake = new Product(null, "Caramel Cake", "Delicious caramel cake, customizable with toppings and fillings of your choice!", "https://drive.google.com/thumbnail?id=1wQUTz2ePmjsXlTsfAitl95e201v4_ZUs", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(caramelCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Chocolate Cake
        Product chocolateCake = new Product(null, "Chocolate Cake", "Delicious chocolate cake, customizable with toppings and fillings of your choice!", "https://drive.google.com/thumbnail?id=1ifr8sYbwVXtpVDLw6d2FLHgKaisvBCR_", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(chocolateCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Vanilla Cake
        Product vanillaCake = new Product(null, "Vanilla Cake", "Delicious vanilla cake, customizable with toppings and fillings of your choice!", "https://drive.google.com/thumbnail?id=1e86D7GYG83cKD8V4TvCsZjhsg3aXHksM", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(vanillaCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Strawberry Cake
        Product strawberryCake = new Product(null, "Strawberry Cake", "Delicious strawberry cake, customizable with toppings and fillings of your choice!", "https://drive.google.com/thumbnail?id=1WwNh_nY9vKiLl7gJIfoijEpO3WbHaCHN", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(strawberryCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Blueberry Cake
        Product blueberryCake = new Product(null, "Blueberry Cake", "Delicious blueberry cake, customizable with toppings and fillings of your choice!", "https://drive.google.com/thumbnail?id=1rlLWbEAKAgguWR1ipYdNxNPu1z6F3zqG", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(blueberryCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Custom strawberry cake
        Product customStrawberryCake = new Product(null, "Custom Strawberry Cake", "A delicious strawberry cake that you can fully customize! Choose your toppings, fillings, and size to make it your own.", "https://drive.google.com/thumbnail?id=1ZULduXcVOXpfnU0Fc6m6ZgwMArwhQzJo", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(customStrawberryCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Custom cherry cake
        Product customCherryCake = new Product(null, "Custom Cherry Cake", "A delightful cherry cake that you can fully customize! Choose your toppings, fillings, and size to create your perfect dessert.", "https://drive.google.com/thumbnail?id=1OtZweS2S7qufl5l9839_x2K2UkWGFGE_", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(customCherryCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));

        // Custom lemon cake
        Product customLemonCake = new Product(null, "Custom Lemon Cake", "A zesty and refreshing lemon cake that you can fully customize! Add your favorite toppings, fillings, and choose the perfect size.", "https://drive.google.com/thumbnail?id=14o3N0M5mhhavcbRGNYmTn2O0rOE5t3_S", 10.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        productService.addProduct(new ProductManagementDto(customLemonCake, 3L, List.of(getCakeConfiguration(0), getCakeConfiguration(1), getCakeConfiguration(2)), 1L));
    }

    //------------------- Cookies -------------------
    private ProductConfiguration getCookiesSizeConfig() {
        ConfigurationAttributes cookieSizeOptions = new ConfigurationAttributes(null, "Cookie Size", AttributeType.SIZE, List.of(new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)), null);
        return new ProductConfiguration(null, "Size", true, 0.0, null, List.of(cookieSizeOptions));
    }

    private void cookies() {
        // classic cookies
        Product customCookies = new Product(null, "Custom Cookies", "Delicious cookies made just the way you like them! Customize with your favorite flavors, toppings, and sizes.", "https://drive.google.com/thumbnail?id=1HNXDbz6bDqkyBR-WBKN-87gV00wiL0gS", 5.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes cookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Classic Chocolate Chip", 0.0), new Choice("Peanut Butter", 0.5), new Choice("Oatmeal Raisin", 0.5), new Choice("Sugar Cookie", 0.0)), null);
        ConfigurationAttributes cookieToppingOptions = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Sprinkles", 0.5), new Choice("Chocolate Chips", 0.0), new Choice("Nuts", 1.0), new Choice("Dried Fruits", 1.5)), null);
        ProductConfiguration cookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(cookieBaseFlavor));
        ProductConfiguration cookieToppings = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(cookieToppingOptions));
        productService.addProduct(new ProductManagementDto(customCookies, 3L, List.of(cookieBase, cookieToppings, getCookiesSizeConfig()), 2L));

        //Dark Cookies
        Product darkCookies = new Product(null, "Dark Cookies", "Rich and decadent dark cookies with customizable flavors, toppings, and sizes to match your cravings.", "https://drive.google.com/thumbnail?id=1oKsdMLu-NII0fL8VCB_KiXaBRPB7PNUI", 6.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes darkCookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Dark Chocolate", 0.0), new Choice("Double Chocolate", 0.5), new Choice("Dark Cocoa & Sea Salt", 0.7), new Choice("Espresso Infused", 1.0)), null);
        ConfigurationAttributes darkCookieToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Dark Chocolate Chips", 0.0), new Choice("Caramel Drizzle", 0.7), new Choice("Crushed Almonds", 1.0), new Choice("Dried Cranberries", 1.2)), null);
        ProductConfiguration darkCookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(darkCookieBaseFlavor));
        ProductConfiguration darkCookieToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(darkCookieToppings));
        productService.addProduct(new ProductManagementDto(darkCookies, 3L, List.of(darkCookieBase, darkCookieToppingsConfig, getCookiesSizeConfig()), 2L));

        //Red velvet Cookies
        Product redVelvetCookies = new Product(null, "Red Velvet Cookies", "Indulge in soft and decadent red velvet cookies with customizable flavors, toppings, and sizes. Perfect for those with a sweet tooth!", "https://drive.google.com/thumbnail?id=1rS3GsOTgt0F7JN3pZsQRjexpF99pCFyL", 6.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes redVelvetCookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Classic Red Velvet", 0.0), new Choice("Red Velvet with White Chocolate", 0.5), new Choice("Red Velvet with Cream Cheese Swirl", 1.0)), null);
        ConfigurationAttributes redVelvetCookieToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Cream Cheese Frosting", 1.0), new Choice("White Chocolate Chips", 0.5), new Choice("Crushed Walnuts", 0.0), new Choice("Red Sprinkles", 0.5)), null);
        ProductConfiguration redVelvetCookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(redVelvetCookieBaseFlavor));
        ProductConfiguration redVelvetCookieToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(redVelvetCookieToppings));
        productService.addProduct(new ProductManagementDto(redVelvetCookies, 3L, List.of(redVelvetCookieBase, redVelvetCookieToppingsConfig, getCookiesSizeConfig()), 2L));

        //Custom White cookies
        Product customWhiteCookies = new Product(null, "Custom White Cookies", "Delicate and sweet white cookies that you can fully customize with your favorite flavors, toppings, and sizes.", "https://drive.google.com/thumbnail?id=1xyxA5eR8cnPJmsPpnAtNg-4pnLlBIXHL", 5.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes whiteCookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Classic Vanilla", 0.0), new Choice("White Chocolate Macadamia", 1.0), new Choice("Coconut White", 0.5), new Choice("Lemon White", 0.7)), null);
        ConfigurationAttributes whiteCookieToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("White Chocolate Chips", 0.0), new Choice("Shredded Coconut", 0.7), new Choice("Crushed Almonds", 1.0), new Choice("Sprinkles", 0.3)), null);
        ProductConfiguration whiteCookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(whiteCookieBaseFlavor));
        ProductConfiguration whiteCookieToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(whiteCookieToppings));
        productService.addProduct(new ProductManagementDto(customWhiteCookies, 3L, List.of(whiteCookieBase, whiteCookieToppingsConfig, getCookiesSizeConfig()), 2L));

        //custom lotus cookies
        Product customLotusCookies = new Product(null, "Custom Lotus Cookies", "Enjoy the rich and caramelized flavor of lotus cookies, fully customizable with your choice of flavors, toppings, and sizes.", "https://drive.google.com/thumbnail?id=17LNcwNRrl4zZb0ZiaoipK_05w8Abw4xe", 5.5, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes lotusCookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Classic Lotus", 0.0), new Choice("Lotus with Caramel", 1.0), new Choice("Lotus with Chocolate Drizzle", 1.2), new Choice("Lotus with Coffee Infusion", 1.5)), null);
        ConfigurationAttributes lotusCookieToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Lotus Biscuit Crumbles", 0.0), new Choice("Caramel Drizzle", 1.0), new Choice("Chocolate Chips", 0.7), new Choice("Roasted Pecans", 1.0)), null);
        ProductConfiguration lotusCookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(lotusCookieBaseFlavor));
        ProductConfiguration lotusCookieToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(lotusCookieToppings));
        productService.addProduct(new ProductManagementDto(customLotusCookies, 3L, List.of(lotusCookieBase, lotusCookieToppingsConfig, getCookiesSizeConfig()), 2L));

        //custom chocolate cookies
        Product customChocolateNutsCookies = new Product(null, "Custom Chocolate Cookies", "Rich and chewy chocolate cookies with nuts, customizable with your choice of nuts, flavors, and sizes.", "https://drive.google.com/thumbnail?id=1J3TyBQLXfQ6h80gP1vMcfU4S7JZk29Yu", 6.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes chocoNutsCookieBaseFlavor = new ConfigurationAttributes(null, "Base Flavor", AttributeType.OTHER, List.of(new Choice("Classic Chocolate", 0.0), new Choice("Dark Chocolate", 1.0), new Choice("Milk Chocolate", 0.5), new Choice("Double Chocolate", 1.5)), null);
        ConfigurationAttributes chocoNutsCookieNuts = new ConfigurationAttributes(null, "Nuts", AttributeType.OTHER, List.of(new Choice("Almonds", 0.5), new Choice("Walnuts", 0.7), new Choice("Pecans", 1.0), new Choice("Hazelnuts", 0.0)), null);
        ConfigurationAttributes chocoNutsCookieToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Chocolate Chips", 0.5), new Choice("Sea Salt", 0.3), new Choice("Caramel Drizzle", 1.0), new Choice("Shredded Coconut", 0.7)), null);
        ProductConfiguration chocoNutsCookieBase = new ProductConfiguration(null, "Base Flavor", true, 0.0, null, List.of(chocoNutsCookieBaseFlavor));
        ProductConfiguration chocoNutsCookieNutsConfig = new ProductConfiguration(null, "Nuts", true, 0.0, null, List.of(chocoNutsCookieNuts));
        ProductConfiguration chocoNutsCookieToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(chocoNutsCookieToppings));
        productService.addProduct(new ProductManagementDto(customChocolateNutsCookies, 3L, List.of(chocoNutsCookieBase, chocoNutsCookieNutsConfig, chocoNutsCookieToppingsConfig, getCookiesSizeConfig()), 2L));
    }

    //------------------- Cookies -------------------
    private ProductConfiguration getChocolateSize() {
        ConfigurationAttributes chocolateBarSizeOptions = new ConfigurationAttributes(null, "Bar Size", AttributeType.SIZE, List.of(new Choice("Small (S)", 0.0), new Choice("Medium (M)", 1.0), new Choice("Large (L)", 2.0)), null);
        return new ProductConfiguration(null, "Size", true, 0.0, null, List.of(chocolateBarSizeOptions));
    }

    private void chocolate() {
        //chocolate milk bar
        Product milkChocolateBar = new Product(null, "Milk Chocolate Bar", "Smooth and creamy milk chocolate bar with options to customize toppings, fillings, and sizes to suit your taste.", "https://drive.google.com/thumbnail?id=1q1O0pkj0ikXE7yNqhuYQTyA9xyLoaoPS", 3.5, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes chocolateBarToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Crushed Almonds", 0.5), new Choice("Nothing", 0.0), new Choice("Sea Salt", 0.3), new Choice("Crispy Rice", 0.4)), null);
        ConfigurationAttributes chocolateBarFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Peanut Butter", 1.0), new Choice("Caramel", 1.2), new Choice("Hazelnut Cream", 1.5), new Choice("Nothing", 0.0)), null);
        ProductConfiguration chocolateBarToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(chocolateBarToppings));
        ProductConfiguration chocolateBarFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(chocolateBarFillings));
        productService.addProduct(new ProductManagementDto(milkChocolateBar, 3L, List.of(chocolateBarToppingsConfig, chocolateBarFillingsConfig, getChocolateSize()), 3L));

        // Dark Chocolate
        Product darkChocolateBar = new Product(null, "Dark Chocolate Bar", "Rich and indulgent dark chocolate bar with customizable options for toppings, fillings, and sizes, perfect for chocolate lovers.", "https://drive.google.com/thumbnail?id=1uwcypjWqij0BdBolJAdEwvw_7s7uhIfU", 4.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes darkChocolateToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Crushed Almonds", 0.5), new Choice("Sea Salt", 0.3), new Choice("Nothing", 0.0), new Choice("Shredded Coconut", 0.4)), null);
        ConfigurationAttributes darkChocolateFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Hazelnut Cream", 1.5), new Choice("Peanut Butter", 1.0), new Choice("Caramel", 1.2), new Choice("Nothing", 0.0)), null);
        ProductConfiguration darkChocolateToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(darkChocolateToppings));
        ProductConfiguration darkChocolateFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(darkChocolateFillings));
        productService.addProduct(new ProductManagementDto(darkChocolateBar, 3L, List.of(darkChocolateToppingsConfig, darkChocolateFillingsConfig, getChocolateSize()), 3L));

        // Custom Dubai chocolate
        Product dubaiChocolate = new Product(null, "Dubai Chocolate", "Luxurious and rich chocolate inspired by the flavors of Dubai, with customizable options for toppings, fillings, and sizes.", "https://drive.google.com/thumbnail?id=1zkeCZiYdtuWjhoCVdrQW8zdlILGUxynO", 5.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes dubaiChocolateToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Gold Flakes", 0.0), new Choice("Pistachios", 1.0), new Choice("Rose Petals", 0.8), new Choice("Coconut Shreds", 0.5)), null);
        ConfigurationAttributes dubaiChocolateFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Date Paste", 1.5), new Choice("Caramel", 1.2), new Choice("Almond Butter", 1.8), new Choice("Pistachio ", 0.0)), null);
        ProductConfiguration dubaiChocolateToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(dubaiChocolateToppings));
        ProductConfiguration dubaiChocolateFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(dubaiChocolateFillings));
        productService.addProduct(new ProductManagementDto(dubaiChocolate, 3L, List.of(dubaiChocolateToppingsConfig, dubaiChocolateFillingsConfig, getChocolateSize()), 3L));

        // custom Nuts Chocolate
        Product customNutsChocolate = new Product(null, "Custom Nuts Chocolate", "A rich and creamy chocolate bar with customizable nut toppings, fillings, and sizes for a personalized nutty experience.", "https://drive.google.com/thumbnail?id=1wMkUsYT9rYmEuDn2hURE2J9CPhbuxYhV", 4.5, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes nutsChocolateToppings = new ConfigurationAttributes(null, "Toppings", AttributeType.OTHER, List.of(new Choice("Almonds & Hazelnuts", 0.0), new Choice("Pecans", 1.5), new Choice("Walnuts", 1.3)), null);
        ConfigurationAttributes nutsChocolateFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Peanut Butter", 1.0), new Choice("Almond Butter", 1.5), new Choice("Hazelnut Cream", 1.8), new Choice("Caramel", 0.0)), null);
        ProductConfiguration nutsChocolateToppingsConfig = new ProductConfiguration(null, "Toppings", true, 0.0, null, List.of(nutsChocolateToppings));
        ProductConfiguration nutsChocolateFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(nutsChocolateFillings));
        productService.addProduct(new ProductManagementDto(customNutsChocolate, 3L, List.of(nutsChocolateToppingsConfig, nutsChocolateFillingsConfig, getChocolateSize()), 3L));
    }

    //------------------- Macaroni -------------------
    private void macaroni() {
        //Strawberry Macaroni
        Product strawberryMacaroni = new Product(null, "Strawberry Macaroni", "Delicious strawberry-flavored macaroni with customizable options for shape, color, and fillings to make it uniquely yours.", "https://drive.google.com/thumbnail?id=1kjw3Z9jXiAJuBOWH_S-IGFn59F746d0F", 3.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes macaroniShapes = new ConfigurationAttributes(null, "Shapes", AttributeType.OTHER, List.of(new Choice("Heart", 0.5), new Choice("Star", 0.7), new Choice("Round", 0.0), new Choice("Shell", 0.6)), null);
        ConfigurationAttributes macaroniColors = new ConfigurationAttributes(null, "Colors", AttributeType.COLOR, List.of(new Choice("Pink", 0.0), new Choice("Red", 0.3), new Choice("White", 0.2)), null);
        ConfigurationAttributes macaroniFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Strawberry Jam", 0.0), new Choice("Cream Cheese", 1.2), new Choice("Chocolate Ganache", 1.5)), null);
        ProductConfiguration macaroniShapesConfig = new ProductConfiguration(null, "Shapes", true, 0.0, null, List.of(macaroniShapes));
        ProductConfiguration macaroniColorsConfig = new ProductConfiguration(null, "Colors", true, 0.0, null, List.of(macaroniColors));
        ProductConfiguration macaroniFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(macaroniFillings));
        productService.addProduct(new ProductManagementDto(strawberryMacaroni, 3L, List.of(macaroniShapesConfig, macaroniColorsConfig, macaroniFillingsConfig), 4L));

        //lemon Macaroni
        Product lemonMacaroni = new Product(null, "Lemon Macaroni", "Zesty and refreshing lemon-flavored macaroni with customizable options for shape, color, and fillings to match your taste.", "https://drive.google.com/thumbnail?id=12Y_s7uX4ftGCGEt6wJw0MHW207BbZnJ6", 3.5, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes lemonMacaroniShapes = new ConfigurationAttributes(null, "Shapes", AttributeType.OTHER, List.of(new Choice("Star", 0.5), new Choice("Round", 0.0), new Choice("Shell", 0.6), new Choice("Leaf", 0.8)), null);
        ConfigurationAttributes lemonMacaroniColors = new ConfigurationAttributes(null, "Colors", AttributeType.COLOR, List.of(new Choice("Yellow", 0.0), new Choice("Light Yellow", 0.3), new Choice("Golden", 0.5)), null);
        ConfigurationAttributes lemonMacaroniFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Lemon Curd", 0.0), new Choice("Vanilla Cream", 1.5), new Choice("White Chocolate Ganache", 1.8)), null);
        ProductConfiguration lemonMacaroniShapesConfig = new ProductConfiguration(null, "Shapes", true, 0.0, null, List.of(lemonMacaroniShapes));
        ProductConfiguration lemonMacaroniColorsConfig = new ProductConfiguration(null, "Colors", true, 0.0, null, List.of(lemonMacaroniColors));
        ProductConfiguration lemonMacaroniFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(lemonMacaroniFillings));
        productService.addProduct(new ProductManagementDto(lemonMacaroni, 3L, List.of(lemonMacaroniShapesConfig, lemonMacaroniColorsConfig, lemonMacaroniFillingsConfig), 4L));

        //Chocolate Macaroni
        Product chocolateMacaroni = new Product(null, "Chocolate Macaroni", "Rich and indulgent chocolate-flavored macaroni with customizable options for shape, color, and fillings to make it extra special.", "https://drive.google.com/thumbnail?id=1xUEuiC-DnzzwlMzj_zykrFcdWR7oiE0n", 4.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes chocolateMacaroniShapes = new ConfigurationAttributes(null, "Shapes", AttributeType.OTHER, List.of(new Choice("Heart", 0.5), new Choice("Round", 0.0), new Choice("Shell", 0.6), new Choice("Square", 0.8)), null);
        ConfigurationAttributes chocolateMacaroniColors = new ConfigurationAttributes(null, "Colors", AttributeType.COLOR, List.of(new Choice("Dark Brown", 0.0), new Choice("Milk Brown", 0.3), new Choice("Light Brown", 0.5)), null);
        ConfigurationAttributes chocolateMacaroniFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Chocolate Ganache", 0.0), new Choice("Caramel", 1.5), new Choice("Hazelnut Cream", 1.8)), null);
        ProductConfiguration chocolateMacaroniShapesConfig = new ProductConfiguration(null, "Shapes", true, 0.0, null, List.of(chocolateMacaroniShapes));
        ProductConfiguration chocolateMacaroniColorsConfig = new ProductConfiguration(null, "Colors", true, 0.0, null, List.of(chocolateMacaroniColors));
        ProductConfiguration chocolateMacaroniFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(chocolateMacaroniFillings));
        productService.addProduct(new ProductManagementDto(chocolateMacaroni, 3L, List.of(chocolateMacaroniShapesConfig, chocolateMacaroniColorsConfig, chocolateMacaroniFillingsConfig), 4L));

        //custom strawberry Macaroni
        Product customHeartStrawberryMacaroni = new Product(null, "Custom Heart Strawberry Macaroni", "A special heart-shaped strawberry-flavored macaroni with customizable options for color and fillings, perfect for unique occasions.", "https://drive.google.com/thumbnail?id=17YDUlNKO-3YawpAjznQ_rSxkFdMmeU15", 4.5, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes customHeartMacaroniColors = new ConfigurationAttributes(null, "Colors", AttributeType.COLOR, List.of(new Choice("Pink", 0.0), new Choice("Red", 0.3), new Choice("White", 0.5)), null);
        ConfigurationAttributes customHeartMacaroniFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Strawberry Jam", 0.0), new Choice("Vanilla Cream", 1.2), new Choice("Chocolate Ganache", 1.5)), null);
        ConfigurationAttributes fixedHeartShape = new ConfigurationAttributes(null, "Shape", AttributeType.OTHER, List.of(new Choice("Heart", 0.0)), null);
        ProductConfiguration customHeartMacaroniColorsConfig = new ProductConfiguration(null, "Colors", true, 0.0, null, List.of(customHeartMacaroniColors));
        ProductConfiguration customHeartMacaroniFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(customHeartMacaroniFillings));
        ProductConfiguration customHeartMacaroniShapeConfig = new ProductConfiguration(null, "Shape", false, 0.0, null, List.of(fixedHeartShape));
        productService.addProduct(new ProductManagementDto(customHeartStrawberryMacaroni, 3L, List.of(customHeartMacaroniShapeConfig, customHeartMacaroniColorsConfig, customHeartMacaroniFillingsConfig), 4L));

        //custom cloud macaroni
        Product customCloudMacaroni = new Product(null, "Custom Cloud Macaroni", "Fluffy cloud-shaped macaroni with customizable options for color and fillings, giving you a soft and airy treat that’s perfect for any occasion.", "https://drive.google.com/thumbnail?id=1hAHzU7Sw9ZViNJsYDcw3V1Z2Sx5pW032", 4.0, 0, 0, false, true, null, true, false, null, 0, null, null, null, null);
        ConfigurationAttributes cloudMacaroniColors = new ConfigurationAttributes(null, "Colors", AttributeType.COLOR, List.of(new Choice("White", 0.0), new Choice("Sky Blue", 0.3), new Choice("Light Pink", 0.4)), null);
        ConfigurationAttributes cloudMacaroniFillings = new ConfigurationAttributes(null, "Fillings", AttributeType.OTHER, List.of(new Choice("Cotton Candy", 0.0), new Choice("Vanilla Cream", 1.2), new Choice("Whipped Cream", 1.5)), null);
        ConfigurationAttributes fixedCloudShape = new ConfigurationAttributes(null, "Shape", AttributeType.OTHER, List.of(new Choice("Cloud", 0.0)), null);
        ProductConfiguration cloudMacaroniColorsConfig = new ProductConfiguration(null, "Colors", true, 0.0, null, List.of(cloudMacaroniColors));
        ProductConfiguration cloudMacaroniFillingsConfig = new ProductConfiguration(null, "Fillings", true, 0.0, null, List.of(cloudMacaroniFillings));
        ProductConfiguration cloudMacaroniShapeConfig = new ProductConfiguration(null, "Shape", false, 0.0, null, List.of(fixedCloudShape));
        productService.addProduct(new ProductManagementDto(customCloudMacaroni, 3L, List.of(cloudMacaroniShapeConfig, cloudMacaroniColorsConfig, cloudMacaroniFillingsConfig), 4L));
    }
}
