package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.api.transferObjects.UserBaseDTO;
import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.api.transferObjects.CourseDTO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import org.mapstruct.*;

import java.util.List;

/** A MapStruct mapper for converting between Course entities and CourseDTOs. */
/** A MapStruct mapper for converting between CourseDBO entities and CourseDTOs. */
@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CourseMapper {

//  @Mapping(source = "courseCategories", target = "courseCategories")
//  @Mapping(source = "receivedCourseRatings", target = "receivedCourseRatings")
  @Mapping(source = "createdBy", target = "createdBy")  // Map 'createdBy' field
  CourseDTO toDTO(CourseDBO course);

  List<CourseDTO> toDTOList(List<CourseDBO> courses);


}