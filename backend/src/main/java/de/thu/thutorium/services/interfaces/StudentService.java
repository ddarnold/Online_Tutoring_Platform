package de.thu.thutorium.services.interfaces;

import org.springframework.stereotype.Service;

/**
 * Service interface for managing student-related operations
 *<p>
 *     This interface defines the contract for various operations related to the
 *     {@link de.thu.thutorium.api.transferObjects.common.UserTO} student.
 *     Implementations of this interface in
 *     {@link  de.thu.thutorium.services.implementations.StudentServiceImpl} provide the actual logic.
 *</p>
 */
@Service
public interface StudentService {

    /**
     * Enrolls student in a course
     * <p>
     *     The function checks if the provided student and courses exist in the database.
     *     If they exist, the relationship is resolved in the courses_students table.
     *     If either of the entities do not exist, an appropriate exception is thrown.
     * </p>
     * @param studentId, the ID of the student to be enrolled.
     * @param courseId, the ID of the course to enrol in.
     * @throws IllegalArgumentException if either of the entities do not exist
     */
    void enrollStudentInCourse(int studentId, int courseId);
}
