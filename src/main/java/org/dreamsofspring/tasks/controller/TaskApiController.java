package org.dreamsofspring.tasks.controller;

import org.dreamsofspring.tasks.entity.Task;
import org.dreamsofspring.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController {
    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks(
            @RequestParam(required = false, name = "page") Optional<Integer> pageNumber,
            @RequestParam(required = false, name = "pageSize") Optional<Integer> pageSize,
            @RequestBody(required = false) Task filteringTask
    ) {
        return ResponseEntity.ok(
                this.taskService.getAllTasks(
                        filteringTask,
                        pageSize.orElse(Integer.MAX_VALUE),
                        pageNumber.orElse(1)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = this.taskService.getTask(id);

        return task != null ?
                ResponseEntity.ok(task) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = this.taskService.addTask(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable String id, @RequestBody Task task) {
        if (this.taskService.getTask(id) == null) {
            return ResponseEntity.notFound().build();
        }

        task.setId(id);
        Task updatedTask = this.taskService.updateTask(task);

        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable String id) {
        if (this.taskService.getTask(id) == null) {
            return ResponseEntity.notFound().build();
        }

        Task task = this.taskService.deleteTask(id);
        return ResponseEntity.ok(task);
    }
}
