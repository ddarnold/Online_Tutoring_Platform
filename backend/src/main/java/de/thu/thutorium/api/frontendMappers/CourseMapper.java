package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.api.transferObjects.CourseDTO;
import org.mapstruct.*;
import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, CourseCategoryMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CourseMapper {

  @Mapping(source = "createdBy", target = "createdBy") // Map 'createdBy' field
  @Mapping(source = "category", target = "category") // Map courseCategories
  CourseDTO toDTO(CourseDBO course);

  List<CourseDTO> toDTOList(List<CourseDBO> courses);
}