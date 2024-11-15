package de.thu.thutorium.repository;

import de.thu.thutorium.model.User;
import de.thu.thutorium.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link User} entities.
 *
 * <p>This interface provides methods for interacting with the user data stored in the database,
 * including custom queries to count users based on their roles.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Counts the number of users with a specific role.
   *
   * @param role the {@link UserRole} to count (e.g., STUDENT, TUTOR)
   * @return the total count of users with the specified role
   * @apiNote This method uses a custom JPQL query to count users by their role.
   * @example countByRole(UserRole.STUDENT) // returns 42
   */
  @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
  Long countByRole(UserRole role);

  /**
   * Finds tutors based on their full name or a partial match of their name.
   *
   * <p>This query retrieves all users with the role of "TUTOR" whose full name matches the given
   * search string, regardless of the order of the first and last name. The search is
   * case-insensitive and supports partial matches.
   *
   * <p>For example, a search for "charlie brown" will match both "Charlie Brown" and "Brown
   * Charlie".
   *
   * @param tutorName The search string to match against the tutor's full name. This parameter is
   *     compared case-insensitively and supports partial matching.
   * @return A list of {@link User} objects representing tutors whose full names match the search
   *     string.
   */
  @Query(
      "SELECT u FROM User u WHERE u.role = 'TUTOR' "
          + "AND (LOWER(CONCAT(u.firstName, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :tutorName, '%')) "
          + "OR LOWER(CONCAT(u.lastName, ' ', u.firstName)) LIKE LOWER(CONCAT('%', :tutorName, '%')))")
  List<User> findByTutorFullName(@Param("tutorName") String tutorName);
}
