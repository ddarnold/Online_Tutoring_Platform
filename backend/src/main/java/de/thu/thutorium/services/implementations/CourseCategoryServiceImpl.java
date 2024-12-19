package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.TOMappers.common.CourseCategoryTOMapper;
import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.database.DBOMappers.common.CourseCategoryDBOMapper;
import de.thu.thutorium.database.dbObjects.CourseCategoryDBO;
import de.thu.thutorium.database.exceptions.ResourceAlreadyExistsException;
import de.thu.thutorium.database.repositories.CourseCategoryRepository;
import de.thu.thutorium.services.interfaces.CourseCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final CourseCategoryDBOMapper courseCategoryDBOMapper;
    private final CourseCategoryTOMapper courseCategoryTOMapper;

    /**
     * Creates a new course category.
     *
     * @param courseCategory the data transfer object containing the details of
     *                         the course category to be created
     * @return the created course category as a {@link CourseCategoryTO}
     */
    @Override
    public CourseCategoryTO createCourseCategory(@Valid CourseCategoryTO courseCategory) {
        //Check if the courseCategoryDBO already exists (by name)
        //If yes, Throw ResourceAlreadyExistsException
        //Save to DB

        CourseCategoryDBO categoryDBO = courseCategoryRepository.findCourseCategoryDBOByCategoryName(courseCategory.getCategoryName());
        if (categoryDBO != null) {
            throw new ResourceAlreadyExistsException(courseCategory + " already exists!");
        }
        categoryDBO = courseCategoryDBOMapper.toDBO(courseCategory);
        CourseCategoryDBO savedCategoryDBO = courseCategoryRepository.save(categoryDBO);
        return courseCategoryTOMapper.toDTO(savedCategoryDBO);
    }

    /**
     * Updates an existing course category.
     *
     * @param categoryId       the ID of the course category to be updated
     * @param courseCategory the data transfer object containing the updated
     *                         details of the course category
     * @return an {@link Optional} containing the updated course category as a
     *         {@link CourseCategoryTO}, or empty if not found
     */
    @Override
    public Optional<CourseCategoryTO> updateCourseCategory(int categoryId, CourseCategoryTO courseCategory) {
        //Check if the courseCategory exists => throw Not found exception
        //Update and return the courseCategoryTO
        return Optional.empty();
    }

    /**
     * Deletes a course category by its ID.
     *
     * @param categoryId the ID of the course category to be deleted
     */
    @Override
    public void deleteCourseCategory(int categoryId) {
        //Check if the courseCategory exists => throw Not found exception
        //Delete the courseCategoryDBO => Check for side effects on courses, other possible side effects
    }

}
