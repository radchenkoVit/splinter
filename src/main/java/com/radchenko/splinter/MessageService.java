package com.radchenko.splinter;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> filterByTag(String tagFilter) {
        return messageRepository.findByTag(tagFilter);
    }
}
