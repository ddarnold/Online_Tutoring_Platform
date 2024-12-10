package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.services.interfaces.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The MessageController class provides RESTful endpoints for managing and retrieving messages. It
 * exposes methods to send a new message and retrieve messages for a specific chat.
 */
@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  /**
   * Constructs a new MessageController.
   *
   * @param messageService the service used to manage message data
   */
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * Send a new message. This endpoint allows a user to send a new message by providing the message
   * details in the request body.
   *
   * @param messageDTO the message data to be saved
   * @return the saved MessageDTO object representing the sent message
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO) {
    return messageService.saveMessage(messageDTO);
  }

  /**
   * Get all messages for a specific chat. This endpoint retrieves a list of all messages associated
   * with a given chat ID for a specific user.
   *
   * @param chatId the ID of the chat whose messages are to be retrieved
   * @param currentUserId the ID of the user requesting the messages
   * @return a list of MessageDTO objects representing the messages in the specified chat
   */
  @GetMapping("/chat/{chatId}")
  public List<MessageDTO> getMessagesByChatId(
      @PathVariable Long chatId, @RequestParam Long currentUserId) {
    return messageService.getMessageByChatId(chatId, currentUserId);
  }
}
