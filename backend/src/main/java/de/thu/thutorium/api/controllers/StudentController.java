package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.common.RatingCourseTO;
import de.thu.thutorium.api.transferObjects.common.RatingTutorTO;
import de.thu.thutorium.services.implementations.UserServiceImpl;
import de.thu.thutorium.services.interfaces.CourseService;
import de.thu.thutorium.swagger.CommonApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Controller for managing user operations. */
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@Slf4j
@CommonApiResponses
public class StudentController {

    private final UserServiceImpl studentService;
    private final CourseService courseService;

    /**
     * Retrieves a course based on its ID.
     *
     * @param studentId The ID of the student who wants to enroll in a course.
     * @param courseId The ID of the course in which the student wants to enroll.
     * @return suitable HTTP response upon successful enrolment.
     * @throws EntityNotFoundException, if the searched student/course does not exist in the database.
     */
    @Operation(
            summary = "Student enrolls in a course. ",
            description =
                    "A student can enroll in a course, provided it exists already in the database",
            tags = {"Student Endpoints"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully enrolled."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student or course not found.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/enroll-course")
    public ResponseEntity<?> enrollInCourse(@Parameter(
            name = "student ID",
            description = "The ID of the student enrolling in the course",
            required = true
    ) @RequestParam Long studentId,
    @Parameter(
            name = "course ID",
            description = "The ID of the course into which the student enrolls.",
            required = true
    ) @RequestParam Long courseId) {
        try {
            studentService.enrollCourse(studentId, courseId);
            return ResponseEntity.status(HttpStatus.OK).body("Enrolled successfully in the course");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }

    /**
     * Provide a course rating based on its ID.
     *
     * @param ratingCourseTO the {@link RatingCourseTO} which contains the details of the rating and review from the user.
     * @return suitable HTTP response upon successful rating.
     * @throws EntityNotFoundException, if the searched student/course does not exist in the database.
     */
    @Operation(
            summary = "Student rates a course in which they are enrolled. ",
            description =
                    "A student can rate a course, provided it exists already in the database and the student "
                            + "is already enrolled in it.",
            tags = {"Student Endpoints"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course successfully rated."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student or course not found.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/rate-course")
    public ResponseEntity<?> rateCourse(@Valid @RequestBody RatingCourseTO ratingCourseTO) {
        try {
            courseService.rateCourse(ratingCourseTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }

    /**
     * Provide a tutor rating based on its ID.
     *
     * @param ratingTutorTO the {@link RatingTutorTO} which contains the details of the tutor and review from the student.
     * Only a student can rate a tutor.
     * @return suitable HTTP response upon successful rating.
     * @throws EntityNotFoundException, if the searched student/tutor does not exist in the database.
     */
    @Operation(
            summary = "Student rates a tutor. ",
            description =
                    "A student can rate a tutor, provided they exists already in the database and the student "
                            + "has enrolled in at least one of the courses offered by the tutor.",
            tags = {"Student Endpoints"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tutor successfully rated."),
            @ApiResponse(
                    responseCode = "404",
                    description = "Student or tutor not found.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class)
                    )
            )
    })
    @PostMapping("/rate-tutor")
    public ResponseEntity<?> rateTutor(@Valid @RequestBody RatingTutorTO ratingTutorTO) {
        try {
            studentService.rateTutor(ratingTutorTO);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + ex.getMessage());
        }
    }
}
