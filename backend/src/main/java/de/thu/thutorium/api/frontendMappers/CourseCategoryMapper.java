package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.database.dbObjects.CourseCategoryDBO;
import de.thu.thutorium.api.transferObjects.CourseCategoryDTO;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CourseCategoryMapper {

    @Mapping(source = "categoryName", target = "categoryName")
    CourseCategoryDTO toDTO(CourseCategoryDBO courseCategory);
}