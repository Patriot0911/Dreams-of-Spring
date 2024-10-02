package org.example.lab2.controller;

import org.example.lab2.entity.Task;
import org.example.lab2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    @Qualifier("TaskService")
    private TaskService taskService;

    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list";
    }

    @PostMapping
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PutMapping
    public String updateTask(@ModelAttribute Task task, Model model) {
        Task updatedTask = taskService.updateTask(task);
        model.addAttribute("task", updatedTask);
        return "task-edit";
    }
}
