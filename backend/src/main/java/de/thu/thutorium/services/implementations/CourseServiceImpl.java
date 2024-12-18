package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.services.interfaces.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link CourseService} interface that provides various methods for
 * retrieving courses based on different search criteria and getting overall course statistics.
 *
 * <p>This service interacts with the {@link de.thu.thutorium.database.repositories.CourseRepository} to fetch course data from the
 * database and uses {@link de.thu.thutorium.api.TOMappers.common.CourseTOMapper} to convert the
 * {@link de.thu.thutorium.database.dbObjects.CourseDBO} (database object) into
 * {@link de.thu.thutorium.api.transferObjects.common.CourseTO} (data transfer object)
 * for use in the application.
 * Todo: Implement functions
 */
@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    @Override
    public CourseTO createCourse(CourseTO course) {
        return null;
    }

    @Override
    public Optional<CourseTO> findByCourseId(int courseId) {
        return Optional.empty();
    }

    @Override
    public List<CourseTO> findAllCourses() {
        return List.of();
    }

    @Override
    public List<CourseTO> findByCourseName(String courseName) {
        return List.of();
    }

    @Override
    public List<CourseTO> findByCourseCategory(String category) {
        return List.of();
    }

    @Override
    public List<CourseTO> findByTutor(int tutorID) {
        return List.of();
    }

    @Override
    public Long getCourseCount() {
        return 0L;
    }

    @Override
    public Optional<CourseTO> updateCourse(int courseId, CourseTO course) {
        return Optional.empty();
    }

    @Override
    public void deleteCourse(int courseId) {

    }
}
