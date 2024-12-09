package de.thu.thutorium.database.dbObjects;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
/**
 * FOR MR JOSSIN
 *
 * Why do we need it?
 *
 * Extensibility is a Priority (Long-Term Focus)
 * Efficient Querying
 * Support for Group Chats
 */

@Builder
@Entity
@Table(name = "chat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDBO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "chat_participants",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserDBO> participants;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageDBO> messages;
}
