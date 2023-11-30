package unideb.webfejlesztes.dto;

import unideb.webfejlesztes.model.User;

public record UserDTO(
        long id,
        String name
) {
    public static UserDTO fromDao(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}
