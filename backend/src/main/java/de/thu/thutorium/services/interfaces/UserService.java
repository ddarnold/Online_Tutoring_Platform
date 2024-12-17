package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.api.transferObjects.common.UserTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service interface for managing addresses.
 */
@Service
public interface UserService {

    /**
     * Updates an existing user.
     *
     * @param id the id of the user
     * @param user the {@code UserTO} object containing the user data
     * @return the created {@code UserTO} object
     */
    Optional<UserTO> updateUser(int id, UserTO user);

}
