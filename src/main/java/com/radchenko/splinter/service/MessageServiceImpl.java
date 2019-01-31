package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.repository.MessageRepository;
import com.radchenko.splinter.web.response.MessageDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ModelMapper mapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ModelMapper mapper) {
        this.messageRepository = messageRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<MessageDto> getAll() {
        return messageRepository.findAll()
                .stream()
                .map(m -> mapper.map(m, MessageDto.class))
                .collect(toList());
    }

    @Transactional
    public MessageDto save(Message message) {
       Message saved = messageRepository.save(message);
       return mapper.map(saved, MessageDto.class);
    }

    @Transactional(readOnly = true)
    public List<MessageDto> filterByTag(String tagFilter) {
        return messageRepository
                .findByTag(tagFilter)
                .stream()
                .map(m -> mapper.map(m, MessageDto.class))
                .collect(toList());
    }
}
