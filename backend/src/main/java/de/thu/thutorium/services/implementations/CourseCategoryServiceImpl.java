package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.database.repositories.CourseCategoryRepository;
import de.thu.thutorium.services.interfaces.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link CourseCategoryService} interface for managing
 * course categories.
 * <p>
 * This class provides the actual logic for the operations defined in the
 * {@link CourseCategoryService} interface,
 * such as creating, finding, updating, and deleting course categories. It
 * interacts with the database through
 * repositories to perform these operations.
 * </p>
 */

/**
 * Todo: Implement functions
 */
@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;

    /**
     * Creates a new course category.
     *
     * @param courseCategoryTO the data transfer object containing the details of
     *                         the course category to be created
     * @return the created course category as a {@link CourseCategoryTO}
     */
    @Override
    public CourseCategoryTO createCourseCategory(CourseCategoryTO courseCategoryTO) {
        return null;
    }

    /**
     * Finds a course category by its ID.
     *
     * @param categoryId the ID of the course category to be found
     * @return an {@link Optional} containing the found course category as a
     *         {@link CourseCategoryTO}, or empty if not found
     */
    @Override
    public Optional<CourseCategoryTO> findByCourseCategoryId(int categoryId) {
        return Optional.empty();
    }

    /**
     * Finds all course categories.
     *
     * @return a list of all course categories as {@link CourseCategoryTO}
     */
    @Override
    public List<CourseCategoryTO> findAllCategories() {
        return List.of();
    }

    /**
     * Updates an existing course category.
     *
     * @param categoryId       the ID of the course category to be updated
     * @param courseCategoryTO the data transfer object containing the updated
     *                         details of the course category
     * @return an {@link Optional} containing the updated course category as a
     *         {@link CourseCategoryTO}, or empty if not found
     */
    @Override
    public Optional<CourseCategoryTO> updateCourseCategory(int categoryId, CourseCategoryTO courseCategoryTO) {
        return Optional.empty();
    }

    /**
     * Deletes a course category by its ID.
     *
     * @param categoryId the ID of the course category to be deleted
     */
    @Override
    public void deleteCourseCategory(int categoryId) {

    }
}
