package de.thu.thutorium.service;

import de.thu.thutorium.model.Course;
import de.thu.thutorium.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Service class responsible for handling operations related to courses. */
@Service
public class CourseService {

  @Autowired private CourseRepository courseRepository;

  /**
   * Finds courses by tutor's first and last name.
   *
   * @param firstName The tutor's first name.
   * @param lastName The tutor's last name.
   * @return A list of {@link Course} objects taught by the tutor with the provided name.
   */
  public List<Course> findCoursesByTutorName(String firstName, String lastName) {
    return courseRepository.findByTutorFirstNameAndLastName(firstName, lastName);
  }

  /**
   * Finds courses by tutor's full name.
   *
   * @param tutorName The full name of the tutor (e.g., "John Doe").
   * @return A list of {@link Course} objects taught by the tutor with the provided full name.
   */
  public List<Course> findCoursesByFullTutorName(String tutorName) {
    return courseRepository.findByTutorFullName(tutorName);
  }

  /**
   * Finds courses by a partial match on the course name.
   *
   * @param name The partial name of the course.
   * @return A list of {@link Course} objects that match the specified name.
   */
  public List<Course> findCoursesByName(String name) {
    return courseRepository.findByCourseName(name);
  }
}
