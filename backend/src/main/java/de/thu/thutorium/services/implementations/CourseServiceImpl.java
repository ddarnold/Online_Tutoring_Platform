package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.frontendMappers.CourseMapper;
import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.database.databaseMappers.CourseDBMapper;
import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import de.thu.thutorium.database.repositories.CourseRepository;
import de.thu.thutorium.database.repositories.UserRepository;
import de.thu.thutorium.services.interfaces.CourseService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementation of the {@link CourseService} interface that provides various methods for
 * retrieving courses based on different search criteria and getting overall course statistics.
 *
 * <p>This service interacts with the {@link CourseRepository} to fetch course data from the
 * database and uses {@link CourseMapper} to convert the {@link CourseDBO} (database object) into
 * {@link CourseTO} (data transfer object) for use in the application.
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private final CourseRepository courseRepository;
  private final CourseDBMapper courseDBMapper;
  private final CourseMapper courseMapper;
  private final UserRepository userRepository;



  /**
   * Finds a course by its unique ID.
   *
   * <p>This method retrieves the course with the given {@code id} from the {@link CourseRepository}
   * and maps it to a {@link CourseTO} object using the {@link CourseMapper}. If no course is found
   * for the provided ID, it returns {@code null}.
   *
   * @param id the unique ID of the course to retrieve.
   * @return a {@link CourseTO} representing the course with the given ID, or {@code null} if no
   *     such course is found.
   */
  @Override
  public CourseTO findCourseById(Long id) {
    CourseDBO course = courseRepository.findCourseById(id);
    return courseMapper.toDTO(course);
  }

  /**
   * Finds courses taught by a tutor with the specified first and last name.
   *
   * <p>This method retrieves a list of courses that are taught by a tutor with the provided first
   * and last name from the {@link CourseRepository}. The result is mapped into a list of {@link
   * CourseTO} objects using the {@link CourseMapper}.
   *
   * @param firstName the first name of the tutor.
   * @param lastName the last name of the tutor.
   * @return a list of {@link CourseTO} objects representing courses taught by the specified tutor.
   *     If no courses are found, an empty list is returned.
   */
  @Override
  public List<CourseTO> findCoursesByTutorName(String firstName, String lastName) {
    List<CourseDBO> courses = courseRepository.findByTutorFirstNameAndLastName(firstName, lastName);
    return courseMapper.toDTOList(courses);
  }

  /**
   * Finds courses taught by a tutor with the specified full name.
   *
   * <p>This method retrieves a list of courses taught by a tutor whose full name matches the given
   * {@code tutorName} from the {@link CourseRepository}. The result is mapped into a list of {@link
   * CourseTO} objects using the {@link CourseMapper}.
   *
   * @param tutorName the full name of the tutor.
   * @return a list of {@link CourseTO} objects representing courses taught by the specified tutor.
   *     If no courses are found, an empty list is returned.
   */
  @Override
  public List<CourseTO> findCoursesByFullTutorName(String tutorName) {
    List<CourseDBO> courses = courseRepository.findByTutorFullName(tutorName);
    return courseMapper.toDTOList(courses);
  }

  /**
   * Finds courses by their name.
   *
   * <p>This method retrieves a list of courses whose names match the given {@code name} from the
   * {@link CourseRepository}. The result is mapped into a list of {@link CourseTO} objects using
   * the {@link CourseMapper}.
   *
   * @param name the name of the course to search for.
   * @return a list of {@link CourseTO} objects representing courses that match the given name. If
   *     no courses are found, an empty list is returned.
   */
  @Override
  public List<CourseTO> findCoursesByName(String name) {
    List<CourseDBO> courses = courseRepository.findCourseByName(name);
    return courseMapper.toDTOList(courses);
  }

  /**
   * Retrieves courses that belong to a specific category.
   *
   * <p>This method retrieves a list of courses that are associated with the specified {@code
   * categoryName}. The result is mapped into a list of {@link CourseTO} objects using the {@link
   * CourseMapper}.
   *
   * @param categoryName the name of the category to search for.
   * @return a list of {@link CourseTO} objects representing courses in the specified category. If
   *     no courses are found, an empty list is returned.
   */
  @Override
  public List<CourseTO> getCoursesByCategory(String categoryName) {
    List<CourseDBO> courses = courseRepository.findCoursesByCategoryName(categoryName);
    return courses.stream().map(courseMapper::toDTO).toList();
  }

  /**
   * Retrieves the total number of courses available.
   *
   * <p>This method returns the total count of courses stored in the database using the {@link
   * CourseRepository}. The result is returned as a {@code Long}.
   *
   * @return the total number of courses available.
   */
  @Override
  public Long getTotalCountOfCourses() {
    return courseRepository.countAllCourses();
  }

  @Override
  @Transactional
  public void createCourse(CourseTO courseTO) {
    // Fetch tutor from the repository using tutorId
    UserDBO tutor =
        userRepository
            .findById(courseTO.getTutorId())
            .orElseThrow(() -> new EntityNotFoundException("Tutor not found"));

    // Create CourseDBO from CourseTO using the mapper
    CourseDBO courseDBO = courseDBMapper.toEntity(courseTO);

    // Set the tutor in the CourseDBO
    courseDBO.setTutor(tutor);

    // Set the createdOn timestamp to the current time
    courseDBO.setCreatedOn(LocalDateTime.now());

    // Save the new course entity to the database
    courseRepository.save(courseDBO);
  }

  @Override
  @Transactional
  public void deleteCourse(Long courseId) {
    if (!courseRepository.existsById(courseId)) {
      throw new EntityNotFoundException("Course not found with ID: " + courseId);
    }
    courseRepository.deleteById(courseId);
  }

  @Override
  public void updateCourse(Long courseId, CourseTO courseTO) {
    CourseDBO existingCourse =
        courseRepository
            .findById(courseId)
            .orElseThrow(
                () -> new EntityNotFoundException("Course not found with ID: " + courseId));

    // Update fields of the existing course
    existingCourse.setCourseName(courseTO.getCourseName());
    existingCourse.setDescriptionShort(courseTO.getDescriptionShort());
    existingCourse.setDescriptionLong(courseTO.getDescriptionLong());
    existingCourse.setStartDate(courseTO.getStartDate());
    existingCourse.setEndDate(courseTO.getEndDate());

    // Update associated tutor (use userRepository to get the tutor)
    UserDBO tutor =
        userRepository
            .findById(courseTO.getTutorId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Tutor not found with ID: " + courseTO.getTutorId()));
    existingCourse.setTutor(tutor);

    // Save the updated course
    courseRepository.save(existingCourse);
  }
}
