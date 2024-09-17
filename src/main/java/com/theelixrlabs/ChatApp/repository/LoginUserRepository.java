package com.theelixrlabs.ChatApp.repository;

import com.theelixrlabs.ChatApp.model.LoginUser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on the LoginUser collection in MongoDB.
 * Extends MongoRepository, providing built-in methods for data access and custom query methods.
 */
public interface LoginUserRepository extends MongoRepository<LoginUser, String> {
    Optional<LoginUser> findByUsername(String username);
}
