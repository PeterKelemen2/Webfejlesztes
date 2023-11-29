package unideb.webfejlesztes.dto;

import unideb.webfejlesztes.model.User;

public record UserDTO(
        String name
) {
    public static UserDTO fromDao(User user){
        return new UserDTO(
                user.getUsername()
        );
    }
}
