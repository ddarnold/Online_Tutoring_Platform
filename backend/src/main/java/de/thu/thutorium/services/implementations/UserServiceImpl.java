package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.TOMappers.common.UserTOMapper;
import de.thu.thutorium.api.transferObjects.common.UserTO;
import de.thu.thutorium.database.DBOMappers.common.AffiliationDBOMapper;
import de.thu.thutorium.database.dbObjects.AffiliationDBO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import de.thu.thutorium.database.repositories.AffiliationRepository;
import de.thu.thutorium.database.repositories.UserRepository;
import de.thu.thutorium.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service implementation for managing users.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AffiliationRepository affiliationRepository;
    private final UserTOMapper userTOMapper;
    private final AffiliationDBOMapper affiliationDBOMapper;

    /**
     * Updates a user based on the request body.
     * The code finds the requested database object by Id, and updates its fields based on the field values in the transfer object.
     * The affiliation (which includes affiliation type + university), is set for the user.
     * If the requested affiliation combination does not exist, it is created for the user.
     * However, the university should already exist in the database.
     * @param id, the of the user to be updated.
     * @param user the user request transfer object containing the updated information of the user.
     * @return a ResponseEntity containing the response transfer object of the updated user object.
     */
    @Override
    @Transactional
    public Optional<UserTO> updateUser(int id, UserTO user) {
        Optional<UserDBO> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            UserDBO existingUser = existingUserOptional.get();

            // Convert AffiliationTO to AffiliationDBO
            AffiliationDBO affiliationDBO = affiliationDBOMapper.toDBO(user.getAffiliation());

            // Check if the affiliation already exists by university name and affiliation type
            Optional<AffiliationDBO> existingAffiliationOptional = affiliationRepository
                    .findByAffiliationTypeAndUniversity_UniversityName(
                            affiliationDBO.getAffiliationType(), affiliationDBO.getUniversity().getUniversityName());
            //If the affiliation combination already exists, it is used; otherwise a new affiliation is created for the user.
            AffiliationDBO savedAffiliationDBO = existingAffiliationOptional
                    .orElseGet(() -> affiliationRepository.save(affiliationDBO));

            // Set the saved AffiliationDBO in the existing UserDBO
            existingUser.setAffiliation(savedAffiliationDBO);

            // Update other fields of the existing UserDBO
            existingUser.setDescription(user.getDescription());
            existingUser.setEmail(user.getEmail());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());

            // Save the updated UserDBO
            UserDBO updatedUser = userRepository.save(existingUser);
            return Optional.of(userTOMapper.toDTO(updatedUser));
        } else {
            return Optional.empty();
        }
    }
}
