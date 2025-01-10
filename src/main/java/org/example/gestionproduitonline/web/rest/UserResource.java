package org.example.gestionproduitonline.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.example.gestionproduitonline.constants.ErrorCodes;
import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.exception.NotFoundEntityException;
import org.example.gestionproduitonline.service.UserService;
import org.example.gestionproduitonline.web.rest.dto.UserDTO;
import org.example.gestionproduitonline.web.rest.vm.RegisterVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code POST  /users} : Create a new User
     *
     * @param registerVM the user to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user,
     * or with status {@code 400 (Bad Request)} if the user has already an id.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody RegisterVM registerVM) throws URISyntaxException {
        UserDTO result = userService.save(registerVM);
        return ResponseEntity.created(new URI("/api/v1/users" + result.getId())).body(result);
    }

    /**
     * {@code PUT  /users/{id}} : Update an existing user
     *
     * @param userDto the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user,
     * or with status {@code 400 (Bad Request)} if the user's id is not found,
     * or with status {@code 500 (Internal Server Error)} if the user couldn't be updated.
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Long id) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        UserDTO updatedUser = userService.update(user, id);
        return ResponseEntity.ok().body(updatedUser);
    }

    /**
     * {@code GET  /users} : get all users
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of user in body.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> page = this.userService.findAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /users/:id} : get the User by id.
     *
     * @param id the id of the user to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user,
     * or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService
            .findOne(id)
            .orElseThrow(
                    () -> new NotFoundEntityException(ErrorCodes.USER_NOT_FOUND.toString(), "No user found with this id")
            );
        UserDTO userDto = new UserDTO();
        userDto.setId(id);
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());

        return ResponseEntity.ok().body(userDto);
    }

    /**
     * {@code DELETE  /users/:id} : delete User by id.
     *
     * @param id the id of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
