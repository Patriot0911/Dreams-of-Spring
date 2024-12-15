package org.dreamsofspring.tasks.repository;

import org.dreamsofspring.tasks.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.tasks t LEFT JOIN FETCH u.fbiAgents f WHERE u.id = :userId")
    Optional<UserEntity> findUserWithTasksAndFbi(@Param("userId") Long userId);

    /** Automatically from named query */
    Object user();
}
