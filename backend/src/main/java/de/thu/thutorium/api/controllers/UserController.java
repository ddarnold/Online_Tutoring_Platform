package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.common.UserTO;
import de.thu.thutorium.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserTO> updateUser(@PathVariable int id, @RequestBody UserTO userTO) {
        Optional<UserTO> updatedUserOptional = userService.updateUser(id, userTO);
        return updatedUserOptional
                .map(updatedUser -> ResponseEntity.ok().body(updatedUser))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
