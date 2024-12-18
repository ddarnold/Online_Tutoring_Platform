package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.api.transferObjects.common.CourseTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing courses.
 * <p>
 * This interface defines the contract for various operations related to courses,
 * such as creating, finding, updating, and deleting courses.
 * Implementations of this interface should provide the actual logic for these
 * operations.
 * </p>
 */
@Service
public interface CourseService {

    /**
     * Creates a new course.
     *
     * @param course the data transfer object containing the details of the course to be created
     * @return the created course category as a {@link CourseTO}
     */
    CourseTO createCourse(CourseTO course);

    /**
     * Finds a course by its ID.
     *
     * @param courseId the ID of the course to be found
     * @return an {@link Optional} containing the found course as a
     *         {@link CourseTO}, or empty if not found
     */
    Optional<CourseTO> findByCourseId(int courseId);

    /**
     * Finds all courses.
     *
     * @return a list of all courses as {@link CourseTO}
     */
    List<CourseTO> findAllCourses();

    /**
     * Finds courses by the given course name.
     *
     * <p>This method will return a list of {@link CourseTO} objects that match the given course name.
     * The search may support case-insensitivity and partial name matches depending on the
     * implementation.
     *
     * @param courseName the name (or partial name) of the course to search for.
     * @return a list of {@link CourseTO} objects representing courses that match the search criteria.
     *     If no courses are found, an empty list is returned.
     */
    List<CourseTO> findByCourseName(String courseName);

    /**
     * Retrieves courses that belong to a specific category.
     *
     * <p>This method returns a list of {@link CourseTO} objects that belong to the given category.
     *
     * @param category the name of the category to search for.
     * @return a list of {@link CourseTO} objects representing courses in the specified category. If
     *     no courses are found for the given category, an empty list is returned.
     */
    List<CourseTO> findByCourseCategory(String category);

    /**
     * Finds courses taught by a tutor specified by an ID.
     *
     * <p>This method will return a list of {@link CourseTO} objects that match the provided tutor's
     * ID.
     *
     * @param tutorID the ID of the tutor.
     * @return a list of {@link CourseTO} objects representing the courses taught by the tutor specified
     * by ID. If no courses are found, an empty list is returned.
     */
    List<CourseTO> findByTutor(int tutorID);

    /**
     * Gets the total count of courses available.
     * <p>This method returns the total number of courses offered.
     *
     * @return the total number of courses as a {@code Long} value.
     */
    Long getCourseCount();

    /**
     * Updates an existing course.
     *
     * @param courseId the ID of the course category to be updated
     * @param course the data transfer object containing the updated
     *                         details of the course
     * @return an {@link Optional} containing the updated course as a
     *         {@link CourseTO}, or empty if not found
     */
    Optional<CourseTO> updateCourse(int courseId, CourseTO course);

    /**
     * Deletes a course by its ID.
     *
     * @param courseId the ID of the course to be deleted
     */
    void deleteCourse(int courseId);
}
