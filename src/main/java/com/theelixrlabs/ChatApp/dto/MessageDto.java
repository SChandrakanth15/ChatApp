package com.theelixrlabs.ChatApp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

/**
 * MessageDto is a Data Transfer Object that represents the message data being exchanged
 */
@SuperBuilder
@Data
@NoArgsConstructor
public class MessageDto {
    private UUID id;
    private String content;
    private String timestamp; // Formatted timestamp in "dd:MM:yyyy HH:mm:ss"
}