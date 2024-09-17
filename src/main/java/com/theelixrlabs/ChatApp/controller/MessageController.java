package com.theelixrlabs.ChatApp.controller;

import com.theelixrlabs.ChatApp.constants.ApiPathsConstant;
import com.theelixrlabs.ChatApp.constants.MessageConstants;
import com.theelixrlabs.ChatApp.dto.MessageDto;
import com.theelixrlabs.ChatApp.response.ApiResponse;
import com.theelixrlabs.ChatApp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MessageController handles the HTTP requests related to messages.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Endpoint for saving a message.
     * This handles POST requests to the URL defined in ApiPathsConstant.SAVE_MESSAGE_URL.
     *
     * @param messageDto The message data transfer object containing the message content.
     * @return ResponseEntity with an ApiResponse containing a success or failure message.
     */
    @PostMapping(ApiPathsConstant.SAVE_MESSAGE_URL)
    public ResponseEntity<ApiResponse<String>> sendMessage(@RequestBody MessageDto messageDto) {
        try {
            messageService.saveMessage(messageDto.getContent());
            return ResponseEntity.ok(new ApiResponse<>(true, MessageConstants.MESSAGE_SEND_SUCCESSFULLY, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, MessageConstants.FAILED_TO_SEND_MESSAGE));
        }
    }

    /**
     * Endpoint for retrieving the latest 10 messages.
     * This handles GET requests to the URL defined in ApiPathsConstant.RETRIEVE_MESSAGE_URL.
     *
     * @return ResponseEntity with an ApiResponse containing a list of MessageDto or an error message.
     */
    @GetMapping(ApiPathsConstant.RETRIEVE_MESSAGE_URL)
    public ResponseEntity<ApiResponse<List<MessageDto>>> retrieveMessages() {
        try {
            List<MessageDto> messages = messageService.getLatestMessages();
            return ResponseEntity.ok(new ApiResponse<>(true, messages, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, null, MessageConstants.FAILED_TO_RETRIVE_MESSAGE));
        }
    }
}
