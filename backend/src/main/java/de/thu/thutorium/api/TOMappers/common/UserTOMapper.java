package de.thu.thutorium.api.TOMappers.common;

import de.thu.thutorium.api.transferObjects.common.UserTO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper interface for converting {@link UserDBO} to
 * {@link UserTO}.
 */
@Mapper(componentModel = "spring", uses = { AffiliationTOMapper.class })
public interface UserTOMapper {
    /**
     * Converts a {@link UserDBO} object to a {@link UserTO}.
     *
     * @param user the {@code UserDBO} object to convert
     * @return a {@code UserBaseDTO} object containing the user data
     */
    UserTO toDTO(UserDBO user);
}
