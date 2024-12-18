package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.RatingCourseDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link RatingCourseDBO} entities.
 */
@Repository
public interface RatingCourseRepository extends JpaRepository<RatingCourseDBO, Long> { }
