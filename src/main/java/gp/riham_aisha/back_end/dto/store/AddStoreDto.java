package gp.riham_aisha.back_end.dto.store;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddStoreDto(

        @NotNull(message = "Name cannot be null")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,

        @NotNull(message = "Description cannot be null")
        @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
        String description,

        String logoURL,
        String coverURL,

        @NotNull(message = "Category ID cannot be null")
        Long categoryId
) {
}
