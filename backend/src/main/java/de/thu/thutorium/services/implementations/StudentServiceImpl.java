package de.thu.thutorium.services.implementations;

import de.thu.thutorium.services.interfaces.StudentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link StudentService} interface for managing student related requests.
 * <p>
 * This calss provides the actual implementation logic for the operations defined in the 
 * {@link StudentService}. It interacts with the database through repositories
 * to perfome the intended operations.
 */
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    /**
     * Enrolls students in a course
     * 
     * <P>
     * This method checks if the requested {@link de.thu.thutorium.api.transferObjects.common.UserTO} student and
     * the {@link de.thu.thutorium.api.transferObjects.common.CourseTO} course exist in the database.
     * If both entities exist, it updates the relationship between the student and the course in the
     * students_courses table. If either entity does not exist, an appropriate exception is thrown.
     * @param studentId, the ID of the student to enrol
     * @param courseId, the ID of the course to get enrolled in.
     * @throws IllegalArgumentException, if the student or course do not exist.
     */
    @Override
    @Transactional
    public void enrollStudentInCourse(int studentId, int courseId) {

    }
}
