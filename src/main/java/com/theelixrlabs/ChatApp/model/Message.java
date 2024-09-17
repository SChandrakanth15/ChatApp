package com.theelixrlabs.ChatApp.model;

import com.theelixrlabs.ChatApp.constants.MessageConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * The Message class represents the message entity stored in the MongoDB collection.
 * It includes fields for the message content, timestamp, and a unique identifier.
 */
@Data
@Document(collection = MessageConstants.MESSAGE_COLLECTION_NAME)
@SuperBuilder
@NoArgsConstructor
public class Message {
    @Id
    private UUID id;
    private String content;
    private String timestamp; // Store timestamp as String in "dd:MM:yyyy HH:mm:ss" format
}
