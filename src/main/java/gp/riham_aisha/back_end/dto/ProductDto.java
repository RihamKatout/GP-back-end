package gp.riham_aisha.back_end.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

import java.util.List;
import java.util.Map;

@Builder
public record ProductDto(

        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name,

        @NotNull(message = "Description cannot be null")
        @Size(min = 1, message = "Description must not be empty")
        String description,

        @NotNull(message = "Price cannot be null")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        double price,

        @NotNull(message = "Stock cannot be null")
        @Min(value = 0, message = "Stock must be zero or greater")
        int stock,

        @URL(message = "Image URL must be valid")
        String imageurl,

        @NotNull(message = "Availability must be specified")
        Boolean isAvailable,

        @NotNull(message = "Customizability must be specified")
        Boolean isCustomizable,

        String model3dURL,

        @NotNull(message = "Category ID cannot be null")
        Long productCategoryId,

        @NotNull(message = "Store ID cannot be null")
        Long storeId,

        List<String> colors,
        Map<gp.riham_aisha.back_end.enums.Size, Double> sizePrices) {
}
