package de.thu.thutorium.database.dbObjects;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * FOR MR JOSSIN
 *
 * <p>Why do we need it?
 *
 * <p>Extensibility is a Priority (Long-Term Focus) Efficient Querying Support for Group Chats
 */

/**
 * The ChatDBO (Database Object) class represents a chat entity in the database. It defines the
 * structure of the chat data as stored in the "chat" table. This class contains information about
 * the chat, such as its participants, messages, and creation time.
 */
@Builder
@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDBO {
  /**
   * The unique identifier of the chat. This is the primary key for the "chat" table and uniquely
   * identifies each chat.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chatId;

  /**
   * The timestamp when the chat was created. This field stores the date and time when the chat was
   * first created. It is marked as nullable = false and updatable = false to ensure it cannot be
   * modified after creation.
   */
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  /**
   * The list of participants in the chat. This represents the users involved in the chat, stored in
   * the "chat_participants" join table. It is a many-to-many relationship between the chat and user
   * entities.
   */
  @ManyToMany
  @JoinTable(
      name = "chat_participants",
      joinColumns = @JoinColumn(name = "chat_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<UserDBO> participants;

  /**
   * The list of messages associated with the chat. This is a one-to-many relationship with the
   * `MessageDBO` entity, representing all messages sent within the chat. The `cascade =
   * CascadeType.ALL` ensures that messages are automatically persisted or deleted when the chat is
   * saved or deleted. The `orphanRemoval = true` ensures that any message that is removed from the
   * chat will be deleted from the database.
   */
  @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MessageDBO> messages;
}
