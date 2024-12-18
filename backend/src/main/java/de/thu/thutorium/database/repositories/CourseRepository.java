package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.CourseDBO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link CourseDBO} entities. This interface extends {@link
 * JpaRepository} and provides custom query methods to interact with the underlying database,
 * particularly for finding courses.
 */
public interface CourseRepository extends JpaRepository<CourseDBO, Long> {

}
