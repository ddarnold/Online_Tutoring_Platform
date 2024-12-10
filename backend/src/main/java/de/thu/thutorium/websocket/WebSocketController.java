package de.thu.thutorium.websocket;

import de.thu.thutorium.api.transferObjects.MessageDTO;
import de.thu.thutorium.services.interfaces.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocketController handles WebSocket messaging and facilitates real-time message sending through
 * WebSocket connections. This controller listens for messages sent from clients, saves them via the
 * message service, and broadcasts the messages to all subscribed clients.
 */
@RestController
public class WebSocketController {

  private final MessageService messageService;

  /**
   * Constructor for initializing the WebSocketController with the MessageService.
   *
   * @param messageService the service responsible for handling message operations
   */
  public WebSocketController(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * Handles the sending of a new message via WebSocket. This method listens for messages sent to
   * the "/sendMessage" destination, processes the message, saves it using the message service, and
   * then broadcasts the message to all clients subscribed to the "/topic/messages" destination.
   *
   * @param messageDTO the message data transfer object containing the message details
   * @return the saved message as a MessageDTO, which will be sent to all subscribers
   */
  @MessageMapping("/sendMessage")
  @SendTo("/topic/messages")
  public MessageDTO sendMessage(MessageDTO messageDTO) {
    // Save the message via the service
    return messageService.saveMessage(messageDTO);
  }
}
