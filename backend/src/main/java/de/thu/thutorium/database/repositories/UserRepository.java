package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.RoleDBO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for UserDBO entities.
 * Provides methods for performing CRUD operations on user data.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDBO, Integer> {
    /**
     * Finds a user entity by its username; in this case, email, primarily for authentication purposes.
     * see {@link de.thu.thutorium.security.AuthConfig} and
     * {@link de.thu.thutorium.services.implementations.AuthenticationServiceImpl}
     *
     * @param email the email of the user
     * @return an Optional {@link UserDBO} containing the found UserDBO, or empty if not found
     */
    Optional<UserDBO> findByEmail(String email);

    /**
     * Finds a user by their email and roles, see {@link de.thu.thutorium.services.implementations.AuthenticationServiceImpl}
     *
     * @param email the email of the user
     * @param roles the roles assigned to the user
     * @return the {@link @UserDBO} with the specified email and roles, or
     * {@code null} if no such user exists
     */
    UserDBO findByEmailAndRoles(String email, Set<RoleDBO> roles);

}
