package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.common.UserTO;
import de.thu.thutorium.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Updates an existing user.
     *
     *
     * @param id the id of the user to be updated
     * @param userTO {@code UserTO},  contains the information to be updated.
     * @return a {@code ResponseEntity} containing the updated {@code UserTO}
     * object and a {@link org.springframework.http.HttpStatus#OK} status
     * The status is set to HttpStatus.NOT_FOUND, if the update was unsuccessful.
     */
    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserTO> updateUser(@PathVariable int id, @RequestBody UserTO userTO) {
        Optional<UserTO> updatedUserOptional = userService.updateUser(id, userTO);
        return updatedUserOptional
                .map(updatedUser -> ResponseEntity.ok().body(updatedUser))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a user by theirID.
     * * <p>
     * This endpoint deletes a user with the specified ID. If the user is successfully deleted,
     * a 204 No Content response is returned. If no user with the given ID is found,
     * a 404 Not Found response is returned.
     * </p>
     * @param id, the ID of the user to be deleted.
     * @return a {@link ResponseEntity} with teh appropriate HTTP status code.
     */
    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
