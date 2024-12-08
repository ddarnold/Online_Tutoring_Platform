package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.UserDBO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link UserDBO} entities.
 *
 * <p>This interface provides methods for interacting with the user data stored in the database,
 * including custom queries to count users based on their roles.
 */
@Repository
public interface UserRepository extends JpaRepository<UserDBO, Long> {

  /**
   * Counts the number of users with a specific role.
   *
   * @param roleName the name of the role (e.g., STUDENT, TUTOR)
   * @return the total count of users with the specified role
   */
  @Query("SELECT COUNT(u) FROM UserDBO u JOIN u.roles r WHERE r.role = :roleName")
  Long countByRole(@Param("roleName") String roleName);

  /**
   * Finds tutors based on their full name or a partial match of their name.
   *
   * <p>This query retrieves all users with the role of "TUTOR" whose full name matches the given
   * search string, regardless of the order of the first and last name. The search is
   * case-insensitive and supports partial matches.
   *
   * @param tutorName The search string to match against the tutor's full name. This parameter is
   *     compared case-insensitively and supports partial matching.
   * @return A list of {@link UserDBO} objects representing tutors whose full names match the search
   *     string.
   */
  @Query(
          "SELECT u FROM UserDBO u JOIN u.roles r WHERE r.role = 'TUTOR' AND "
                  + "(LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :tutorName, '%')) OR "
                  + "LOWER(CONCAT(u.lastName, ' ', u.firstName)) LIKE LOWER(CONCAT('%', :tutorName, '%')))")
  List<UserDBO> findByTutorFullName(@Param("tutorName") String tutorName);

  /**
   * Retrieves a {@link UserDBO} entity by its unique identifier.
   *
   * @param userId The unique identifier of the user to be retrieved.
   * @return The {@link UserDBO} entity with the specified user ID, or {@code null} if no user is
   *     found.
   */
  @Query("SELECT u FROM UserDBO u WHERE u.userId = :userId")
  UserDBO findByUserId(@Param("userId") Long userId);

  /**
   * Retrieves a {@link UserDBO} entity with the role of "TUTOR" based on the specified user ID.
   *
   * @param userId the unique identifier of the tutor to be retrieved.
   * @return the {@link UserDBO} entity with the specified user ID and the role of "TUTOR", or
   *     {@code null} if no matching tutor is found.
   */
  @Query("SELECT u FROM UserDBO u JOIN u.roles r WHERE u.userId = :userId AND r.role = 'TUTOR'")
  UserDBO findByTutorId(@Param("userId") Long userId);
}
