package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.web.response.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MessageService {
    Page<MessageDto> getAll(Pageable pageable);
    MessageDto save(Message message);
    Page<MessageDto> filterByTag(String tagFilter, Pageable pageable);
}
