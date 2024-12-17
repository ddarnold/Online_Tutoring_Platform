package de.thu.thutorium.api.TOMappers.common;

import de.thu.thutorium.api.transferObjects.common.UniversityTO;
import de.thu.thutorium.database.dbObjects.UniversityDBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Mapper interface for converting {@link UniversityDBO} to {@link UniversityTO}.
 */
@Mapper(
        componentModel = "spring",
        uses = {AddressTOMapper.class}
)
public interface UniversityTOMapper {
    /**
     * Converts a {@link UniversityDBO} object to an {@link UniversityTO}
     * object.
     *
     * @param universityDBO the {@code UniversityDBO} object to convert
     * @return an {@code toDTO} object containing the mapped data
     */
    @Mappings({
            @Mapping(target = "universityName", source = "universityName"),
            @Mapping(target = "address", source = "address")
    })
    UniversityTO toDTO(UniversityDBO universityDBO);
}
