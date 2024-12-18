package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.AffiliationDBO;
import de.thu.thutorium.database.dbObjects.enums.AffiliationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link AffiliationDBO} entities
 * in the database.
 * This interface extends {@link JpaRepository}, providing CRUD operations and
 * additional query methods
 * for the {@code AffiliationDBO} entity.
 * <p>
 * By extending {@code JpaRepository}, this interface inherits these methods and
 * can be used to perform
 * database operations on {@code AffiliationDBO} entities.
 * </p>
 * Todo: Revise Deprecated methods
 */
@Repository
public interface AffiliationRepository extends JpaRepository<AffiliationDBO, Long> {
    /**
     * Checks if an affiliationDBO entity exists with the given affiliation type and university name.
     *
     * @param affiliationType the affiliation type to check
     * @param universityName the name of the university  to check
     * @return {@code Optional<AffiliationDBO>} object if it exists according to the parameters above.
     * @deprecated : Check and revise.
     */
    @Deprecated
    Optional<AffiliationDBO> findByAffiliationTypeAndUniversity_UniversityName(AffiliationType affiliationType, String universityName);
}
