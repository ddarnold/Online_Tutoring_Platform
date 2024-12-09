package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.api.transferObjects.StudentDTO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        uses = {CourseMapper.class, UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StudentMapper {

    /**
     * Maps a UserDBO representing a student to a StudentDTO.
     */
    @Mapping(target = "studentCourses", source = "studentCourses")
    StudentDTO toDTO(UserDBO student);
}