package org.dreamsofspring.tasks.repository;

import org.dreamsofspring.tasks.entity.FbiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FbiRepository extends JpaRepository<FbiEntity, Long> {
    void deleteFbiEntityById(Number id);
}