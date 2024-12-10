package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.frontendMappers.ChatMapper;
import de.thu.thutorium.api.frontendMappers.MessageMapper;
import de.thu.thutorium.api.transferObjects.ChatDTO;
import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.database.dbObjects.ChatDBO;
import de.thu.thutorium.database.dbObjects.MessageDBO;
import de.thu.thutorium.database.repositories.ChatRepository;
import de.thu.thutorium.database.repositories.MessageRepository;
import de.thu.thutorium.services.interfaces.ChatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The ChatServiceImpl class is a service implementation that handles business logic related to
 * chats. It interacts with the `ChatRepository` and `MessageRepository` to fetch and manipulate
 * chat and message data. The class implements the `ChatService` interface and provides methods for
 * retrieving chats and chat histories.
 */
@Service
public class ChatServiceImpl implements ChatService {

  private final ChatRepository chatRepository;
  private final MessageRepository messageRepository;

  /**
   * Constructor to initialize ChatServiceImpl with required dependencies.
   *
   * @param chatRepository the repository for accessing chat data
   * @param messageRepository the repository for accessing message data
   */
  public ChatServiceImpl(ChatRepository chatRepository, MessageRepository messageRepository) {
    this.chatRepository = chatRepository;
    this.messageRepository = messageRepository;
  }

  /**
   * Retrieves all chats for a given user. This method filters chats by checking if the user is a
   * participant in each chat, and then maps each chat to a `ChatDTO` for the response.
   *
   * @param userId the ID of the user whose chats are to be fetched
   * @return a list of `ChatDTO` objects representing all chats that the user is a participant of
   */
  @Override
  public List<ChatDTO> getAllChatsForUser(long userId) {
    // Fetch all chats and map them to DTOs
    return chatRepository.findAll().stream()
        .filter(chat -> isUserParticipant(chat, userId))
        .map(chat -> toChatDTO(chat, userId))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a specific chat by its ID. This method fetches the chat from the repository, and if
   * it exists, maps it to a `ChatDTO` for the response. If the chat is not found, an
   * `EntityNotFoundException` is thrown.
   *
   * @param chatId the ID of the chat to retrieve
   * @param userId the ID of the user requesting the chat (used to map the chat to a DTO)
   * @return a `ChatDTO` representing the chat with the specified ID
   * @throws EntityNotFoundException if no chat is found with the given ID
   */
  @Override
  public ChatDTO getChatById(long chatId, long userId) {
    // Fetch chat by ID and map to DTO
    ChatDBO chat =
        chatRepository
            .findById(chatId)
            .orElseThrow(() -> new EntityNotFoundException("Chat not found with id " + chatId));
    return toChatDTO(chat, userId);
  }

  /**
   * Retrieves the message history for a specific chat. This method fetches all messages associated
   * with the given `chatId`, ordered by the send timestamp. Each message is then mapped to a
   * `MessageDTO` using the `MessageMapper`.
   *
   * @param chatId the ID of the chat whose message history is to be retrieved
   * @param currentUserId the ID of the user requesting the message history (used to map each
   *     message)
   * @return a list of `MessageDTO` objects representing the message history of the specified chat
   */
  @Override
  public List<MessageDTO> getChatHistory(long chatId, long currentUserId) {
    // Delegate message retrieval to MessageService
    return messageRepository.findByChatChatIdOrderBySendAtAsc(chatId).stream()
        .map(message -> MessageMapper.INSTANCE.toDTO(message, currentUserId))
        .collect(Collectors.toList());
  }

  /**
   * Helper method to check if a user is a participant in a specific chat. This method checks if the
   * provided user ID exists as one of the participants in the chat.
   *
   * @param chat the `ChatDBO` object to check for the user participant
   * @param userId the ID of the user to check for participation
   * @return true if the user is a participant in the chat, false otherwise
   */
  private boolean isUserParticipant(ChatDBO chat, long userId) {
    return chat.getParticipants().stream().anyMatch(user -> user.getUserId().equals(userId));
  }

  /**
   * Helper method to map a `ChatDBO` object to a `ChatDTO`. This method retrieves the last message
   * from the chat, if available, and creates a `ChatDTO` with the provided user ID.
   *
   * @param chat the `ChatDBO` object to map
   * @param userId the ID of the user to map the chat for (used to map participant details)
   * @return a `ChatDTO` object representing the chat
   */
  private ChatDTO toChatDTO(ChatDBO chat, long userId) {
    Optional<MessageDBO> lastMessage =
        chat.getMessages().stream().max(Comparator.comparing(MessageDBO::getSendAt));
    return ChatMapper.INSTANCE.toDTO(chat, userId, lastMessage);
  }
}
