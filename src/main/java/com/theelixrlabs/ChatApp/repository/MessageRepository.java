package com.theelixrlabs.ChatApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.theelixrlabs.ChatApp.model.Message;

import java.util.UUID;
import java.util.List;

/**
 * Repository interface for performing CRUD operations on the Message collection in MongoDB.
 */
public interface MessageRepository extends MongoRepository<Message, UUID> {
    /**
     * Retrieves the latest 10 messages, ordered by their timestamp in descending order.
     *
     * @return A list of the latest 10 Message objects.
     */
    List<Message> findTop10ByOrderByTimestampDesc();
}
