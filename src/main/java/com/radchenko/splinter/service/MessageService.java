package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;
import com.radchenko.splinter.web.response.MessageDto;

import java.util.List;

public interface MessageService {
    List<MessageDto> getAll();
    MessageDto save(Message message);
    List<MessageDto> filterByTag(String tagFilter);
}
