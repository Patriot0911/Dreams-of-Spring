package org.example.lab2.controller;

import org.example.lab2.entity.Task;
import org.example.lab2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("new_task", taskService.protoTask());
        return "task-list";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable String id, Model model) {
        model.addAttribute("specific_task", taskService.getTask(id));
        return "redirect:/tasks";
    }

    @PostMapping
    public String addTask(@ModelAttribute("new_task") Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateTask(
        // @ModelAttribute("task") Task task
        @RequestBody Task task
    ) {
        taskService.updateTask(task);
        System.out.println(task.getId());
        return "redirect:/tasks";
    }
}
