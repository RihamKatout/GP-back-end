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
public class SiwarStoreProducts {
    private final ProductService productService;

    public void loadProducts() {
        plushToys();
        doll();

    }

    //------------------- plushToys -------------------

    private void plushToys() {

        Product mushroomPlush = new Product(
                null,
                "Mushroom Plush Toy",
                "A soft and adorable mushroom plush toy! Customize its color and size for a unique and cozy companion.",
                "https://drive.google.com/thumbnail?id=1xFDxhJs1DhuaPcRiOdcAOiXny9TERCGV", // 1xFDxhJs1DhuaPcRiOdcAOiXny9TERCGV
                25.0, // Base price
                2, 6, true, true, null, false, true, null, 0, null, null, null, null
        );

        ConfigurationAttributes plushColorCustomization = new ConfigurationAttributes(
                null, "Plush Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FF0000", 0.0),  // Default
                        new Choice("#FFC0CB", 0.0),
                        new Choice("#8B4513", 0.0),
                        new Choice("#228B22", 0.0),
                        new Choice("#FFD700", 0.0),
                        new Choice("#FFFFFF", 0.0),
                        new Choice("#800080", 0.0)
                ),
                null
        );

        ConfigurationAttributes plushSizeCustomization = new ConfigurationAttributes(
                null, "Plush Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (20cm)", 0.0),
                        new Choice("Medium (30cm)", 5.0),
                        new Choice("Large (40cm)", 8.0),
                        new Choice("Extra Large (50cm)", 12.0)
                ),
                null
        );


        ProductConfiguration plushColorConfig = new ProductConfiguration(
                null, "Color ", true, 0.0, null,
                List.of(plushColorCustomization)
        );

        ProductConfiguration plushSizeConfig = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(plushSizeCustomization)
        );


        productService.addProduct(
                new ProductManagementDto(
                        mushroomPlush,
                        1L,
                        List.of(plushColorConfig, plushSizeConfig),
                        10L
                )
        );

        //-----------Buzzed Toy----------

        Product buzzedToy = new Product(
                null,
                "Buzzed Toy",
                "A fun and unique buzzed toy! Customize its color and size to make it perfect for you.",
                "https://drive.google.com/thumbnail?id=1YPDI5R68rm7dpiRsBBmU5jwBrU8Ftn5g", // Replace with actual image URL
                20.0, // Base price
                2, 6, true, true, null, false, true, null, 0, null, null, null, null
        );

        ConfigurationAttributes buzzedColorCustomization = new ConfigurationAttributes(
                null, "Toy Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FF5733", 0.0),  // Default
                        new Choice("#33FF57", 0.0),
                        new Choice("#3357FF", 0.0),
                        new Choice("#F1C40F", 0.0),
                        new Choice("#8E44AD", 0.0),
                        new Choice("#ECF0F1", 0.0),
                        new Choice("#2C3E50", 0.0)
                ),
                null
        );

        ConfigurationAttributes buzzedSizeCustomization = new ConfigurationAttributes(
                null, "Toy Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 5.0),
                        new Choice("Large (35cm)", 8.0),
                        new Choice("Extra Large (45cm)", 12.0)
                ),
                null
        );

        ProductConfiguration buzzedColorConfig = new ProductConfiguration(
                null, "Color ", true, 0.0, null,
                List.of(buzzedColorCustomization)
        );

        ProductConfiguration buzzedSizeConfig = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(buzzedSizeCustomization)
        );

        productService.addProduct(
                new ProductManagementDto(
                        buzzedToy,
                        1L,
                        List.of(buzzedColorConfig, buzzedSizeConfig),
                        10L
                )
        );

        //--------------Custom Yarn toy -------------

        Product customYarnToy = new Product(
                null,
                "Custom Yarn Toy",
                "A handmade custom yarn toy! Choose your favorite color, size, character, and suit style for a fully personalized experience.",
                "https://drive.google.com/thumbnail?id=1qwWQb_AdAZ3juMQZiu03IJqI7vo97IuI", // Replace with actual image URL
                30.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null
        );

        ConfigurationAttributes yarnColorCustomization = new ConfigurationAttributes(
                null, "Yarn Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#D35400", 0.0),  // Default
                        new Choice("#1ABC9C", 0.0),
                        new Choice("#3498DB", 0.0),
                        new Choice("#9B59B6", 0.0),
                        new Choice("#E74C3C", 0.0),
                        new Choice("#F1C40F", 0.0)
                ),
                null
        );

        ConfigurationAttributes yarnSizeCustomization = new ConfigurationAttributes(
                null, "Toy Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (10cm)", 0.0),
                        new Choice("Medium (20cm)", 5.0),
                        new Choice("Large (30cm)", 8.0),
                        new Choice("Extra Large (40cm)", 12.0)
                ),
                null
        );

        ConfigurationAttributes yarnCharacterCustomization = new ConfigurationAttributes(
                null, "Character",
                AttributeType.OTHER,
                List.of(
                        new Choice("Bunny", 3.0),
                        new Choice("Cat", 3.0),
                        new Choice("Bear", 3.5),
                        new Choice("Dog", 3.5),
                        new Choice("Fox", 4.0),
                        new Choice("No Character", 0.0)  // Default
                ),
                null
        );

        ConfigurationAttributes yarnSuitCustomization = new ConfigurationAttributes(
                null, "Suit Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Casual", 3.0),
                        new Choice("Formal", 5.0),
                        new Choice("Superhero", 6.5),
                        new Choice("Winter Outfit", 4.0),
                        new Choice("Animal Costume", 5.5),
                        new Choice("No Suit", 0.0)  // Default
                ),
                null
        );

        ProductConfiguration yarnColorConfig = new ProductConfiguration(
                null, "Color ", true, 0.0, null,
                List.of(yarnColorCustomization)
        );

        ProductConfiguration yarnSizeConfig = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(yarnSizeCustomization)
        );

        ProductConfiguration yarnCharacterConfig = new ProductConfiguration(
                null, "Character ", true, 0.0, null,
                List.of(yarnCharacterCustomization)
        );

        ProductConfiguration yarnSuitConfig = new ProductConfiguration(
                null, "Suit ", true, 0.0, null,
                List.of(yarnSuitCustomization)
        );

        productService.addProduct(
                new ProductManagementDto(
                        customYarnToy,
                        1L,
                        List.of(yarnColorConfig, yarnSizeConfig, yarnCharacterConfig, yarnSuitConfig),
                        10L
                )
        );

        //------------Custom Shape Toy ---------

        Product customYarnShape = new Product(
                null,
                "Custom Yarn Shape",
                "A handmade custom yarn decoration! Choose your favorite shape, color, size, and an optional emoji face for a fun and unique design.",
                "https://drive.google.com/thumbnail?id=1fhkXD7FNm6t8Cc-qTILPdEo8Gkw72TUM", // Replace with actual image URL
                22.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null
        );

        ConfigurationAttributes yarnShapeCustomization1 = new ConfigurationAttributes(
                null, "Yarn Shape",
                AttributeType.OTHER,
                List.of(
                        new Choice("Heart", 3.0),
                        new Choice("Star", 3.5),
                        new Choice("Cloud", 4.0),
                        new Choice("Moon", 4.5),
                        new Choice("Flower", 5.0),
                        new Choice("Circle", 2.5),
                        new Choice("No Shape", 0.0)  // Default
                ),
                null
        );

        ConfigurationAttributes yarnColorCustomization1 = new ConfigurationAttributes(
                null, "Yarn Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#FF5733", 0.0),  // Default
                        new Choice("#FFC300", 0.0),
                        new Choice("#DAF7A6", 0.0),
                        new Choice("#900C3F", 0.0),
                        new Choice("#581845", 0.0),
                        new Choice("#1ABC9C", 0.0)
                ),
                null
        );

        ConfigurationAttributes yarnSizeCustomization1 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (10cm)", 0.0),
                        new Choice("Medium (15cm)", 3.0),
                        new Choice("Large (20cm)", 5.0),
                        new Choice("Extra Large (25cm)", 7.0)
                ),
                null
        );

        ConfigurationAttributes emojiFaceCustomization = new ConfigurationAttributes(
                null, "Emoji Face",
                AttributeType.OTHER,
                List.of(
                        new Choice("😊 Smiling", 1.5),  // Default
                        new Choice("😂 Laughing", 1.5),
                        new Choice("😢 Sad", 1.5),
                        new Choice("😎 Cool", 1.5),
                        new Choice("🥰 Love", 1.5),
                        new Choice("😴 Sleeping", 1.5),
                        new Choice("No Face", 0.0)  // Default
                ),
                null
        );

        ProductConfiguration yarnShapeConfig = new ProductConfiguration(
                null, "Shape ", true, 0.0, null,
                List.of(yarnShapeCustomization1)
        );

        ProductConfiguration yarnColorConfig1 = new ProductConfiguration(
                null, "Color ", true, 0.0, null,
                List.of(yarnColorCustomization1)
        );

        ProductConfiguration yarnSizeConfig1 = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(yarnSizeCustomization1)
        );

        ProductConfiguration emojiFaceConfig = new ProductConfiguration(
                null, "Emoji Face ", true, 0.0, null,
                List.of(emojiFaceCustomization)
        );

        productService.addProduct(
                new ProductManagementDto(
                        customYarnShape,
                        1L,
                        List.of(yarnShapeConfig, yarnColorConfig1, yarnSizeConfig1, emojiFaceConfig),
                        10L
                )
        );




    }

    //------------------- doll -------------------

    private void doll() {

        //------------custom doll --------------

        Product girlDollToy = new Product(
                null,
                "Custom Girl Doll",
                "A beautiful handmade girl doll! Customize the hair, eyes, outfit, skin tone, and size to create your perfect doll.",
                "https://drive.google.com/thumbnail?id=1RcV3I0KTFXhraPTv3C2fAub3009mDWVm", // Replace with actual image URL
                35.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null
        );

// Hair Style Customization
        ConfigurationAttributes hairStyleCustomization = new ConfigurationAttributes(
                null, "Hair Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Short Bob", 0.0),
                        new Choice("Long Straight", 2.0),
                        new Choice("Curly Hair", 2.5),
                        new Choice("Pigtails", 3.0),
                        new Choice("Braided", 3.5),
                        new Choice("No Hair", 0.0)  // Default (Bald option)
                ),
                null
        );

// Hair Color Customization (Hex Only)
        ConfigurationAttributes hairColorCustomization = new ConfigurationAttributes(
                null, "Hair Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#000000", 0.0), // Black
                        new Choice("#8B4513", 0.0), // Brown
                        new Choice("#FFD700", 0.0), // Blonde
                        new Choice("#FF4500", 0.0), // Red
                        new Choice("#A52A2A", 0.0), // Auburn
                        new Choice("#FFFFFF", 0.0)  // White
                ),
                null
        );

// Eye Color Customization (Hex Only)
        ConfigurationAttributes eyeColorCustomization = new ConfigurationAttributes(
                null, "Eye Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#0000FF", 0.0), // Blue
                        new Choice("#008000", 0.0), // Green
                        new Choice("#A52A2A", 0.0), // Hazel
                        new Choice("#6A5ACD", 0.0), // Purple
                        new Choice("#8B4513", 0.0), // Brown
                        new Choice("#000000", 0.0)  // Black
                ),
                null
        );

// Skin Tone Customization (Hex Only)
        ConfigurationAttributes skinToneCustomization = new ConfigurationAttributes(
                null, "Skin Tone",
                AttributeType.COLOR,
                List.of(
                        new Choice("#F5CBA7", 0.0),  // Light
                        new Choice("#E0AC69", 0.0),  // Tan
                        new Choice("#D08C60", 0.0),  // Medium
                        new Choice("#9C7248", 0.0),  // Olive
                        new Choice("#5C4033", 0.0),  // Dark
                        new Choice("#3D2B1F", 0.0)   // Deep
                ),
                null
        );

// Outfit Customization
        ConfigurationAttributes outfitCustomization = new ConfigurationAttributes(
                null, "Outfit",
                AttributeType.OTHER,
                List.of(
                        new Choice("Casual Dress", 0.0),
                        new Choice("Princess Gown", 5.0),
                        new Choice("Sporty Outfit", 3.0),
                        new Choice("Winter Coat", 4.0),
                        new Choice("Pajamas", 2.0),
                        new Choice("No Outfit", 0.0)  // Default
                ),
                null
        );

// Size Customization
        ConfigurationAttributes dollSizeCustomization = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 5.0),
                        new Choice("Large (35cm)", 8.0),
                        new Choice("Extra Large (45cm)", 12.0)
                ),
                null
        );

// Product Configurations
        ProductConfiguration hairStyleConfig = new ProductConfiguration(
                null, "Hair ", true, 0.0, null,
                List.of(hairStyleCustomization , hairColorCustomization)
        );



        ProductConfiguration eyeColorConfig = new ProductConfiguration(
                null, "Eye Color ", true, 0.0, null,
                List.of(eyeColorCustomization)
        );

        ProductConfiguration skinToneConfig = new ProductConfiguration(
                null, "Skin Tone ", true, 0.0, null,
                List.of(skinToneCustomization)
        );

        ProductConfiguration outfitConfig = new ProductConfiguration(
                null, "Outfit ", true, 0.0, null,
                List.of(outfitCustomization)
        );

        ProductConfiguration dollSizeConfig = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(dollSizeCustomization)
        );

// Adding Product with Configurations
        productService.addProduct(
                new ProductManagementDto(
                        girlDollToy,
                        1L,
                        List.of(hairStyleConfig,  eyeColorConfig, skinToneConfig, outfitConfig, dollSizeConfig),
                        9L
                )
        );


        Product girlDollToy2 = new Product(
                null,
                "Custom Girl Doll",
                "A beautiful handmade girl doll! Customize the hair, eyes, outfit, skin tone, and size to create your perfect doll.",
                "https://drive.google.com/thumbnail?id=16psD5iWeukSpUKsWrWrk09NRVzaeogDJ", // Replace with actual image URL
                35.0, // Base price
                2, 6, true, true, null, false, false, null, 0, null, null, null, null
        );

// Hair Style Customization
        ConfigurationAttributes hairStyleCustomization1 = new ConfigurationAttributes(
                null, "Hair Style",
                AttributeType.OTHER,
                List.of(
                        new Choice("Short Bob", 0.0),
                        new Choice("Long Straight", 2.0),
                        new Choice("Curly Hair", 2.5),
                        new Choice("Pigtails", 3.0),
                        new Choice("Braided", 3.5),
                        new Choice("No Hair", 0.0)  // Default (Bald option)
                ),
                null
        );

// Hair Color Customization (Hex Only)
        ConfigurationAttributes hairColorCustomization1 = new ConfigurationAttributes(
                null, "Hair Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#000000", 0.0), // Black
                        new Choice("#8B4513", 0.0), // Brown
                        new Choice("#FFD700", 0.0), // Blonde
                        new Choice("#FF4500", 0.0), // Red
                        new Choice("#A52A2A", 0.0), // Auburn
                        new Choice("#FFFFFF", 0.0)  // White
                ),
                null
        );

// Eye Color Customization (Hex Only)
        ConfigurationAttributes eyeColorCustomization1 = new ConfigurationAttributes(
                null, "Eye Color",
                AttributeType.COLOR,
                List.of(
                        new Choice("#0000FF", 0.0), // Blue
                        new Choice("#008000", 0.0), // Green
                        new Choice("#A52A2A", 0.0), // Hazel
                        new Choice("#6A5ACD", 0.0), // Purple
                        new Choice("#8B4513", 0.0), // Brown
                        new Choice("#000000", 0.0)  // Black
                ),
                null
        );

// Skin Tone Customization (Hex Only)
        ConfigurationAttributes skinToneCustomization1 = new ConfigurationAttributes(
                null, "Skin Tone",
                AttributeType.COLOR,
                List.of(
                        new Choice("#F5CBA7", 0.0),  // Light
                        new Choice("#E0AC69", 0.0),  // Tan
                        new Choice("#D08C60", 0.0),  // Medium
                        new Choice("#9C7248", 0.0),  // Olive
                        new Choice("#5C4033", 0.0),  // Dark
                        new Choice("#3D2B1F", 0.0)   // Deep
                ),
                null
        );

// Outfit Customization
        ConfigurationAttributes outfitCustomization1 = new ConfigurationAttributes(
                null, "Outfit",
                AttributeType.OTHER,
                List.of(
                        new Choice("Casual Dress", 0.0),
                        new Choice("Princess Gown", 5.0),
                        new Choice("Sporty Outfit", 3.0),
                        new Choice("Winter Coat", 4.0),
                        new Choice("Pajamas", 2.0),
                        new Choice("No Outfit", 0.0)  // Default
                ),
                null
        );

// Size Customization
        ConfigurationAttributes dollSizeCustomization1 = new ConfigurationAttributes(
                null, "Size",
                AttributeType.SIZE,
                List.of(
                        new Choice("Small (15cm)", 0.0),
                        new Choice("Medium (25cm)", 5.0),
                        new Choice("Large (35cm)", 8.0),
                        new Choice("Extra Large (45cm)", 12.0)
                ),
                null
        );

// Product Configurations
        ProductConfiguration hairStyleConfig1 = new ProductConfiguration(
                null, "Hair ", true, 0.0, null,
                List.of(hairStyleCustomization1 , hairColorCustomization1)
        );



        ProductConfiguration eyeColorConfig1 = new ProductConfiguration(
                null, "Eye Color ", true, 0.0, null,
                List.of(eyeColorCustomization1)
        );

        ProductConfiguration skinToneConfig1 = new ProductConfiguration(
                null, "Skin Tone ", true, 0.0, null,
                List.of(skinToneCustomization1)
        );

        ProductConfiguration outfitConfig1 = new ProductConfiguration(
                null, "Outfit ", true, 0.0, null,
                List.of(outfitCustomization1)
        );

        ProductConfiguration dollSizeConfig1 = new ProductConfiguration(
                null, "Size ", true, 0.0, null,
                List.of(dollSizeCustomization1)
        );

// Adding Product with Configurations
        productService.addProduct(
                new ProductManagementDto(
                        girlDollToy2,
                        1L,
                        List.of(hairStyleConfig1, eyeColorConfig1, skinToneConfig1, outfitConfig1, dollSizeConfig1),
                        9L
                )
        );






    }


}
