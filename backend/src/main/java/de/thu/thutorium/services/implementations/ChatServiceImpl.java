package de.thu.thutorium.services.implementations;

import de.thu.thutorium.database.dbObjects.ChatDBO;
import de.thu.thutorium.database.dbObjects.MessageDBO;
import de.thu.thutorium.database.repositories.ChatRepository;
import de.thu.thutorium.database.repositories.MessageRepository;
import de.thu.thutorium.services.interfaces.ChatService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    @Override
    public List<ChatDBO> getAllChatsForUser(long userId) {
        return chatRepository.findAll().stream()
                .filter(chat -> chat.getParticipants().stream().anyMatch(user -> user.getUserId().equals(userId)))
                .collect(Collectors.toList());
    }

    @Override
    public ChatDBO getChatById(long chatId) {
    return chatRepository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found with id " + chatId));
    }

    @Override
    public List<MessageDBO> getChatHistory(long chatId) {
        return messageRepository.findByChatChatIdOrderBySendAtAsc(chatId);
    }

    @Override
    public MessageDBO saveMessage(MessageDBO message) {
        return messageRepository.save(message);
    }
}
