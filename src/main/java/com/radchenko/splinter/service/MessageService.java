package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.web.response.MessageDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll(Pageable pageable);
    MessageDto save(Message message);
    List<MessageDto> filterByTag(String tagFilter, Pageable pageable);
}
