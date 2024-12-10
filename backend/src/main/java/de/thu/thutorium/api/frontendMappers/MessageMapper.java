package de.thu.thutorium.api.frontendMappers;

import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.database.dbObjects.MessageDBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The MessageMapper interface is responsible for mapping between MessageDBO objects (database
 * objects) and MessageDTO objects (data transfer objects) used in the API layer. It uses MapStruct
 * to automatically map between the two data structures and defines explicit mappings for specific
 * fields.
 */
@Mapper
public interface MessageMapper {
  // A static instance of the mapper, used to perform the mapping
  MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

  /**
   * Converts a MessageDBO object to a MessageDTO object. This method defines custom mapping logic
   * to map the sender's ID, receiver's ID, and read status.
   *
   * @param message the MessageDBO object representing the message data from the database
   * @param currentUserId the ID of the current user, used to potentially modify the DTO data
   * @return a MessageDTO object with mapped message data
   */
  @Mapping(target = "senderId", source = "message.sender.userId") // Extract sender ID
  @Mapping(target = "receiverId", source = "message.receiver.userId") // Extract receiver ID
  @Mapping(target = "read", source = "message.isRead") // Explicitly map isRead to read
  MessageDTO toDTO(MessageDBO message, Long currentUserId);

  /**
   * Converts a MessageDTO object to a MessageDBO object. This method maps a MessageDTO back to the
   * MessageDBO entity used for database operations.
   *
   * @param dto the MessageDTO object to be converted to an entity
   * @return a MessageDBO object representing the message data to be saved in the database
   */
  MessageDBO toEntity(MessageDTO dto);
}
