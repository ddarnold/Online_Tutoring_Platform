package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.MessageDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The MessageRepository interface extends JpaRepository and is responsible for providing data
 * access methods for the `MessageDBO` (message) entities. It defines a custom query to retrieve
 * messages by chat ID, ordered by timestamp.
 */
@Repository
public interface MessageRepository extends JpaRepository<MessageDBO, Long> {
  /**
   * Fetches all messages associated with a specific chat, ordered by the time they were sent. This
   * custom query retrieves messages from the `MessageDBO` table by the `chatId`, and orders them in
   * ascending order of the `sendAt` timestamp, which ensures that the messages are retrieved in the
   * correct chronological order.
   *
   * @param chatId the ID of the chat for which messages are to be retrieved
   * @return a list of `MessageDBO` objects representing the messages sent in the specified chat,
   *     ordered by timestamp
   */
  @Query("SELECT m FROM MessageDBO m WHERE m.chat.chatId = :chatId ORDER BY m.sendAt ASC")
  List<MessageDBO> findByChatChatIdOrderBySendAtAsc(
      @Param("chatId") Long chatId); // Fetch messages by chat ID, ordered by timestamp
}
