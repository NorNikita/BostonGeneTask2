package com.bostongene.nikita.api.users;

import com.bostongene.nikita.exceptions.ApiErrorContainer;
import com.bostongene.nikita.exceptions.ApiException;
import com.bostongene.nikita.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Level;

@RestController
@Log
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable @NotNull Long id) throws ApiException {
        return userService.getUser(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody @Valid UserBodyContainer userBodyContainer) throws ApiException {
        return userService.createUser(userBodyContainer);
    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid UserBodyContainer userBodyContainer) throws ApiException {
        return userService.updateUser(id, userBodyContainer);
    }

    @DeleteMapping("/{id}")
    public RemoveResponseStatus removeUser(@PathVariable Long id) throws ApiException {
        return userService.removeUser(id);
    }


    @ExceptionHandler({ ApiException.class })
    public ResponseEntity handleException(ApiException ex){
        log.log(Level.INFO, ex.getMessage());
        return ResponseEntity
                .status(ex.getType().getHttpStatus())
                .body(new ApiErrorContainer(ex.getType(), ex.getMessage()));
    }
}
