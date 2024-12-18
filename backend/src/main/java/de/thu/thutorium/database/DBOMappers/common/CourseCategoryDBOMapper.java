package de.thu.thutorium.database.DBOMappers.common;

import de.thu.thutorium.api.transferObjects.common.CourseCategoryTO;
import de.thu.thutorium.database.dbObjects.CourseCategoryDBO;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper interface for converting {@link CourseCategoryTO} to
 * {@link CourseCategoryDBO}.
 */
@Mapper(componentModel = "spring")
public interface CourseCategoryDBOMapper {
    /**
     * Converts an {@link CourseCategoryTO} object to an {@link CourseCategoryDBO} object.
     */
    CourseCategoryDBO toDBO(CourseCategoryTO courseCategory);
}
