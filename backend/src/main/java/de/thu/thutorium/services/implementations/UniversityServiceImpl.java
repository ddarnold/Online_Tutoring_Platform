package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.TOMappers.common.UniversityTOMapper;
import de.thu.thutorium.api.transferObjects.common.UniversityTO;
import de.thu.thutorium.database.DBOMappers.common.UniversityDBOMapper;
import de.thu.thutorium.database.dbObjects.UniversityDBO;
import de.thu.thutorium.database.exceptions.ResourceAlreadyExistsException;
import de.thu.thutorium.database.repositories.UniversityRepository;
import de.thu.thutorium.services.interfaces.UniversityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing universities.
 * This class provides methods for creating and managing university entities.
 * It uses {@link UniversityRepository} for database operations and
 * {@link UniversityTOMapper} for mapping between
 * transfer objects and database objects.
 */
@RequiredArgsConstructor
@Service
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;
    private final UniversityTOMapper universityTOMapper;
    private final UniversityDBOMapper universityDBOMapper;

    /**
     * Creates a new university.
     * This method converts the {@link UniversityTO} object to a {@link UniversityDBO} object, saves it to the database.
     * An exception is thrown if the univeristy already exists in the database.
     * The university should be added together with its address
     *
     * @param university the {@code UniversityTO} object containing the university data
     * @return the created {@code UniversityTO} object
     */
    @Override
    @Transactional
    public UniversityTO createUniversity(UniversityTO university) {
        if (universityExists(university)) {
            throw new ResourceAlreadyExistsException("University with name " + university.getUniversityName() + " already exists");
        }
        UniversityDBO universityDBO = universityDBOMapper.toDBO(university);
        universityRepository.save(universityDBO);
        return universityTOMapper.toDTO(universityDBO);
    }

    /**
     * Checks if the university already exists.
     *
     * @param university the {@code UniversityTO} object containing the university data
     * @return the boolean value corresponding to if the university already exists or not.
     */
    @Override
    public Boolean universityExists(UniversityTO university) {
        Optional<UniversityDBO> existingUniversity = universityRepository.findByUniversityName(university.getUniversityName());
        return existingUniversity.isPresent();
    }

}
