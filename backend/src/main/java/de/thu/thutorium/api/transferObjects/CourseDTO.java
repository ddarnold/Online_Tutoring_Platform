package de.thu.thutorium.api.transferObjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a course in the system.
 *
 * <p>This class holds information about a course, including its name, description, start and end
 * dates, the user who created the course, the associated category, and any ratings received for the
 * course.
 */
@Data
public class CourseDTO {
  /**
   * The unique identifier for the course.
   *
   * <p>This field represents the unique ID for the course, which is used for identifying and
   * retrieving the course from the system.
   */
  private Long courseId;

  /**
   * The name of the course.
   *
   * <p>This field holds the name or title of the course, providing a quick reference to the course
   * content.
   */
  private String courseName;

  /**
   * A short description of the course.
   *
   * <p>This field provides a brief overview of the course, usually meant to give potential students
   * a quick understanding of what the course covers.
   */
  private String descriptionShort;

  /**
   * A detailed description of the course.
   *
   * <p>This field contains an in-depth description of the course, outlining the course content,
   * objectives, prerequisites, and other important details.
   */
  private String descriptionLong;

  /**
   * The timestamp when the course was created.
   *
   * <p>This field records the date and time when the course was created, helping track the course's
   * creation date within the system.
   */
  private LocalDateTime createdAt;

  /**
   * The start date of the course.
   *
   * <p>This field indicates when the course will begin, which is important for students to know
   * when the course is available to attend or when the course content will be accessible.
   */
  private LocalDate startDate;

  /**
   * The end date of the course.
   *
   * <p>This field indicates when the course will conclude, which helps students know when to expect
   * the course to be completed or when the final assessments will take place.
   */
  private LocalDate endDate;

  /**
   * Basic information about the user who created the course.
   *
   * <p>This field contains a {@code UserBaseDTO} object, representing the creator of the course. It
   * includes basic details such as the user's name and email, but does not include sensitive
   * information.
   */
  private UserBaseDTO createdBy;

  /**
   * The category associated with the course.
   *
   * <p>This field contains a {@code CourseCategoryDTO} object, which represents the category or
   * subject area the course falls under. Categories help organize courses and make it easier for
   * students to find relevant courses.
   */
  private CourseCategoryDTO category;

  /**
   * A list of ratings for the course.
   *
   * <p>This field contains a list of {@code RatingCourseDTO} objects, each representing a rating
   * and review provided by a student for the course. The ratings can provide valuable feedback on
   * the course content and the overall student experience.
   */
  private List<RatingCourseDTO> receivedCourseRatings;
}
