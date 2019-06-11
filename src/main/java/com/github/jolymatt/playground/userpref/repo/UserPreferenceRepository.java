package com.github.jolymatt.playground.userpref.repo;

import com.github.jolymatt.playground.userpref.domain.UserPreference;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * The UserPreferences Repository
 * @author  Joly Mathew
 *
 */

public interface UserPreferenceRepository extends CrudRepository<UserPreference, UUID> {
    /**
     * This method retrieves User Preferences by User Id
     * @param userId String
     * @return UserPreference
     */
    public Optional<UserPreference> findByUserId(String userId);

}
