package gp.riham_aisha.back_end.dto;

public record RegistrationRequest(String username, String firstName, String lastName,
                                  String email, String password,
                                  String phoneNumber) {
}
