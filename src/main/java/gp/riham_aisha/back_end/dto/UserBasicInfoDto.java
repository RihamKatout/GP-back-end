package gp.riham_aisha.back_end.dto;

import gp.riham_aisha.back_end.model.User;

public record UserBasicInfoDto(
    Long id,
    String username,
    String firstName,
    String lastName,
    String imageurl
) {
    public static UserBasicInfoDto fromUser(User user) {
        return new UserBasicInfoDto(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getUserImageUrl()
        );
    }
}
