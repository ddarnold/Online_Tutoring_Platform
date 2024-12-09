package de.thu.thutorium.services.interfaces;

import de.thu.thutorium.database.dbObjects.MessageDBO;

import java.util.List;

public interface MessageService {
    MessageDBO saveMessage(MessageDBO message);
    List<MessageDBO> getMessageByChatId(Long chatId);
}
