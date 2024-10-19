package org.dreamsofspring.lab3.controller;

import org.dreamsofspring.lab3.entity.Task;
import org.dreamsofspring.lab3.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTasks(
        @RequestParam(required = false) String sortBy,
        @RequestParam(required = false) String order,
        Model model
    ) {
        if (sortBy != null) {
            model.addAttribute("tasks", taskService.getSortedTasks(sortBy, order));
        } else {
            model.addAttribute("tasks", taskService.getAllTasks());
        }
        model.addAttribute("new_task", taskService.protoTask());
        model.addAttribute("edit_task", taskService.protoTask());
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

    @PutMapping()
    public String updateTask(
        @ModelAttribute Task task
    ) {
        taskService.updateTask(task);
        System.out.println(task.getId());
        return "redirect:/tasks";
    }
}
