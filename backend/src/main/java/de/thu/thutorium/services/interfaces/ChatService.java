package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.database.dbObjects.ChatDBO;
import de.thu.thutorium.database.dbObjects.MessageDBO;

import java.util.List;

public interface ChatService {
    List<ChatDBO> getAllChatsForUser(long userId);
    ChatDBO getChatById(long chatId);
    List<MessageDBO> getChatHistory(long userId);
    MessageDBO saveMessage(MessageDBO message);
}
