package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.ChatDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The ChatRepository interface extends JpaRepository and is responsible for providing data access
 * methods for the `ChatDBO` (chat) entities. It defines custom queries to retrieve chats by
 * participant user ID and to find a chat by its ID.
 */
@Repository
public interface ChatRepository extends JpaRepository<ChatDBO, Long> {
  /**
   * Finds all chats where the specified user is a participant. This custom query uses a JOIN to
   * find chats that have a specific user ID as one of the participants.
   *
   * @param userId the ID of the user whose chats are to be retrieved
   * @return a list of `ChatDBO` objects representing the chats the user is a participant of
   */
  @Query("SELECT c FROM ChatDBO c JOIN c.participants p WHERE p.userId = :userId")
  List<ChatDBO> findAllByParticipantUserId(@Param("userId") Long userId);

  /**
   * Finds a chat by its unique chat ID. This method retrieves a single chat by its chat ID. The
   * result is wrapped in an Optional to handle the possibility that the chat might not exist.
   *
   * @param chatId the ID of the chat to be retrieved
   * @return an Optional containing the `ChatDBO` object if the chat is found, or an empty Optional
   *     if not
   */
  @Query("SELECT c FROM ChatDBO c WHERE c.chatId = :chatId")
  Optional<ChatDBO> findById(@Param("chatId") Long chatId);
}
