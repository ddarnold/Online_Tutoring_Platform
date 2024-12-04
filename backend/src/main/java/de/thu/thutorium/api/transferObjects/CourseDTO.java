package de.thu.thutorium.api.transferObjects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/** A Data Transfer Object for the Course entity. */
@Data
public class CourseDTO {
  private Long courseId;
  private String courseName;
  private String descriptionShort;
  private String descriptionLong;
  private LocalDateTime createdAt;
  private LocalDate startDate;
  private LocalDate endDate;

  // User who created the course (only basic details)
  private UserBaseDTO createdBy;

  // List of categories associated with the course
  private CourseCategoryDTO category;

  // List of ratings for this course
  private List<RatingCourseDTO> receivedCourseRatings;


}