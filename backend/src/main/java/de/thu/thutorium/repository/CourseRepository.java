package de.thu.thutorium.repository;

import de.thu.thutorium.model.Course;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for managing {@link Course} entities. This interface extends {@link
 * JpaRepository} and provides custom query methods to interact with the underlying database,
 * particularly for finding courses based on the tutor's name.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

  /**
   * Finds a list of courses based on the tutor's first and last names. This method uses a custom
   * JPQL query to search for courses where the tutor's first and last name match the provided
   * parameters. The search is case-insensitive and supports partial matches for both first and last
   * names.
   *
   * @param firstName The tutor's first name.
   * @param lastName The tutor's last name.
   * @return A list of {@link Course} objects taught by the tutor with the provided name. If no
   *     courses are found, an empty list is returned.
   */
  @Query(
      "SELECT c FROM Course c WHERE LOWER(c.tutor.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) AND LOWER(c.tutor.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
  List<Course> findByTutorFirstNameAndLastName(
      @Param("firstName") String firstName, @Param("lastName") String lastName);
}
