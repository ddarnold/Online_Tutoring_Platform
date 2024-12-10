package de.thu.thutorium.api.transferObjects;

import lombok.Data;

/**
 * The ChatDTO class represents a Data Transfer Object (DTO) for a chat. It is used to transfer
 * chat-related information between the backend and the frontend. This class contains details about
 * the chat, such as the participant, the last message, and unread message count.
 */
@Data
public class ChatDTO {
  /**
   * The unique identifier of the chat. This is the primary key that identifies a specific chat in
   * the system.
   */
  private Long chatId;

  /**
   * The ID of the other participant in the chat. This ID represents the user involved in the chat,
   * excluding the current user.
   */
  private Long participantId;

  /**
   * The full name of the other participant in the chat. This field provides the name of the
   * participant that is part of the chat, excluding the current user.
   */
  private String participantName;

  /**
   * The details of the last message sent in the chat. This field contains information about the
   * most recent message exchanged in the chat.
   */
  private MessageDTO lastMessage;

  /**
   * The count of unread messages in the chat for the current user. This field keeps track of how
   * many messages in the chat have not been read by the current user.
   */
  private int unreadCount;
}
