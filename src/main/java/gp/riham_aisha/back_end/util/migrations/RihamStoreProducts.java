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
public class RihamStoreProducts {
    private final ProductService productService;

    public void loadProducts() {
        clocks();
        vases();
        rugs();
        mirrors();
        candles();
        wallArt();
    }

    //------------------- clocks -------------------

    private void clocks() {
        //--------------White Clock-----------------
        Product whiteClockWall = new Product(
                null,
                "White Clock Wall",
                "Elegant and modern white wall clock with customizable options for color and size to perfectly match your space.",
                "https://drive.google.com/thumbnail?id=1AP8RBjk8icLw8z9XrY9q7iM27J8yErn7",
                15.0, // Base price for White Clock Wall
                1, 3, false, true, null, false, true, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes clockColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0),
                        new Choice("#87CEEB", 0.0),
                        new Choice("#FFC0CB", 0.0),
                        new Choice("#FFD700", 0.0),
                        new Choice("#C0C0C0", 0.0),
                        new Choice("#A52A2A", 0.0)
                ),
                null
        );

        ConfigurationAttributes clockSizes = new ConfigurationAttributes(
                null, "Size (cm)",
                AttributeType.SIZE,
                List.of(
                        new Choice("30 cm", 0.0),
                        new Choice("40 cm", 5.0),
                        new Choice("50 cm", 10.0)
                ),
                null
        );

        ProductConfiguration clockColorsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(clockColors)
        );

        ProductConfiguration clockSizesConfig = new ProductConfiguration(
                null, "Size (cm)", true, 0.0, null,
                List.of(clockSizes)
        );

        productService.addProduct(
                new ProductManagementDto(
                        whiteClockWall,
                        2L,
                        List.of(clockColorsConfig, clockSizesConfig),
                        16L
                )
        );

        //---------------Dark Gray Clock -------------

        Product darkGrayClockWall = new Product(
                null,
                "Dark Gray Clock Wall",
                "Modern and stylish dark gray wall clock with customizable color and size options to complement any space.",
                "https://drive.google.com/thumbnail?id=1L4-x_bmgXZM_86re9Z8mPnqE45g7jwlz",
                15.0, // Base price for Dark Gray Clock Wall
                1, 3, false, true, null, false, true, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes availableClockColors = new ConfigurationAttributes(
                null, "Colors",
                AttributeType.COLOR,
                List.of(
                        new Choice("#2F4F4F", 0.0), // Dark Slate Gray
                        new Choice("#696969", 0.0), // Dim Gray
                        new Choice("#808080", 0.0), // Gray
                        new Choice("#A9A9A9", 0.0), // Dark Gray
                        new Choice("#D3D3D3", 0.0), // Light Gray
                        new Choice("#000000", 0.0)  // Black
                ),
                null
        );

        ConfigurationAttributes availableClockSizes = new ConfigurationAttributes(
                null, "Size (cm)",
                AttributeType.SIZE,
                List.of(
                        new Choice("30 cm", 0.0),
                        new Choice("40 cm", 0.0),
                        new Choice("50 cm", 0.0)
                ),
                null
        );

        ProductConfiguration colorOptionsConfig = new ProductConfiguration(
                null, "Colors", true, 0.0, null,
                List.of(availableClockColors)
        );

        ProductConfiguration sizeOptionsConfig = new ProductConfiguration(
                null, "Size (cm)", true, 0.0, null,
                List.of(availableClockSizes)
        );

        productService.addProduct(
                new ProductManagementDto(
                        darkGrayClockWall,
                        2L,
                        List.of(colorOptionsConfig, sizeOptionsConfig),
                        16L
                )
        );

        //-------------Resin Custom Clock------------

        Product resinCustomClock = new Product(
                null,
                "Resin Custom Clock",
                "Elegant and artistic resin wall clock with customizable base and secondary colors, along with decorative toppings like glitter and jewels.",
                "https://drive.google.com/thumbnail?id=14tcKmpKOb-oXFB5vvqjL1bhkcREz0tD7",
                20.0, // Base price for Resin Custom Clock
                1, 3, false, true, null, false, false, null, 0, null, null, null, null, null, null
        );


        ConfigurationAttributes baseColorOptions = new ConfigurationAttributes(
                null, "Base Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#000000", 0.0)  // Black
                ),
                null
        );


        ConfigurationAttributes secondaryColorOptions = new ConfigurationAttributes(
                null, "Secondary Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFD700", 5.0), // Gold
                        new Choice("#C0C0C0", 5.0)  // Silver
                ),
                null
        );


        ConfigurationAttributes decorationToppings = new ConfigurationAttributes(
                null, "Decoration Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Glitter", 1.5),
                        new Choice("Small Jewels", 2.0),
                        new Choice("Pearl Accents", 3.0),
                        new Choice("Gold Flakes", 2.5),
                        new Choice("None", 0.0)
                ),
                null
        );

        ProductConfiguration baseColorConfig = new ProductConfiguration(
                null, "Color", true, 0.0, null,
                List.of(baseColorOptions, secondaryColorOptions)
        );

        ProductConfiguration decorationConfig = new ProductConfiguration(
                null, "Decoration Toppings", true, 0.0, null,
                List.of(decorationToppings)
        );

        productService.addProduct(
                new ProductManagementDto(
                        resinCustomClock,
                        2L,
                        List.of(baseColorConfig, decorationConfig),
                        16L
                )
        );

        //------------- White & Blue Resin Custom Clock--------------

        Product whiteBlueResinClock = new Product(
                null,
                "Resin Custom Clock",
                "Elegant and artistic resin wall clock with customizable base and secondary colors, along with decorative toppings like glitter and jewels.",
                "https://drive.google.com/thumbnail?id=10Uxo4E5fLijP3qUmE9R_p1W8nxbDVPUc",
                20.0, // Base price for White & Blue Resin Custom Clock
                1, 3, false, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes baseColorOptions2 = new ConfigurationAttributes(
                null, "Base Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#000000", 0.0)  // Black
                ),
                null
        );

        ConfigurationAttributes secondaryColorOptions2 = new ConfigurationAttributes(
                null, "Secondary Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#1E90FF", 0.0), // Dodger Blue
                        new Choice("#87CEEB", 0.0), // Sky Blue
                        new Choice("#4682B4", 0.0)  // Steel Blue
                ),
                null
        );

        ConfigurationAttributes decorationToppings2 = new ConfigurationAttributes(
                null, "Decoration Toppings",
                AttributeType.OTHER,
                List.of(
                        new Choice("Glitter", 1.5),
                        new Choice("Small Jewels", 2.0),
                        new Choice("Pearl Accents", 3.0),
                        new Choice("Silver Flakes", 2.5),
                        new Choice("None", 0.0)
                ),
                null
        );

        ProductConfiguration baseColorConfig2 = new ProductConfiguration(
                null, "Color", true, 0.0, null,
                List.of(baseColorOptions2, secondaryColorOptions2)
        );

        ProductConfiguration decorationConfig2 = new ProductConfiguration(
                null, "Decoration Toppings", true, 0.0, null,
                List.of(decorationToppings2)
        );

        productService.addProduct(
                new ProductManagementDto(
                        whiteBlueResinClock,
                        2L,
                        List.of(baseColorConfig2, decorationConfig2),
                        16L
                )
        );


    }

    //------------------- vases -------------------

    private void vases() {

        //-----------------whiteVase---------------
        Product whiteVase = new Product(
                null,
                "White Vase",
                "Elegant and minimalistic white vase with customizable color, size, material, and flower options to perfectly match your decor.",
                "https://drive.google.com/thumbnail?id=1nesuNF1G6MkPEF3j2jHmiNGHUc0W0CLe",
                25.0, // Base price for White Vase
                3, 5, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes vaseColorAttributes = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#F5F5DC", 0.0), // Beige
                        new Choice("#C0C0C0", 0.0), // Silver
                        new Choice("#D2B48C", 0.0)  // Tan
                ),
                null
        );

        ConfigurationAttributes vaseSizeAttributes = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small", 0.0),
                        new Choice("Medium", 3.0),
                        new Choice("Large", 5.0)
                ),
                null
        );

        ConfigurationAttributes vaseMaterialAttributes = new ConfigurationAttributes(
                null, "Material",
                AttributeType.OTHER,
                List.of(
                        new Choice("Ceramic", 0.0),
                        new Choice("Glass", 1.0),
                        new Choice("Porcelain", 2.0),
                        new Choice("Metal", 3.0)
                ),
                null
        );

        ProductConfiguration vaseConfigurations = new ProductConfiguration(
                null, "Vase Options", false, 0.0, null,
                List.of(vaseColorAttributes, vaseSizeAttributes, vaseMaterialAttributes)
        );

        ConfigurationAttributes flowerColorAttributes = new ConfigurationAttributes(
                null, "Flower Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 1.0), // White
                        new Choice("#FFC0CB", 1.5), // Pink
                        new Choice("#FFD700", 2.0), // Yellow
                        new Choice("#FF0000", 2.5)  // Red
                ),
                null
        );

        ConfigurationAttributes flowerTypeAttributes = new ConfigurationAttributes(
                null, "Flower Type",
                AttributeType.OTHER,
                List.of(
                        new Choice("Peonies", 0.0),
                        new Choice("Rose", 1.0),
                        new Choice("Alstroemeria", 2.0),
                        new Choice("Chrysanthemum", 3.0)
                ),
                null
        );

        ProductConfiguration flowerConfigurations = new ProductConfiguration(
                null, "Flower Options", true, 2.0, null,
                List.of(flowerColorAttributes, flowerTypeAttributes)
        );

        productService.addProduct(
                new ProductManagementDto(
                        whiteVase,
                        2L,
                        List.of(vaseConfigurations, flowerConfigurations),
                        12L
                )
        );

        //----------------green Vase ----------------

        Product greenVase = new Product(
                null,
                "Green Vase",
                "Modern and stylish green vase with customizable size, material, and flower options to perfectly match your space.",
                "https://drive.google.com/thumbnail?id=1haIR3sv8YUYJSWGNndQid0-km5xq0ZNh", // Replace with actual image URL
                28.0, // Base price for Green Vase
                3, 5, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes vaseSizeAttributes1 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 3.0),
                        new Choice("Large (35cm)", 5.0)
                ),
                null
        );

        ConfigurationAttributes vaseMaterialAttributes1 = new ConfigurationAttributes(
                null, "Material",
                AttributeType.OTHER,
                List.of(
                        new Choice("Ceramic", 0.0),
                        new Choice("Clay", 1.5),
                        new Choice("Glass", 2.0),
                        new Choice("Resin", 2.5)
                ),
                null
        );

        ProductConfiguration vaseConfigurations1 = new ProductConfiguration(
                null, "Vase Options", false, 0.0, null,
                List.of(vaseSizeAttributes1, vaseMaterialAttributes1)
        );

        ConfigurationAttributes flowerColorAttributes1 = new ConfigurationAttributes(
                null, "Flower Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#F5DEB3", 1.5), // Wheat
                        new Choice("#C19A6B", 2.0), // Light Brown
                        new Choice("#8B4513", 2.5)  // Dark Brown
                ),
                null
        );


        ConfigurationAttributes flowerTypeAttributes1 = new ConfigurationAttributes(
                null, "Flower Type",
                AttributeType.OTHER,
                List.of(
                        new Choice("Dried Wheat", 0.0),
                        new Choice("Pampas Grass", 1.5),
                        new Choice("Lavender", 2.0),
                        new Choice("Eucalyptus", 2.5)
                ),
                null
        );


        ProductConfiguration flowerConfigurations1 = new ProductConfiguration(
                null, "Flower Options", true, 2.0, null,
                List.of(flowerColorAttributes1, flowerTypeAttributes1)
        );

        productService.addProduct(
                new ProductManagementDto(
                        greenVase,
                        2L,
                        List.of(vaseConfigurations1, flowerConfigurations1),
                        12L
                )
        );
        //----------------Green & White Vase-------------
        Product greenWhiteVase = new Product(
                null,
                "Green & White Vase",
                "Elegant and modern green and white vase with a smooth artistic finish. Customize the size, material, and optional flower arrangements.",
                "https://drive.google.com/thumbnail?id=1hQMKM9QNIEdW_Fms-ZqivRlQhNQjtKQ4", // Replace with actual image URL
                30.0, // Base price for Green & White Vase
                3, 5, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );


        ConfigurationAttributes vaseSizeAttributes2 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 3.0),
                        new Choice("Large (35cm)", 5.0)
                ),
                null
        );

        ConfigurationAttributes vaseMaterialAttributes2 = new ConfigurationAttributes(
                null, "Material",
                AttributeType.OTHER,
                List.of(
                        new Choice("Ceramic", 0.0),
                        new Choice("Porcelain", 2.0),
                        new Choice("Glass", 2.5)
                ),
                null
        );

// Product Configuration for Vase
        ProductConfiguration vaseConfigurations2 = new ProductConfiguration(
                null, "Vase Options", false, 0.0, null,
                List.of(vaseSizeAttributes2, vaseMaterialAttributes2)
        );


        ConfigurationAttributes flowerColorAttributes2 = new ConfigurationAttributes(
                null, "Flower Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 1.0), // White
                        new Choice("#F5DEB3", 1.5), // Wheat
                        new Choice("#C19A6B", 2.0), // Light Brown
                        new Choice("#8B4513", 2.5)  // Dark Brown
                ),
                null
        );


        ConfigurationAttributes flowerTypeAttributes2 = new ConfigurationAttributes(
                null, "Flower Type",
                AttributeType.OTHER,
                List.of(
                        new Choice("Dried Pampas Grass", 1.0),
                        new Choice("Orchid", 1.5),
                        new Choice("Bamboo Stalks", 2.0),
                        new Choice("Hydrangea", 2.5)
                ),
                null
        );


        ProductConfiguration flowerConfigurations2 = new ProductConfiguration(
                null, "Flower Options", true, 2.0, null,
                List.of(flowerColorAttributes2, flowerTypeAttributes2)
        );


        productService.addProduct(
                new ProductManagementDto(
                        greenWhiteVase,
                        2L,
                        List.of(vaseConfigurations2, flowerConfigurations2),
                        12L
                )
        );

        //-----------------Black vase---------------

        Product blackVase = new Product(
                null,
                "Black Vase",
                "Minimalist and elegant black vase with a matte finish. Customizable in different colors, sizes, and flower arrangements.",
                "https://drive.google.com/thumbnail?id=1Fmkwz0pZkvHNnFIMrEjC5EbjilPkneJa", // Replace with actual image URL
                28.0, // Base price for Black Vase
                3, 5, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes vaseColorAttributes3 = new ConfigurationAttributes(
                null, "Vase Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#000000", 0.0), // Black (Default)
                        new Choice("#4B4B4B", 0.0), // Charcoal Gray
                        new Choice("#8B5A2B", 0.0), // Walnut Brown
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#B5A642", 0.0)  // Antique Gold
                ),
                null
        );

        ConfigurationAttributes vaseSizeAttributes3 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 3.0),
                        new Choice("Large (35cm)", 5.0)
                ),
                null
        );

        ConfigurationAttributes vaseMaterialAttributes3 = new ConfigurationAttributes(
                null, "Material",
                AttributeType.OTHER,
                List.of(
                        new Choice("Ceramic", 0.0),
                        new Choice("Glass", 2.0),
                        new Choice("Metal", 2.5),
                        new Choice("Porcelain", 3.0)
                ),
                null
        );

        ProductConfiguration vaseConfigurations3 = new ProductConfiguration(
                null, "Vase Options", false, 0.0, null,
                List.of(vaseColorAttributes3, vaseSizeAttributes3, vaseMaterialAttributes3)
        );

        ConfigurationAttributes flowerColorAttributes3 = new ConfigurationAttributes(
                null, "Flower Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#F5DEB3", 1.5), // Wheat
                        new Choice("#C19A6B", 2.0), // Light Brown
                        new Choice("#8B4513", 2.5)  // Dark Brown
                ),
                null
        );

        ConfigurationAttributes flowerTypeAttributes3 = new ConfigurationAttributes(
                null, "Flower Type",
                AttributeType.OTHER,
                List.of(
                        new Choice("Dried Pampas Grass", 0.0),
                        new Choice("White Pom Flowers", 1.5),
                        new Choice("Orchid", 2.0),
                        new Choice("Hydrangea", 2.5)
                ),
                null
        );

        ProductConfiguration flowerConfigurations3 = new ProductConfiguration(
                null, "Flower Options", true, 2.0, null,
                List.of(flowerColorAttributes3, flowerTypeAttributes3)
        );

        productService.addProduct(
                new ProductManagementDto(
                        blackVase,
                        2L,
                        List.of(vaseConfigurations3, flowerConfigurations3),
                        12L
                )
        );


    }


    //------------------- rugs -------------------

    private void rugs() {

        //----------------Heart Shape Rug--------------
        Product heartShapeRug = new Product(
                null,
                "Heart Shape Rug",
                "Soft and cozy heart-shaped rug, perfect for adding a warm and stylish touch to any space. Available in various colors and sizes.",
                "https://drive.google.com/thumbnail?id=1EwuxuZwT9uYdy7huQBUQ93jSXvvsYQlr", // Replace with actual image URL
                35.0, // Base price for Heart Shape Rug
                2, 6, true, true, null, false, true, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes shapeOptions = new ConfigurationAttributes(
                null, "Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Heart", 0.0), // Default Shape
                        new Choice("Round", 2.0),
                        new Choice("Oval", 2.5),
                        new Choice("Rectangle", 3.0)
                ),
                null
        );

        ConfigurationAttributes rugColorAttributes = new ConfigurationAttributes(
                null, "Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0), // White
                        new Choice("#FF0000", 0.0), // Red
                        new Choice("#FFC0CB", 0.0), // Pink
                        new Choice("#000000", 0.0), // Black
                        new Choice("#808080", 0.0), // Gray
                        new Choice("#B5A642", 0.0)  // Beige
                ),
                null
        );

        ConfigurationAttributes rugSizeAttributes = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (90cm x 90cm)", 0.0),
                        new Choice("Medium (120cm x 120cm)", 5.0),
                        new Choice("Large (150cm x 150cm)", 8.0),
                        new Choice("Extra Large (180cm x 180cm)", 12.0)
                ),
                null
        );

        ProductConfiguration rugConfigurations = new ProductConfiguration(
                null, "Shape", true, 0.0, null,
                List.of(shapeOptions)
        );
        ProductConfiguration rugConfigurations1 = new ProductConfiguration(
                null, "Color", true, 0.0, null,
                List.of(rugColorAttributes)
        );
        ProductConfiguration rugConfigurations2 = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(rugSizeAttributes)
        );


        productService.addProduct(
                new ProductManagementDto(
                        heartShapeRug,
                        2L,
                        List.of(rugConfigurations, rugConfigurations1, rugConfigurations2),
                        13L
                )
        );

        //------------ Egg Rug --------------

        Product eggRug = new Product(
                null,
                "Funny Shape Rug",
                "A soft and fun rug with unique shapes! Customize the shape, size, and facial expressions to match your vibe.",
                "https://drive.google.com/thumbnail?id=1snN37uVh4c4cdbOGeQDmZVL3crLspOeF", // Replace with actual image URL
                30.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );


        ConfigurationAttributes shapeOptions1 = new ConfigurationAttributes(
                null, "Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Egg", 0.0),  // Default
                        new Choice("Banana", 3.0),
                        new Choice("Cloud", 2.5),
                        new Choice("Star", 3.5),
                        new Choice("Heart", 2.0),
                        new Choice("Bear Face", 4.0),
                        new Choice("Cat Paw", 4.5)
                ),
                null
        );


        ConfigurationAttributes emojiFaceOptions = new ConfigurationAttributes(
                null, "Emoji Face",
                AttributeType.OTHER,
                List.of(
                        new Choice("😊 Smiling", 1.5),  // Default
                        new Choice("😂 Laughing", 1.5),
                        new Choice("😢 Sad", 1.5),
                        new Choice("😎 Cool", 1.5),
                        new Choice("🥰 Love", 1.5),
                        new Choice("😴 Sleeping", 1.5),
                        new Choice("No Face", 0.0)
                ),
                null
        );


        ConfigurationAttributes rugSizeAttributes1 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (80cm x 80cm)", 0.0),
                        new Choice("Medium (100cm x 100cm)", 5.0),
                        new Choice("Large (120cm x 120cm)", 8.0),
                        new Choice("Extra Large (150cm x 150cm)", 12.0)
                ),
                null
        );

        ProductConfiguration shapeConfigurations = new ProductConfiguration(
                null, "Shape", true, 0.0, null,
                List.of(shapeOptions1)
        );
        ProductConfiguration emojiConfigurations = new ProductConfiguration(
                null, "Emoji Face", true, 0.0, null,
                List.of(emojiFaceOptions)
        );
        ProductConfiguration rugSizeConfigurations = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(rugSizeAttributes1)
        );


        productService.addProduct(
                new ProductManagementDto(
                        eggRug,
                        2L,
                        List.of(shapeConfigurations, emojiConfigurations, rugSizeConfigurations),
                        13L
                )
        );

        //----------------Avocado Rug---------

        Product fruitRug = new Product(
                null,
                "Fruit Shape Rug",
                "A cozy rug with fun fruit shapes! Customize the shape, seed style, and face expression to add some playful vibes to your space.",
                "https://drive.google.com/thumbnail?id=1luWpsCqo8RWgML60FiEFi1RBsUFyBRsZ", // Replace with actual image URL
                35.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes fruitShapeOptions = new ConfigurationAttributes(
                null, "Fruit Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Avocado", 0.0),  // Default
                        new Choice("Lemon", 3.0),
                        new Choice("Orange", 2.5),
                        new Choice("Watermelon", 4.0),
                        new Choice("Pineapple", 3.5),
                        new Choice("Strawberry", 2.0)
                ),
                null
        );


        ConfigurationAttributes fruitEmojiFaceOptions = new ConfigurationAttributes(
                null, "Emoji Face",
                AttributeType.OTHER,
                List.of(
                        new Choice("No Face", 0.0),
                        new Choice("😊 Smiling", 1.5),  // Default
                        new Choice("😂 Laughing", 1.5),
                        new Choice("😢 Sad", 1.5),
                        new Choice("😎 Cool", 1.5),
                        new Choice("🥰 Love", 1.5),
                        new Choice("😴 Sleeping", 1.5)
                ),
                null
        );

        ConfigurationAttributes rugSizeOptions = new ConfigurationAttributes(
                null, "Rug Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (80cm x 80cm)", 0.0),
                        new Choice("Medium (100cm x 100cm)", 5.0),
                        new Choice("Large (120cm x 120cm)", 8.0),
                        new Choice("Extra Large (150cm x 150cm)", 12.0)
                ),
                null
        );

        ProductConfiguration fruitShapeConfig = new ProductConfiguration(
                null, "Fruit Shape", true, 0.0, null,
                List.of(fruitShapeOptions)
        );

        ProductConfiguration fruitEmojiFaceConfig = new ProductConfiguration(
                null, "Emoji Face", true, 0.0, null,
                List.of(fruitEmojiFaceOptions)
        );
        ProductConfiguration rugSizeConfig = new ProductConfiguration(
                null, "Rug Size", true, 0.0, null,
                List.of(rugSizeOptions)
        );

        productService.addProduct(
                new ProductManagementDto(
                        fruitRug,
                        2L,
                        List.of(fruitShapeConfig, fruitEmojiFaceConfig, rugSizeConfig),
                        13L
                )
        );


    }

    //------------------- mirrors -------------------
    private void mirrors() {

        Product mirrorProduct = new Product(
                null,
                "Suit Mirror",
                "A stylish mirror with customizable suit configurations to match your personal style. Choose the frame style, mirror shape, and suit color!",
                "https://drive.google.com/thumbnail?id=1Rb1ncXI6ZPt3Q6F6tg8eugcpGy9-4Dph", // 1Rb1ncXI6ZPt3Q6F6tg8eugcpGy9-4Dph
                50.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes frameStyleOptions = new ConfigurationAttributes(
                null, "Frame Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Wooden", 0.0),  // Default
                        new Choice("Metal", 5.0),
                        new Choice("Gold Plated", 10.0),
                        new Choice("Silver Plated", 8.0),
                        new Choice("Classic Black", 2.0)
                ),
                null
        );

        ConfigurationAttributes mirrorShapeOptions = new ConfigurationAttributes(
                null, "Mirror Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Rectangular", 0.0),
                        new Choice("Round", 0.0),  // Default
                        new Choice("Square", 0.0),
                        new Choice("Oval", 0.0),
                        new Choice("Heart", 0.0)
                ),
                null
        );


        ConfigurationAttributes mirrorSizeOptions = new ConfigurationAttributes(
                null, "Mirror Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (80cm x 80cm)", 0.0),
                        new Choice("Medium (100cm x 100cm)", 5.0),
                        new Choice("Large (120cm x 120cm)", 8.0),
                        new Choice("Extra Large (150cm x 150cm)", 12.0)
                ),
                null
        );

        ProductConfiguration frameStyleConfig = new ProductConfiguration(
                null, "Frame Style", true, 0.0, null,
                List.of(frameStyleOptions)
        );
        ProductConfiguration mirrorShapeConfig = new ProductConfiguration(
                null, "Mirror Shape", true, 0.0, null,
                List.of(mirrorShapeOptions)
        );

        ProductConfiguration mirrorSizeConfig = new ProductConfiguration(
                null, "Mirror Size", true, 0.0, null,
                List.of(mirrorSizeOptions)
        );

        productService.addProduct(
                new ProductManagementDto(
                        mirrorProduct,
                        2L,
                        List.of(frameStyleConfig, mirrorShapeConfig, mirrorSizeConfig),
                        15L
                )
        );

        Product deskOrganizerProduct = new Product(
                null,
                "Customizable Desk Organizer",
                "A stylish mirror with customizable suit configurations to match your personal style. Choose the frame style, mirror shape, and suit color!",
                "https://drive.google.com/thumbnail?id=11XpBp3eYYBJ8-DdzZfzlvKCM2BNntHPx", // Example thumbnail ID
                45.0, // Base price
                1, 4, true, true, null, false, true, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes materialOptions = new ConfigurationAttributes(
                null, "Frame Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Plastic", 0.0),  // Default
                        new Choice("Wood", 5.0),
                        new Choice("Metal", 5.0)

                ),
                null
        );

        ConfigurationAttributes colorOptions = new ConfigurationAttributes(
                null, "Frame Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#808080 ", 0.0),   // Gray
                        new Choice("#000000 ", 0.0),  // Black
                        new Choice("#FFFFFF ", 0.0),  // White (Default)
                        new Choice("#0000FF ", 0.0),   // Blue
                        new Choice("#FF0000 ", 0.0)     // Red
                ),
                null
        );

        ConfigurationAttributes sizeOptions = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (20cm x 15cm)", 0.0),
                        new Choice("Medium (30cm x 20cm)", 8.0),
                        new Choice("Large (40cm x 25cm)", 12.0)
                ),
                null
        );

        ProductConfiguration materialConfig = new ProductConfiguration(
                null, "Material", true, 0.0, null,
                List.of(materialOptions)
        );
        ProductConfiguration colorConfig = new ProductConfiguration(
                null, "Color", true, 0.0, null,
                List.of(colorOptions)
        );

        ProductConfiguration sizeConfig = new ProductConfiguration(
                null, "Size", true, 0.0, null,
                List.of(sizeOptions)
        );

        productService.addProduct(
                new ProductManagementDto(
                        deskOrganizerProduct,
                        2L,
                        List.of(materialConfig, colorConfig, sizeConfig),
                        15L
                )
        );


    }

    //------------------- candles -------------------
    private void candles() {

        Product shapedCandle = new Product(
                null,
                "Shaped Candle",
                "A beautifully shaped candle in modern designs! Choose your favorite shape, scent, size, and color for the perfect ambiance.",
                "https://drive.google.com/thumbnail?id=1Siv2HJXCGUdENldaKzPGhpY-DtFcQxaB", // 1Siv2HJXCGUdENldaKzPGhpY-DtFcQxaB
                20.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes candleShapeOptions = new ConfigurationAttributes(
                null, "Candle Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Yarn Ball", 0.0),
                        new Choice("Bubble", 0.0),
                        new Choice("Geometric Cube", 0.0),
                        new Choice("Cylinder", 0.0),
                        new Choice("Twisted Spiral", 0.0),
                        new Choice("Cone", 0.0)
                ),
                null
        );

        ConfigurationAttributes candleScentOptions = new ConfigurationAttributes(
                null, "Candle Scent",
                AttributeType.OTHER,
                List.of(
                        new Choice("Unscented", 0.0),
                        new Choice("Lavender", 1.5),  // Default
                        new Choice("Vanilla", 1.5),
                        new Choice("Rose", 1.5),
                        new Choice("Citrus", 1.5),
                        new Choice("Cinnamon", 1.5)

                ),
                null
        );

        ConfigurationAttributes candleSizeOptions = new ConfigurationAttributes(
                null, "Candle Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (5cm x 5cm)", 0.0),
                        new Choice("Medium (7cm x 7cm)", 3.0),
                        new Choice("Large (10cm x 10cm)", 5.0),
                        new Choice("Extra Large (15cm x 15cm)", 8.0)
                ),
                null
        );

        ConfigurationAttributes candleColorOptions = new ConfigurationAttributes(
                null, "Candle Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0),  // Default
                        new Choice("#FF5733", 0.0),
                        new Choice("#3498DB", 0.0),
                        new Choice("#F39C12", 0.0),
                        new Choice("#F1C40F", 0.0),
                        new Choice("#2ECC71", 0.0)
                ),
                null
        );

        ConfigurationAttributes toppingDecorationOptions = new ConfigurationAttributes(
                null, "Topping Decoration",
                AttributeType.OTHER,
                List.of(
                        new Choice("None", 0.0),  // Default
                        new Choice("Gold Paper", 2.5),
                        new Choice("Silver Paper", 2.0),
                        new Choice("Glitter", 3.0),
                        new Choice("Ribbons", 2.0),
                        new Choice("Dried Flowers", 2.0)
                ),
                null
        );

        ProductConfiguration candleShapeConfig = new ProductConfiguration(
                null, "Candle Shape", true, 0.0, null,
                List.of(candleShapeOptions)
        );
        ProductConfiguration candleScentConfig = new ProductConfiguration(
                null, "Candle Scent", true, 0.0, null,
                List.of(candleScentOptions)
        );
        ProductConfiguration candleSizeConfig = new ProductConfiguration(
                null, "Candle Size", true, 0.0, null,
                List.of(candleSizeOptions)
        );
        ProductConfiguration candleColorConfig = new ProductConfiguration(
                null, "Candle Color", true, 0.0, null,
                List.of(candleColorOptions)
        );

        ProductConfiguration toppingDecorationConfig = new ProductConfiguration(
                null, "Topping Decoration", true, 0.0, null,
                List.of(toppingDecorationOptions)
        );
        productService.addProduct(
                new ProductManagementDto(
                        shapedCandle,
                        2L,
                        List.of(candleShapeConfig, candleScentConfig, candleSizeConfig, candleColorConfig, toppingDecorationConfig),
                        14L
                )
        );

        //---------custom candle ---------
        Product customCandle = new Product(
                null,
                "Custom Candle in Cup",
                "A customizable candle in a cup with a creamy topping and decorative elements. Choose your favorite cup style, topping, and decoration to create a personalized candle experience.",
                "https://drive.google.com/thumbnail?id=1spAX52qpyHJpgb8pM42o7obqePjFlSSq", // 1spAX52qpyHJpgb8pM42o7obqePjFlSSq
                25.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes cupStyleOptions = new ConfigurationAttributes(
                null, "Cup Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Glass Cup", 0.0),
                        new Choice("White Ceramic", 2.0),  // Default
                        new Choice("Mason Jar", 4.0),
                        new Choice("Gold-plated Cup", 5.0),
                        new Choice("Marble Cup", 4.0)
                ),
                null
        );

        ConfigurationAttributes creamToppingOptions = new ConfigurationAttributes(
                null, "Cream Topping",
                AttributeType.OTHER,
                List.of(
                        new Choice("None", 0.0),
                        new Choice("Vanilla Cream", 2.0),  // Default
                        new Choice("Chocolate Cream", 2.0),
                        new Choice("Strawberry Cream", 2.0),
                        new Choice("Caramel Cream", 2.0),
                        new Choice("Coconut Cream", 2.0),
                        new Choice("None", 0.0)
                ),
                null
        );

        ConfigurationAttributes decorationOptions = new ConfigurationAttributes(
                null, "Decoration",
                AttributeType.OTHER,
                List.of(
                        new Choice("None", 0.0),  // Default
                        new Choice("Gold Leaf", 3.0),
                        new Choice("Silver Glitter", 3.0),
                        new Choice("Dried Flowers", 3.0),
                        new Choice("Mini Hearts", 3.0),
                        new Choice("Ribbon", 3.0),
                        new Choice("Crushed Seashells", 3.0)
                ),
                null
        );

        ConfigurationAttributes candleSizeOptions1 = new ConfigurationAttributes(
                null, "Candle Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (5cm x 5cm)", 0.0),
                        new Choice("Medium (7cm x 7cm)", 3.0),
                        new Choice("Large (10cm x 10cm)", 5.0),
                        new Choice("Extra Large (15cm x 15cm)", 8.0)
                ),
                null
        );

        ConfigurationAttributes candleColorOptions1 = new ConfigurationAttributes(
                null, "Candle Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0),  // Default
                        new Choice("#FF5733", 0.0),
                        new Choice("#3498DB", 0.0),
                        new Choice("#F39C12", 0.0),
                        new Choice("#F1C40F", 0.0),
                        new Choice("#2ECC71", 0.0)
                ),
                null
        );

        ProductConfiguration cupStyleConfig = new ProductConfiguration(
                null, "Cup Style", true, 0.0, null,
                List.of(cupStyleOptions)
        );
        ProductConfiguration creamToppingConfig = new ProductConfiguration(
                null, "Cream Topping", true, 0.0, null,
                List.of(creamToppingOptions)
        );
        ProductConfiguration decorationConfig = new ProductConfiguration(
                null, "Decoration", true, 0.0, null,
                List.of(decorationOptions)
        );
        ProductConfiguration candleSizeConfig1 = new ProductConfiguration(
                null, "Candle Size", true, 0.0, null,
                List.of(candleSizeOptions1)
        );
        ProductConfiguration candleColorConfig1 = new ProductConfiguration(
                null, "Candle Color", true, 0.0, null,
                List.of(candleColorOptions1)
        );

        productService.addProduct(
                new ProductManagementDto(
                        customCandle,
                        2L,
                        List.of(cupStyleConfig, creamToppingConfig, decorationConfig, candleSizeConfig1, candleColorConfig1),
                        14L
                )
        );

        //--------------Regular Candle ----------

        Product regularCandle = new Product(
                null,
                "Regular Candle",
                "A classic candle with customizable color, size, and scent to fit your preference. Choose from a variety of options for a perfect candle experience.",
                "https://drive.google.com/thumbnail?id=1a_1Nrk1ao3BNTtmfvTaCfBm8rODB4e5B", // 1a_1Nrk1ao3BNTtmfvTaCfBm8rODB4e5B
                15.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null, null, null
        );

        ConfigurationAttributes candleColorCustomization = new ConfigurationAttributes(
                null, "Candle Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FFFFFF", 0.0),  // Default
                        new Choice("#FF5733", 0.0),
                        new Choice("#3498DB", 0.0),
                        new Choice("#F39C12", 0.0),
                        new Choice("#F1C40F", 0.0)
                ),
                null
        );

        ConfigurationAttributes candleSizeCustomization = new ConfigurationAttributes(
                null, "Candle Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (5cm x 5cm)", 0.0),
                        new Choice("Medium (7cm x 7cm)", 3.0),
                        new Choice("Large (10cm x 10cm)", 5.0),
                        new Choice("Extra Large (15cm x 15cm)", 8.0)
                ),
                null
        );

        ConfigurationAttributes candleScentCustomization = new ConfigurationAttributes(
                null, "Candle Scent",
                AttributeType.OTHER,
                List.of(
                        new Choice("Lavender", 1.5),  // Default
                        new Choice("Vanilla", 1.5),
                        new Choice("Rose", 1.5),
                        new Choice("Citrus", 1.5),
                        new Choice("Cinnamon", 1.5),
                        new Choice("Unscented", 0.0)
                ),
                null
        );

        ProductConfiguration candleColorConfig3 = new ProductConfiguration(
                null, "Color Customization", true, 0.0, null,
                List.of(candleColorCustomization)
        );

        ProductConfiguration candleSizeConfig3 = new ProductConfiguration(
                null, "Size Customization", true, 0.0, null,
                List.of(candleSizeCustomization)
        );

        ProductConfiguration candleScentConfig3 = new ProductConfiguration(
                null, "Scent Customization", true, 0.0, null,
                List.of(candleScentCustomization)
        );

        productService.addProduct(
                new ProductManagementDto(
                        regularCandle,
                        2L,
                        List.of(candleColorConfig3, candleSizeConfig3, candleScentConfig3),
                        14L
                )
        );


    }

    //------------------- wallArt -------------------
    private void wallArt() {

    }

}
