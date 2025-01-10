package org.example.gestionproduitonline.service.impl;

import org.example.gestionproduitonline.constants.ErrorCodes;
import org.example.gestionproduitonline.domain.User;
import org.example.gestionproduitonline.exception.AlreadyExistEntityException;
import org.example.gestionproduitonline.exception.NotFoundEntityException;
import org.example.gestionproduitonline.repository.UserRepository;
import org.example.gestionproduitonline.service.UserService;
import org.example.gestionproduitonline.service.mapper.UserMapper;
import org.example.gestionproduitonline.web.rest.dto.UserDTO;
import org.example.gestionproduitonline.web.rest.vm.RegisterVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserMapper userMapper;

    public UserDTO save(RegisterVM registerVM) {
        if (this.findUserByEmail(registerVM.getEmail()).isPresent()) {
            throw new AlreadyExistEntityException(ErrorCodes.USER_ALREADY_EXISTS.toString(), "Email already exists : " + registerVM.getEmail());
        }

        User user = new User();
        user.setEmail(registerVM.getEmail());
        user.setName(registerVM.getName());
        user.setPassword(registerVM.getPassword());

        User userSaved = userRepository.save(user);

        return this.userMapper.toDto(userSaved);
    }

    @Override
    public UserDTO update(User user, Long id) {
        User userFound = findOne(id).orElseThrow(() -> new NotFoundEntityException(ErrorCodes.USER_NOT_FOUND.toString(), "No user found with this id " + id));

        Optional.ofNullable(user.getEmail()).ifPresent(userFound::setEmail);
        Optional.ofNullable(user.getName()).ifPresent(userFound::setName);

        User userUpdated = userRepository.save(userFound);

        return this.userMapper.toDto(userUpdated);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


    @Override
    public void delete(Long id) {
        if (id != null) {
            userRepository.deleteById(id);
        }
    }
}
