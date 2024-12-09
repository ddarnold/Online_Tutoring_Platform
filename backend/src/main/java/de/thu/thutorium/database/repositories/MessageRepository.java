package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.MessageDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageDBO, Long> {
    List<MessageDBO> findByChatChatIdOrderBySendAtAsc(Long chatId); // Fetch messages by chat ID, ordered by timestamp
}