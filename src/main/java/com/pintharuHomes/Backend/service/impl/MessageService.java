package com.pintharuHomes.Backend.service.impl;

import com.pintharuHomes.Backend.entity.Message;
import com.pintharuHomes.Backend.entity.MessageEntity;
import com.pintharuHomes.Backend.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageEntity saveMessage(Message message) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSenderName(message.getSenderName());
        messageEntity.setReceiverName(message.getReceiverName());
        messageEntity.setMessage(message.getMessage());
        messageEntity.setMedia(message.getMedia());
        return messageRepository.save(messageEntity);
    }

    public List<MessageEntity> getAllMessages() {
        return messageRepository.findAll();
    }
}
