package org.dreamsofspring.tasks.controller;

import org.dreamsofspring.tasks.controller.interfaces.TaskApiControllerInterface;
import org.dreamsofspring.tasks.entity.Task;
import org.dreamsofspring.tasks.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController implements TaskApiControllerInterface {
    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            @RequestParam(required = false, name = "page") Optional<Integer> pageNumber,
            @RequestParam(required = false, name = "pageSize") Optional<Integer> pageSize,
            @RequestParam(required = false) MultiValueMap<String, String> params
    ) {
        return ResponseEntity.ok(
                this.taskService.getTasks(
                        pageSize.orElse(Integer.MAX_VALUE),
                        pageNumber.orElse(0),
                        params
                )
        );
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(
                this.taskService.getAllTasks()
        );
    }

    @Override
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getAllCompleted(@RequestParam(required = true) Boolean state) {
        return ResponseEntity.ok(
                this.taskService.findCompletedTasks(state)
        );
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = this.taskService.getTask(id);

        return task != null ?
                ResponseEntity.ok(task) :
                ResponseEntity.notFound().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = this.taskService.addTask(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskById(@PathVariable String id, @RequestBody Task task) {
        if (this.taskService.getTask(id) == null) {
            return ResponseEntity.notFound().build();
        }

        task.setId(id);
        Task updatedTask = this.taskService.updateTask(task);

        return ResponseEntity.ok(updatedTask);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable String id) {
        if (this.taskService.getTask(id) == null) {
            return ResponseEntity.noContent().build();
        }

        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
