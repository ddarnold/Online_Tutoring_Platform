package de.thu.thutorium.database.dbObjects;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

/**
 * Represents a role that a user can have in the system.
 *
 * <p>This entity class is mapped to the {@code role} table in the database and contains information
 * about the role's ID, name, and the users associated with the role. The {@code RoleDBO} is used to
 * define user roles and maintain the many-to-many relationship between users and roles.
 */
@Entity
@Table(name = "role")
@Data
public class RoleDBO {
  /**
   * The unique identifier for the role.
   *
   * <p>This field is the primary key of the {@code role} table and is automatically generated when
   * a new role is created.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The name of the role.
   *
   * <p>This field represents the role name (e.g., "STUDENT", "TUTOR") and is unique across the
   * {@code role} table. It cannot be {@code null}.
   */
  @Column(name = "role_name", unique = true, nullable = false)
  private String name;

  /**
   * The set of users associated with this role.
   *
   * <p>This is a many-to-many relationship between the {@code RoleDBO} and {@code UserDBO}. A role
   * can be assigned to multiple users, and a user can have multiple roles. The {@code mappedBy}
   * attribute indicates that the mapping is defined in the {@code UserDBO} class, where the {@code
   * roles} field is declared.
   */
  @ManyToMany(mappedBy = "roles")
  private Set<UserDBO> users;
}
