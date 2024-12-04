package de.thu.thutorium.api.transferObjects;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Base Data Transfer Object for User entity.
 *
 * <p>This base DTO holds fields common to all user types.
 */
@Data
public class UserBaseDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String description;
    private Boolean isVerified;
    private Boolean enabled;
    private LocalDateTime verifiedOn;

    /** The roles assigned to the user. */
//    private Set<String> roles;
}