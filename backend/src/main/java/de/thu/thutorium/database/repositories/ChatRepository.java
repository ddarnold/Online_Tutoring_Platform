package de.thu.thutorium.database.repositories;

import de.thu.thutorium.database.dbObjects.ChatDBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing {@link ChatDBO} entities
 * in the database.
 * This interface extends {@link JpaRepository}, providing CRUD operations and
 * additional query methods
 * for the {@code ChatDBO} entity.
 */
@Repository
public interface ChatRepository extends JpaRepository<ChatDBO, Long> { }
