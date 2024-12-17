package de.thu.thutorium.database.DBOMappers.common;

import de.thu.thutorium.api.transferObjects.common.UniversityTO;
import de.thu.thutorium.database.dbObjects.UniversityDBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * A MapStruct mapper interface for converting {@link UniversityTO} to
 * {@link UniversityDBO}.
 */
@Mapper(componentModel = "spring",
        uses = { AddressDBOMapper.class })
public interface UniversityDBOMapper {
    /**
     * Converts an {@link UniversityTO} object to a {@link UniversityDBO}
     * object.
     *
     * @param universityTO the {@code AdminUniversityTO} object to convert
     * @return a {@code toDBO} object containing the mapped data
     */
    @Mappings({
                    @Mapping(target = "universityName", source = "universityName"),
                    @Mapping(target = "address", source = "address")
    })
    UniversityDBO toDBO(UniversityTO universityTO);
}
