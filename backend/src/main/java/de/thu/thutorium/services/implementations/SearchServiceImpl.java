package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.TOMappers.CourseCategoryTOMapper;
import de.thu.thutorium.api.TOMappers.CourseTOMapper;
import de.thu.thutorium.api.TOMappers.TutorTOMapper;
import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.api.transferObjects.common.TutorTO;
import de.thu.thutorium.database.dbObjects.*;
import de.thu.thutorium.database.repositories.CategoryRepository;
import de.thu.thutorium.database.repositories.CourseRepository;
import de.thu.thutorium.database.repositories.UserRepository;
import de.thu.thutorium.services.interfaces.SearchService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link SearchService} interface that provides methods for searching tutors,
 * courses, and retrieving all available categories.
 *
 * <p>This service interacts with repositories to fetch relevant data and uses mappers to convert
 * database objects into transfer objects for further use in the application.
 */
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
  private final CourseRepository courseRepository;
  private final CourseTOMapper courseTOMapper;
  private final UserRepository userRepository;
  private final TutorTOMapper tutorTOMapper;
  private final CategoryRepository categoryRepository;
  private final CourseCategoryTOMapper courseCategoryTOMapper;



  /**
   * Constructor for initializing the service with necessary dependencies.
   *
   * @param courseRepository the repository for handling courses
   * @param courseMapper the mapper to convert {@link CourseDBO} to {@link CourseTO}
   * @param userRepository the repository for handling users (tutors)
   * @param tutorMapper the mapper to convert {@link UserDBO} to {@link TutorTO}
   * @param categoryRepository the repository for handling course categories
   * @param courseCategoryTOMapper the mapper to convert {@link CourseCategoryDBO} to {@link
   *     CourseCategoryTO}
   */

  /**
   * Searches for tutors based on their full name.
   *
   * <p>This method fetches the list of tutors whose full name matches the given {@code tutorName}.
   * The search may support partial matches depending on the implementation. The result is mapped
   * into a list of {@link de.thu.thutorium.api.transferObjects.common.UserTO} objects.
   *
   * @param tutorName the full name of the tutor (can be partial).
   * @return a list of {@link de.thu.thutorium.api.transferObjects.common.UserTO} objects
   *     representing the tutors that match the search criteria. If no tutors are found, an empty
   *     list is returned.
   */
  @Override
  public List<TutorTO> searchTutors(String tutorName) {
    List<UserDBO> tutors = userRepository.findByTutorFullName(tutorName);
    if (tutors.isEmpty()) {
      throw new EntityNotFoundException("Tutors not found");
    } else {
      return tutors.stream().map(tutorTOMapper::toDTO).toList();
    }

//    return tutors.stream().map(this::mapWithAverageTutorRating).toList();
  }

  /**
   * Maps a {@link UserDBO} (tutor) entity to a {@link TutorTO} transfer object, including the
   * average rating of the tutor.
   *
   * @param tutor the {@link UserDBO} object representing the tutor
   * @return a {@link TutorTO} object with the mapped data and the average rating
   */
  @Deprecated
  private TutorTO mapWithAverageTutorRating(UserDBO tutor) {
    TutorTO tutorTO = tutorTOMapper.toDTO(tutor);
    // Calculate the average rating
    if (tutor.getReceivedTutorRatings() != null && !tutor.getReceivedTutorRatings().isEmpty()) {
      tutorTO.setAverageRating(
          tutor.getReceivedTutorRatings().stream()
              .mapToDouble(RatingTutorDBO::getPoints)
              .average()
              .orElse(0.0));
    } else {
      tutorTO.setAverageRating(0.0); // Default to 0 if no ratings
    }
    return tutorTO;
  }



  /**
   * Maps a {@link CourseDBO} entity to a {@link CourseTO} transfer object, including the average
   * rating of the course.
   *
   * @param course the {@link CourseDBO} object representing the course
   * @return a {@link CourseTO} object with the mapped data and the average rating
   */
  @Deprecated
  private CourseTO mapWithAverageRating(CourseDBO course) {
    CourseTO courseTO = courseTOMapper.toDTO(course);
    // Calculate the average rating
    if (course.getReceivedCourseRatings() != null && !course.getReceivedCourseRatings().isEmpty()) {
      courseTO.setAverageRating(
          course.getReceivedCourseRatings().stream()
              .mapToDouble(RatingCourseDBO::getPoints)
              .average()
              .orElse(0.0));
    } else {
      courseTO.setAverageRating(0.0); // Default to 0 if no ratings
    }
    return courseTO;
  }

  /**
   * Retrieves all available course categories.
   *
   * <p>This method fetches the list of all course categories from the {@link CategoryRepository}.
   * The result is a list of {@link CourseCategoryTO} objects representing the available categories.
   *
   * @return a list of {@link CourseCategoryTO} objects representing all available categories. If no
   *     categories are found, an empty list is returned.
   */
  public List<CourseCategoryTO> getAllCategories() {
    // Use repository's built-in `findAll` and map results to TOs
    return categoryRepository.findAll().stream()
        .map(courseCategoryTOMapper::toDTO)
        .collect(Collectors.toList());
  }
}
