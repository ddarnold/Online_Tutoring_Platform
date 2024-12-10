package de.thu.thutorium.api.controllers;

import de.thu.thutorium.api.transferObjects.ChatDTO;
import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.services.interfaces.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The ChatController class provides RESTful endpoints for managing and retrieving chat data. It
 * exposes methods to get all chats for a user, get a specific chat by its ID, and get chat history.
 */
@RestController
@RequestMapping("/api/chats")
public class ChatController {

  private final ChatService chatService;

  /**
   * Constructs a new ChatController.
   *
   * @param chatService the service used to manage chat data
   */
  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  /**
   * Example with Spring Security Token @GetMapping("/{chatId}") public ChatDTO
   * getChatById(@PathVariable Long chatId) { Long currentUserId =
   * SecurityContextHolder.getContext().getAuthentication().getPrincipal(); return
   * chatService.getChatById(chatId, currentUserId); }
   */

  /**
   * Get all chats for a given user. This endpoint retrieves a list of all chats associated with the
   * specified user.
   *
   * @param userId the ID of the user whose chats are to be retrieved
   * @return a list of ChatDTO objects representing the user's chats
   */
  @GetMapping("/user/{userId}")
  public List<ChatDTO> getAllChatsForUser(@PathVariable Long userId) {
    return chatService.getAllChatsForUser(userId);
  }

  /**
   * Get a specific chat by its ID. This endpoint retrieves a chat by its ID for a given user.
   *
   * @param chatId the ID of the chat to retrieve
   * @param userId the ID of the user requesting the chat
   * @return a ChatDTO object representing the requested chat
   */
  @GetMapping("/{chatId}")
  public ChatDTO getChatById(@PathVariable Long chatId, @RequestParam Long userId) {
    return chatService.getChatById(chatId, userId);
  }

  /**
   * Get the chat history for a given chat. This endpoint retrieves the message history of a
   * specific chat for a user.
   *
   * @param chatId the ID of the chat whose history is to be retrieved
   * @param currentUserId the ID of the user requesting the chat history
   * @return a list of MessageDTO objects representing the chat's message history
   */
  @GetMapping("/{chatId}/history")
  public List<MessageDTO> getChatHistory(
      @PathVariable Long chatId, @RequestParam Long currentUserId) {
    return chatService.getChatHistory(chatId, currentUserId);
  }
}
