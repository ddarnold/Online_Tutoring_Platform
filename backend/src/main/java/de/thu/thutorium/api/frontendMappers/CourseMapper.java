package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.api.transferObjects.CourseDTO;
import org.mapstruct.*;
import java.util.List;

/**
 * A MapStruct mapper interface for converting between {@link CourseDBO} (Course Database Object)
 * and {@link CourseDTO} (Course Data Transfer Object).
 *
 * <p>This interface defines the mapping logic to convert a {@code CourseDBO} (representing a course
 * in the database) to a {@code CourseDTO} and vice versa. MapStruct automates the mapping process,
 * ensuring type-safe and efficient conversion between these two objects.
 *
 * <p>The {@code uses} attribute specifies that the {@link UserMapper} and {@link
 * CourseCategoryMapper} will be used to map the {@code createdBy} field (which is a {@code
 * UserDBO}) to a {@code UserBaseDTO} in the {@code CourseDTO}, and the {@code category} field
 * (which is a {@code CourseCategoryDBO}) to a {@code CourseCategoryDTO} in the {@code CourseDTO}.
 *
 * <p>Note: The {@code componentModel = "spring"} annotation indicates that MapStruct will generate
 * a Spring bean for this mapper, making it available for dependency injection in Spring components
 * or services.
 */
@Mapper(
    componentModel = "spring",
    uses = {UserMapper.class, CourseCategoryMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
  /**
   * Converts a {@link CourseDBO} (representing a course in the database) to a {@link CourseDTO}.
   *
   * <p>This method uses the {@link UserMapper} to map the {@code createdBy} field (which is a
   * {@code UserDBO}) to {@code UserBaseDTO}, and the {@link CourseCategoryMapper} to map the {@code
   * category} field (which is a {@code CourseCategoryDBO}) to {@code CourseCategoryDTO}.
   *
   * @param course the {@code CourseDBO} object representing the course to convert
   * @return a {@code CourseDTO} object containing the course data
   */
  @Mapping(source = "createdBy", target = "createdBy") // Map 'createdBy' field
  @Mapping(source = "category", target = "category") // Map courseCategories
  @Mapping(
      target = "receivedCourseRatings",
      source = "receivedCourseRatings") // Map List<RatingCourseDBO> to List<RatingCourseDTO>
  CourseDTO toDTO(CourseDBO course);

  /**
   * Converts a list of {@link CourseDBO} (representing multiple courses in the database) to a list
   * of {@link CourseDTO}.
   *
   * <p>This method internally calls the {@link #toDTO(CourseDBO)} method for each {@code CourseDBO}
   * in the list.
   *
   * @param courses a list of {@code CourseDBO} objects to convert
   * @return a list of {@code CourseDTO} objects containing the course data
   */
  List<CourseDTO> toDTOList(List<CourseDBO> courses);
}
