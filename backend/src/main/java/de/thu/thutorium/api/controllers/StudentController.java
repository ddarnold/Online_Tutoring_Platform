package de.thu.thutorium.api.controllers;

import de.thu.thutorium.exceptions.ResourceNotFoundException;
import de.thu.thutorium.exceptions.SpringErrorPayload;
import de.thu.thutorium.services.implementations.UserServiceImpl;
import de.thu.thutorium.swagger.CommonApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
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

    /**
     * Retrieves a course based on its ID.
     *
     * @param studentId The ID of the student who wants to enroll in a course.
     * @param courseId The ID of the course in which the student wants to enroll.
     * @return suitable HTTP response upon successful enrolment.
     * @throws ResourceNotFoundException, if the searched student/course does not exist in the database.
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
}
