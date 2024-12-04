package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.api.transferObjects.RatingCourseDTO;
import de.thu.thutorium.database.dbObjects.RatingCourseDBO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RatingCourseMapper {
    @Mapping(target = "student", source = "student") // Map UserDBO to UserBaseDTO
    RatingCourseDTO toDTO(RatingCourseDBO dbo);

    @InheritInverseConfiguration
    @Mapping(target = "course", ignore = true) // Explicitly ignore the course field
    RatingCourseDBO toDBO(RatingCourseDTO dto);
}
