package de.thu.thutorium.services.implementations;

import de.thu.thutorium.database.dbObjects.MessageDBO;
import de.thu.thutorium.database.repositories.MessageRepository;
import de.thu.thutorium.services.interfaces.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private  MessageRepository messageRepository;

    @Override
    public MessageDBO saveMessage(MessageDBO message) {
        return messageRepository.save(message);
    }

    @Override
    public List<MessageDBO> getMessageByChatId(Long chatId) {
        return messageRepository.findByChatChatIdOrderBySendAtAsc(chatId);
    }
}
