package de.thu.thutorium.database.DBOMappers.common;

import de.thu.thutorium.api.transferObjects.common.AddressTO;
import de.thu.thutorium.database.dbObjects.AddressDBO;
import org.mapstruct.Mapper;

/**
 * A MapStruct mapper interface for converting {@link AddressTO} to
 * {@link AddressDBO}.
 */
@Mapper(componentModel = "spring")
public interface AddressDBOMapper {
        /**
     * Converts an {@link AddressTO} object to an {@link AddressDBO} object.
     *
     * @param addressTO the {@code AdminAddressTO} object to convert
     * @return an {@code AddressDBO} object containing the mapped data
     */
    AddressDBO toDBO(AddressTO addressTO);
}
