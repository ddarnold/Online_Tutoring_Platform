package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.api.transferObjects.MessageDTO;

import java.util.List;

/**
 * The MessageService interface defines the contract for the service layer responsible for handling
 * business logic related to messages. It provides methods for saving new messages and retrieving
 * messages associated with a specific chat.
 */
public interface MessageService {
  /**
   * Saves a new message. This method takes a `MessageDTO` object, saves the message to the
   * database, and returns the saved message as a `MessageDTO` object.
   *
   * @param message the message to be saved
   * @return the saved message mapped to a `MessageDTO`
   */
  MessageDTO saveMessage(MessageDTO message);

  /**
   * Retrieves all messages for a specific chat. This method fetches the messages for the given
   * `chatId` and maps each message to a `MessageDTO`. The messages are returned ordered by their
   * send timestamp.
   *
   * @param chatId the ID of the chat whose messages are to be retrieved
   * @param currentUserId the ID of the user requesting the messages (used to map each message)
   * @return a list of `MessageDTO` objects representing the messages in the specified chat
   */
  List<MessageDTO> getMessageByChatId(Long chatId, Long currentUserId);
}
