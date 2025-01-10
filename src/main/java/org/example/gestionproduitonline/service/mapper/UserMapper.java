package org.example.gestionproduitonline.service.mapper;

import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.web.rest.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDto = new UserDTO();
        Optional.of(user.getId()).ifPresent(userDto::setId);
        Optional.ofNullable(user.getEmail()).ifPresent(userDto::setEmail);
        Optional.ofNullable(user.getName()).ifPresent(userDto::setName);

        return userDto;
    }

    public User toEntity(UserDTO userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();
        Optional.of(userDto.getId()).ifPresent(user::setId);
        Optional.of(userDto.getEmail()).ifPresent(user::setEmail);
        Optional.of(userDto.getName()).ifPresent(user::setName);

        return user;
    }
}
