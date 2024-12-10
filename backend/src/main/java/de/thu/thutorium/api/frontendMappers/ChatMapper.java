package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.api.transferObjects.ChatDTO;
import de.thu.thutorium.database.dbObjects.ChatDBO;
import de.thu.thutorium.database.dbObjects.MessageDBO;
import de.thu.thutorium.database.dbObjects.UserDBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

/**
 * The ChatMapper interface is responsible for mapping between ChatDBO objects (database objects)
 * and ChatDTO objects (data transfer objects) used in the API layer. It uses MapStruct for
 * automatic mapping and defines custom logic for participant-related mappings.
 */
@Mapper(uses = MessageMapper.class)
public interface ChatMapper {
  // A static instance of the mapper, used to perform the mapping
  ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

  /**
   * Converts a ChatDBO object to a ChatDTO object. This method applies custom mapping logic for the
   * participant's ID and name, the last message in the chat, and the unread message count.
   *
   * @param chat the ChatDBO object representing the chat data from the database
   * @param currentUserId the ID of the current user, used to determine the participant and unread
   *     messages
   * @param lastMessage an optional MessageDBO object representing the last message in the chat, if
   *     any
   * @return a ChatDTO object with mapped chat data
   */
  @Mapping(target = "participantId", expression = "java(mapParticipantId(chat, currentUserId))")
  @Mapping(target = "participantName", expression = "java(mapParticipantName(chat, currentUserId))")
  @Mapping(
      target = "lastMessage",
      expression =
          "java(lastMessage.map(msg -> MessageMapper.INSTANCE.toDTO(msg, currentUserId)).orElse(null))")
  @Mapping(
      target = "unreadCount",
      expression =
          "java((int) chat.getMessages().stream().filter(msg -> !msg.getIsRead() && msg.getReceiver().getUserId().equals(currentUserId)).count())")
  ChatDTO toDTO(ChatDBO chat, Long currentUserId, Optional<MessageDBO> lastMessage);

  /**
   * Determines the participant ID for a chat, excluding the current user. It iterates through the
   * participants in the chat and finds a user ID that is not equal to the current user ID.
   *
   * @param chat the ChatDBO object representing the chat
   * @param currentUserId the ID of the current user
   * @return the user ID of the participant in the chat, or null if no participant is found
   */
  default Long mapParticipantId(ChatDBO chat, Long currentUserId) {
    return chat.getParticipants().stream()
        .map(UserDBO::getUserId)
        .filter(userId -> !userId.equals(currentUserId)) // Exclude the current user
        .findFirst()
        .orElse(null); // Return null if no participant is found
  }

  /**
   * Determines the participant's full name for a chat, excluding the current user. It iterates
   * through the participants in the chat and finds the name of the user that is not the current
   * user.
   *
   * @param chat the ChatDBO object representing the chat
   * @param currentUserId the ID of the current user
   * @return the full name of the participant, or null if no participant is found
   */
  default String mapParticipantName(ChatDBO chat, Long currentUserId) {
    return chat.getParticipants().stream()
        .filter(user -> !user.getUserId().equals(currentUserId)) // Exclude the current user
        .map(user -> user.getFirstName() + " " + user.getLastName())
        .findFirst()
        .orElse(null); // Return null if no participant is found
  }
}
