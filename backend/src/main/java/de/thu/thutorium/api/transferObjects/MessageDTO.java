package de.thu.thutorium.api.transferObjects;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * The MessageDTO class represents a Data Transfer Object (DTO) for a message. It is used to
 * transfer message-related information between the backend and the frontend. This class contains
 * details about the message, such as the sender, receiver, content, send time, and read status.
 */
@Data
public class MessageDTO {
  /**
   * The unique identifier of the message. This is the primary key that identifies a specific
   * message in the system.
   */
  private Long messageId;

  /** The ID of the sender of the message. This field represents the user who sent the message. */
  private Long senderId;

  /**
   * The ID of the receiver of the message. This field represents the user who is supposed to
   * receive the message.
   */
  private Long receiverId;

  /**
   * The content of the message. This field stores the text or other content that is part of the
   * message.
   */
  private String messageContent;

  /**
   * The timestamp when the message was sent. This field stores the exact date and time when the
   * message was created and sent.
   */
  private LocalDateTime sendAt;

  /**
   * The read status of the message. This field indicates whether the message has been read by the
   * receiver. It is true if the message has been read, and false if it has not been read.
   */
  private boolean isRead;
}
