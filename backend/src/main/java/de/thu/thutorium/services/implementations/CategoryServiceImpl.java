package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.TOMappers.CourseCategoryTOMapper;
import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.database.DBOMappers.CourseCategoryDBOMapper;
import de.thu.thutorium.database.dbObjects.CourseCategoryDBO;
import de.thu.thutorium.database.repositories.CategoryRepository;
import de.thu.thutorium.exceptions.ResourceAlreadyExistsException;
import de.thu.thutorium.services.interfaces.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing course categories.
 *
 * <p>This service provides functionality to create new course categories and interact with the
 * underlying data store. It interacts with the {@link CategoryRepository} to perform database
 * operations and uses the {@link CourseCategoryTOMapper} to map between the data transfer objects
 * (DTO) and the database objects (DBO).
 *
 * <p>This class provides a method to create a new course category, including setting the creation
 * timestamp.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository courseCategoryRepository;
  private final CourseCategoryDBOMapper courseCategoryDBOMapper;
  private final CourseCategoryTOMapper courseCategoryTOMapper;

  public CategoryServiceImpl(
      CategoryRepository courseCategoryRepository,
      CourseCategoryDBOMapper courseCategoryDBOMapper,
      CourseCategoryTOMapper courseCategoryTOMapper) {
    this.courseCategoryRepository = courseCategoryRepository;
    this.courseCategoryDBOMapper = courseCategoryDBOMapper;
    this.courseCategoryTOMapper = courseCategoryTOMapper;
  }

  /**
   * Creates a new course category.
   *
   * @param courseCategory the data transfer object containing the details of the course category to
   *     be created
   * @return the created course category as a {@link CourseCategoryTO}
   */
  @Override
  public CourseCategoryTO createCourseCategory(@Valid CourseCategoryTO courseCategory) {
    // Check if the courseCategoryDBO already exists (by name)
    // If yes, Throw ResourceAlreadyExistsException
    // Save to DB

    Optional<CourseCategoryDBO> categoryDBOOptional =
        Optional.ofNullable(
            courseCategoryRepository.findCourseCategoryDBOByCategoryName(
                courseCategory.getCategoryName()));
    categoryDBOOptional.ifPresent(
        (category) -> {
          throw new ResourceAlreadyExistsException(
              "Category \"" + category.getCategoryName() + "\" already exists!");
        });
    CourseCategoryDBO categoryDBO = courseCategoryDBOMapper.toDBO(courseCategory);
    CourseCategoryDBO savedCategoryDBO = courseCategoryRepository.save(categoryDBO);
    return courseCategoryTOMapper.toDTO(savedCategoryDBO);
  }

  /**
   * Updates an existing course category.
   *
   * @param categoryId the ID of the course category to be updated
   * @param courseCategory the data transfer object containing the updated details of the course
   *     category
   * @return an {@link Optional} containing the updated course category as a {@link
   *     CourseCategoryTO}, or empty if not found
   */
  @Override
  public Optional<CourseCategoryTO> updateCourseCategory(
      int categoryId, CourseCategoryTO courseCategory) {
    // Check if the courseCategory exists => throw Not found exception
    // Update and return the courseCategoryTO
    return Optional.empty();
  }

  /**
   * Deletes a course category by its ID.
   *
   * @param categoryId the ID of the course category to be deleted
   */
  @Override
  public void deleteCourseCategory(int categoryId) {
    // Check if the courseCategory exists => throw Not found exception
    // Delete the courseCategoryDBO => Check for side effects on courses, other possible side
    // effects
  }
}
