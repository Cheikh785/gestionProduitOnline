package org.example.gestionproduitonline.service;

import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.web.rest.dto.UserDTO;
import org.example.gestionproduitonline.web.rest.vm.RegisterVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface UserService {
    /**
     * Save a User
     *
     * @param registerVM the entity to save.
     * @return the persisted entity.
     */
    UserDTO save(RegisterVM registerVM);

    /**
     * Update a User
     *
     * @param user the entity to update
     * @param id the id of the entity to update
     * @return the updated entity.
     */
    UserDTO update(User user, Long id);

    /**
     * get all Users
     *
     * @return list of users.
     */
    Page<UserDTO> findAll(Pageable pageable);

    /**
     * get a User by id
     *
     * @param id the id of the entity.
     * @return the entity
     */
    Optional<User> findOne(Long id);

    /**
     * get a User by email (active or disabled users)
     *
     * @param email the id of the user.
     * @return the entity
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Delete a User
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
