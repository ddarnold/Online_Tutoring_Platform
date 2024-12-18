package de.thu.thutorium.api.TOMappers.common;

import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.database.dbObjects.CourseDBO;
import org.mapstruct.*;

/**
 * A MapStruct mapper interface for converting between {@link CourseDBO} (Course Database Object)
 * and {@link CourseTO} (Course Data Transfer Object).
 *
 * <p>This interface defines the mapping logic to convert a {@code CourseDBO} (representing a course
 * in the database) to a {@code CourseDTO}.
 * </p>
 */
@Mapper(componentModel = "spring")
public interface CourseTOMapper {

    /**
     * Maps a single CourseDBO to a CourseDTO.
     *
     * @param course The CourseDBO instance to map.
     * @return The mapped CourseDTO.
     */
    CourseTO toDTO(CourseDBO course);
}
