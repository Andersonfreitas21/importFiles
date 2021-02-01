package com.anderson.files.domain.data.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "set")
@Table(schema = "teste", name = "exemplo")
public class Exemplo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private String name;
  private String sobreNome;

  //    @ElementCollection(fetch = FetchType.EAGER)
  //    @CollectionTable(
  //            name = "cancelamanto_motivo",
  //            joinColumns = @JoinColumn(name = "id_cancelamento"))
  //    @Column(name = "id_motivo", columnDefinition = "BINARY(16)")
  //    private Set<UUID> motivos;

  @Setter(AccessLevel.PRIVATE)
  @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Setter(AccessLevel.PRIVATE)
  @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime updatedAt;

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
  }

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }
}
