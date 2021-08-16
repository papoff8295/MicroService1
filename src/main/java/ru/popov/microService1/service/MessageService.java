package ru.popov.microService1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.popov.microService1.model.Message;
import ru.popov.microService1.repo.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Message message) {
        repository.save(message);
    }
}
