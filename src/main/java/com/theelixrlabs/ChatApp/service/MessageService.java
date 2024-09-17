package com.theelixrlabs.ChatApp.service;

import com.theelixrlabs.ChatApp.constants.MessageConstants;
import com.theelixrlabs.ChatApp.dto.MessageDto;
import com.theelixrlabs.ChatApp.model.Message;
import com.theelixrlabs.ChatApp.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    // Use the constant directly from MessageConstants
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(MessageConstants.DATE_FORMATTER);

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(String content) {
        String formattedTimestamp = LocalDateTime.now().format(FORMATTER);
        Message message = Message.builder()
                .id(UUID.randomUUID())
                .content(content)
                .timestamp(formattedTimestamp)
                .build();
        messageRepository.save(message);
    }

    public List<MessageDto> getLatestMessages() {
        return messageRepository.findTop10ByOrderByTimestampDesc().stream()
                .map(message -> MessageDto.builder()
                        .id(message.getId())
                        .content(message.getContent())
                        .timestamp(message.getTimestamp())
                        .build())
                .collect(Collectors.toList());
    }
}
