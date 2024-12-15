package org.dreamsofspring.tasks.mapper;

import org.dreamsofspring.tasks.entity.FbiEntity;
import org.dreamsofspring.tasks.entity.Task;
import org.dreamsofspring.tasks.entity.TaskEntity;
import org.dreamsofspring.tasks.repository.TaskDao;

import java.util.List;

public record UserDto(String name, List<TaskEntity> tasks, List<FbiEntity> fbiAgents) {
    public List<UserDto> getFbiAgents() {
        return this.fbiAgents.stream().map(a -> new UserDto("BOTTOM SECRET", List.of(), List.of())).toList();
    }
}