package ru.popov.microService1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.popov.microService1.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message findById(long id);
}
