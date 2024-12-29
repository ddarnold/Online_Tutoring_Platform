package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.common.AddressTO;
import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.exceptions.ResourceAlreadyExistsException;
import de.thu.thutorium.exceptions.UserNameNotFoundException;
import de.thu.thutorium.services.interfaces.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * AdminController provides REST API endpoints for managing administrative tasks such as creating
 * universities, addresses, and categories, as well as user and chat management.
 *
 * <p>This controller handles requests under the "/admin" path and uses services to perform the
 * necessary operations.
 *
 * <p>Annotations used:
 *
 * <ul>
 *   <li>@RestController: Marks this class as a REST controller.
 *   <li>@RequestMapping("/admin"): Maps all requests starting with "/admin".
 *   <li>@RequiredArgsConstructor: Generates a constructor for final fields.
 *   <li>@Validated: Enables validation on methods.
 * </ul>
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Validated
public class AdminController {

  /** Service for managing address-related operations. */
  private final AddressService addressService;

  /** Service for managing user-related operations. */
  private final UserService userService;

  private final CategoryService categoryService;

  /**
   * Creates a new university and address. This endpoint accepts a {@link AddressTO} object in the
   * request body and creates a new university + address entity. The request body is validated using
   * the {@link Valid} annotation.
   *
   * @param address the {@code AddressTO} object containing the university data
   * @return a {@code ResponseEntity} containing the created {@code AddressTO} object and a {@link
   *     HttpStatus#CREATED} status
   * @throws ResourceAlreadyExistsException, if the university already exists with the provided
   *     address.
   */
  @Operation(
      summary = "Create a university and address object",
      description =
          "Create a university entity together with its address from an Address transfer Object containing"
              + " the university name and an address",
      tags = {"Address and University Endpoints"})
  @ApiResponses(
      @ApiResponse(
          responseCode = "200",
          description = "University and address created successfully.",
          content =
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = AddressTO.class))))
  @PostMapping("address/create-university")
  public ResponseEntity<?> createUniversityAndAddress(@Valid @RequestBody AddressTO address) {
    try {
      AddressTO created = addressService.createUniversityAndAddress(address);
      return ResponseEntity.status(HttpStatus.CREATED).body(created);
    } catch (ResourceAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unexpected error: " + ex.getMessage());
    }
  }

  /**
   * Deletes a user by theirID. *
   *
   * <p>This endpoint deletes a user with the specified ID. If the user is successfully deleted, a
   * 204 No Content response is returned. If no user with the given ID is found, a 404 Not Found
   * response is returned.
   *
   * @param id, the ID of the user to be deleted.
   * @return a {@link ResponseEntity} with the appropriate HTTP status code.
   * @throws UserNameNotFoundException if the use could not be found in the database.
   */
  @Operation(
      summary = "Admin deletes a user account. ",
      description =
          "Allows an admin to delete a user account from the database using the user's ID. "
              + " This operation is irreversible and will permanently remove the user's data.",
      tags = {"User Endpoints"})
  @ApiResponses(
      @ApiResponse(responseCode = "204", description = "User account deleted successfully"))
  @DeleteMapping("/delete-user/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    } catch (UserNameNotFoundException ex) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unexpected error: " + ex.getMessage());
    }
  }

  /**
   * Creates a new course category. This endpoint accepts a {@link CourseCategoryTO} object in the
   * request body and creates a new course category. The request body is validated using the {@link
   * Valid} annotation.
   *
   * @param courseCategory the {@code CourseCategoryTO} object containing the course category data
   * @return a {@code ResponseEntity} containing the created {@code CourseCategoryTO} object and a
   *     {@link HttpStatus#CREATED} status
   * @throws ResourceAlreadyExistsException, if the course category already exists.
   */
  @Operation(
      summary = "Create a new course category.",
      description =
          "Allows an admin to create a new course category in the system. "
              + "This endpoint accepts a course category object and saves it to the database. "
              + "If the category already exists, an appropriate error message is returned.",
      tags = {"Course Category Endpoints"})
  @ApiResponses(
      @ApiResponse(
          responseCode = "200",
          description = "Course Category created successfully",
          content =
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = CourseCategoryTO.class))))
  @PostMapping("course/create-course-category")
  public ResponseEntity<?> createCourseCategory(
      @Valid @RequestBody CourseCategoryTO courseCategory) {
    try {
      CourseCategoryTO created = categoryService.createCourseCategory(courseCategory);
      return ResponseEntity.status(HttpStatus.CREATED).body(created);
    } catch (ResourceAlreadyExistsException ex) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Unexpected error: " + ex.getMessage());
    }
  }
}