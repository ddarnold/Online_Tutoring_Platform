package de.thu.thutorium.api.transferObjects;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingCourseDTO {
    private Long ratingId;
    private Double points;
    private String review;
    private LocalDateTime createdAt;

    // Basic information about the student who gave the rating
    private UserBaseDTO student;
}
