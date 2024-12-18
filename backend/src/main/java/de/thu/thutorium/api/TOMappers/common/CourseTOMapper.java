package de.thu.thutorium.api.TOMappers.common;

import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.api.transferObjects.common.UniversityTO;
import de.thu.thutorium.database.dbObjects.CourseDBO;
import de.thu.thutorium.database.dbObjects.UniversityDBO;
import org.mapstruct.*;

/**
 * Mapper interface for converting {@link CourseDBO} to {@link CourseTO}.
 */
@Mapper(componentModel = "spring")
public interface CourseTOMapper {

    /**
     * Converts a {@link CourseDBO} object to an {@link CourseTO}
     * object.
     */
    CourseTO toTO(CourseDBO courseDBO);
}
