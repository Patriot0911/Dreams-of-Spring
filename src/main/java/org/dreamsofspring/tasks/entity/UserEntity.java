package org.dreamsofspring.tasks.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Entity
@Table(name = "USERS")
@NamedQuery(name = "UserEntity.user", query = "SELECT u FROM UserEntity u")
public class UserEntity {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Setter
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<FbiEntity> fbiAgents;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TaskEntity> tasks;
}
