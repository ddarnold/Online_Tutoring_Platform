package de.thu.thutorium.database.dbObjects;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "role")
@Data
public class RoleDBO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role_name", unique = true, nullable = false)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<UserDBO> users;
}
