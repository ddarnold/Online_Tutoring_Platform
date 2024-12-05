package de.thu.thutorium.database.dbObjects;

import de.thu.thutorium.database.dbObjects.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a role in the database.
 * Contains details about the role, including its name and associated users.
 */
@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
public class RoleDBO {

    /**
     * Unique identifier for the role.
     * Auto-generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long role_id;

    /**
     * Name of the role.
     * Stored as an enumerated type.
     */
    @Enumerated(EnumType.STRING)
    @Column()
    private Role role;

    /**
     * Set of users associated with the role.
     * Mapped by the roles attribute in the UserDBO entity.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<UserDBO> users = new HashSet<>();

    /**
     * Constructs a RoleDBO with an empty set of users.
     */
    public RoleDBO() {
        this.users = new HashSet<>();
    }

    /**
     * Constructs a RoleDBO with the specified role name.
     * @param role the name of the role
     */
    public RoleDBO(Role role) {
        this.role = role;
    }
}