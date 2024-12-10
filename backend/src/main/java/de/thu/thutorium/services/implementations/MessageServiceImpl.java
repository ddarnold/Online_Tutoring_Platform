package de.thu.thutorium.services.implementations;

import de.thu.thutorium.api.frontendMappers.MessageMapper;
import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.database.dbObjects.MessageDBO;
import de.thu.thutorium.database.repositories.MessageRepository;
import de.thu.thutorium.services.interfaces.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The MessageServiceImpl class is a service implementation that handles business logic related to
 * messages. It interacts with the `MessageRepository` to save messages and retrieve message data by
 * chat ID. The class implements the `MessageService` interface and provides methods for saving and
 * retrieving messages.
 */
@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;

  /**
   * Constructor to initialize MessageServiceImpl with the required dependencies.
   *
   * @param messageRepository the repository for accessing message data
   */
  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  /**
   * Saves a new message. This method converts the `MessageDTO` to a `MessageDBO` entity using the
   * `MessageMapper`, saves the message to the database, and then maps the saved message back to a
   * `MessageDTO`.
   *
   * @param messageDTO the message to be saved
   * @return the saved message mapped to a `MessageDTO`
   */
  @Override
  public MessageDTO saveMessage(MessageDTO messageDTO) {
    // Convert DTO to Entity, save, and return mapped DTO
    MessageDBO savedMessage = messageRepository.save(MessageMapper.INSTANCE.toEntity(messageDTO));
    return MessageMapper.INSTANCE.toDTO(savedMessage, messageDTO.getSenderId());
  }

  /**
   * Retrieves all messages for a specific chat by its chat ID. This method fetches all messages
   * associated with the given `chatId`, ordered by the send timestamp. Each message is then mapped
   * to a `MessageDTO` using the `MessageMapper`.
   *
   * @param chatId the ID of the chat whose messages are to be retrieved
   * @param currentUserId the ID of the user requesting the messages (used to map each message)
   * @return a list of `MessageDTO` objects representing the messages in the specified chat
   */
  @Override
  public List<MessageDTO> getMessageByChatId(Long chatId, Long currentUserId) {
    // Fetch messages for chatId and map to DTOs
    return messageRepository.findByChatChatIdOrderBySendAtAsc(chatId).stream()
        .map(message -> MessageMapper.INSTANCE.toDTO(message, currentUserId))
        .collect(Collectors.toList());
  }
}
