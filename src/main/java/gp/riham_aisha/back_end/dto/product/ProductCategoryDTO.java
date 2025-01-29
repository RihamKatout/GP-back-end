package gp.riham_aisha.back_end.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductCategoryDTO(
        Long id,
        @NotBlank @Size(min = 3) String name,
        @NotNull Long storeCategoryId,
        String imageurl
) {
}