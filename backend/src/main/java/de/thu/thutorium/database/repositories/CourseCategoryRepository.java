package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.CourseCategoryDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations on {@link CourseCategoryDBO} entities.
 *
 * <p>This interface extends {@link JpaRepository}, which provides various methods for interacting
 * with the database, such as saving, finding, deleting, and updating entities. Additionally, custom
 * query methods can be defined to suit specific requirements.
 *
 * <p>By using {@code @Repository}, Spring treats this interface as a Data Access Object (DAO),
 * enabling exception translation into Spring's DataAccessException hierarchy.
 */
@Repository
public interface CourseCategoryRepository extends JpaRepository<CourseCategoryDBO, Long> {
}
