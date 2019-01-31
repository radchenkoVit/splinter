package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public Message save(Message message) {
       return messageRepository.save(message);
    }

    @Transactional(readOnly = true)
    public List<Message> filterByTag(String tagFilter) {
        return messageRepository.findByTag(tagFilter);
    }
}
