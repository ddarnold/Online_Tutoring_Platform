package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing course categories.
 * <p>
 * This interface defines the contract for various operations related to course
 * categories,
 * such as creating, finding, updating, and deleting course categories.
 * Implementations of this interface should provide the actual logic for these
 * operations.
 * </p>
 */
@Service
public interface CourseCategoryService {

    /**
     * Creates a new course category.
     *
     * @param courseCategoryTO the data transfer object containing the details of
     *                         the course category to be created
     * @return the created course category as a {@link CourseCategoryTO}
     */
    CourseCategoryTO createCourseCategory(CourseCategoryTO courseCategoryTO);

    /**
     * Finds a course category by its ID.
     *
     * @param categoryId the ID of the course category to be found
     * @return an {@link Optional} containing the found course category as a
     *         {@link CourseCategoryTO}, or empty if not found
     */
    Optional<CourseCategoryTO> findByCourseCategoryId(int categoryId);

    /**
     * Finds all course categories.
     *
     * @return a list of all course categories as {@link CourseCategoryTO}
     */
    List<CourseCategoryTO> findAllCategories();

    /**
     * Updates an existing course category.
     *
     * @param categoryId       the ID of the course category to be updated
     * @param courseCategoryTO the data transfer object containing the updated
     *                         details of the course category
     * @return an {@link Optional} containing the updated course category as a
     *         {@link CourseCategoryTO}, or empty if not found
     */
    Optional<CourseCategoryTO> updateCourseCategory(int categoryId, CourseCategoryTO courseCategoryTO);

    /**
     * Deletes a course category by its ID.
     *
     * @param categoryId the ID of the course category to be deleted
     */
    void deleteCourseCategory(int categoryId);
}
