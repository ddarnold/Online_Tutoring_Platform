package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.api.transferObjects.ChatDTO;
import de.thu.thutorium.api.transferObjects.MessageDTO;

import java.util.List;

/**
 * The ChatService interface defines the contract for the service layer that handles business logic
 * related to chats. It provides methods for retrieving chat details, chat history, and all chats
 * associated with a particular user.
 */
public interface ChatService {
  /**
   * Retrieves all chats for a given user. This method fetches all the chats in which the user is a
   * participant.
   *
   * @param userId the ID of the user whose chats are to be retrieved
   * @return a list of `ChatDTO` objects representing all the chats of the user
   */
  List<ChatDTO> getAllChatsForUser(long userId);

  /**
   * Retrieves a chat by its unique chat ID. This method fetches the details of a specific chat and
   * its participants.
   *
   * @param chatId the ID of the chat to be retrieved
   * @param userId the ID of the user requesting the chat details (for filtering purposes)
   * @return the `ChatDTO` object representing the chat with the specified ID
   */
  ChatDTO getChatById(long chatId, long userId);

  /**
   * Retrieves the message history of a chat. This method returns a list of all messages in the
   * chat, ordered by their send timestamp.
   *
   * @param userId the ID of the user requesting the chat history
   * @param currentUserId the ID of the user whose chat history is being requested
   * @return a list of `MessageDTO` objects representing the chat's message history
   */
  List<MessageDTO> getChatHistory(long userId, long currentUserId);
}
