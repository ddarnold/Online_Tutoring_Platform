package de.thu.thutorium.database.DBOMappers.common;

import de.thu.thutorium.api.transferObjects.common.AddressTO;
import de.thu.thutorium.api.transferObjects.common.CourseTO;
import de.thu.thutorium.database.dbObjects.AddressDBO;
import de.thu.thutorium.database.dbObjects.CourseDBO;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper interface for converting {@link CourseTO} to
 * {@link CourseDBO}.
 */
@Mapper(componentModel = "spring")
public interface CourseDBOMapper {
    /**
     * Converts an {@link CourseTO} object to an {@link CourseDBO} object.
     */
    CourseDBO toDBO(CourseTO course);
}
