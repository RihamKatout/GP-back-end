package gp.riham_aisha.back_end.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailDto(
        @NotNull(message = "Recipient ID cannot be null") Long toId,
        @NotBlank(message = "Subject cannot be blank") String subject,
        @NotBlank(message = "Text cannot be blank") String text
) {
}
