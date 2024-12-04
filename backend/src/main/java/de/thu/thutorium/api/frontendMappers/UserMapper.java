package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.database.dbObjects.*;
import de.thu.thutorium.api.transferObjects.*;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A MapStruct mapper for converting between UserDBO and UserBaseDTO.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    UserBaseDTO toDTO(UserDBO user);

    @InheritInverseConfiguration
    UserDBO toEntity(UserBaseDTO userBaseDTO);
}