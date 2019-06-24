package com.bostongene.nikita.api.users;

import com.bostongene.nikita.exceptions.ApiEmailExistException;
import com.bostongene.nikita.exceptions.ApiException;
import com.bostongene.nikita.exceptions.ApiUserNotFoundException;
import com.bostongene.nikita.model.User;
import com.bostongene.nikita.model.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;

@Service
@Log
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(UserBodyContainer userBodyContainer) throws ApiException {
        log.log(Level.INFO, String.format("Creating user: %s", userBodyContainer.getEmail()));
        checkUserEmailExists(userBodyContainer.getEmail());

        User user = new User(
                userBodyContainer.getEmail(),
                userBodyContainer.getFirstName(),
                userBodyContainer.getLastName(),
                passwordEncoder.encode(userBodyContainer.getPassword()),
                userBodyContainer.getBirthday());

        user = userRepository.save(user);
        log.log(Level.INFO, String.format("User created: %s, %s", user.getId(), user.getEmail()));

        return user;
    }

    public User getUser(Long id) throws ApiException {
        log.log(Level.INFO, String.format("Getting user: %s", id));
        return findUserOrThrowException(id);
    }

    public User updateUser(Long id, UserBodyContainer userBodyContainer) throws ApiException {
        log.log(Level.INFO, String.format("Updating user: %s", id));

        User user = findUserOrThrowException(id);

        if (!userBodyContainer.getEmail().equals(user.getEmail()))
            checkUserEmailExists(userBodyContainer.getEmail());

        user.setEmail(userBodyContainer.getEmail());
        user.setFirstName(userBodyContainer.getFirstName());
        user.setLastName(userBodyContainer.getLastName());
        user.setBirthday(userBodyContainer.getBirthday());
        user.setPasswordEnc(passwordEncoder.encode(userBodyContainer.getPassword()));

        user = userRepository.save(user);
        return user;
    }

    public RemoveResponseStatus removeUser(Long id) throws ApiException {
        log.log(Level.INFO, String.format("Removing user: %s", id));
        User user = findUserOrThrowException(id);
        userRepository.delete(user);

        return new RemoveResponseStatus(id);
    }


    private void checkUserEmailExists(String email) throws ApiException {
        if (userRepository.existsByEmail(email)) {
            throw new ApiEmailExistException(String.format("User with this email exists: %s", email));
        }
    }

    private User findUserOrThrowException(Long id) throws ApiException {
        Optional<User> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new ApiUserNotFoundException(String.format("User with id=%s does not exist", id));
        }
        return userOpt.get();
    }
}
