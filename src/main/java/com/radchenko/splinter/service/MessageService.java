package com.radchenko.splinter.service;

import com.radchenko.splinter.entity.Message;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    Message save(Message message);
    List<Message> filterByTag(String tagFilter);
}
