package de.thu.thutorium.api.transferObjects;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) that represents the base information of a user in the system.
 *
 * <p>This class is used to transfer common user-related data between layers of the application. It
 * contains fields that are shared among all types of users, such as students and tutors. Specific
 * user types may extend or customize this DTO for additional fields.
 */
@Data
public class UserBaseDTO {
  private Long userId;
  private String firstName;
  private String lastName;
  private String email;
  private Boolean isVerified;
  private Boolean enabled;
  private LocalDateTime createdAt;
}
