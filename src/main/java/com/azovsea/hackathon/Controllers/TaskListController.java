package com.azovsea.hackathon.Controllers;


import com.azovsea.hackathon.Entities.TaskEntity;
import com.azovsea.hackathon.Repos.TaskRepository;
import com.azovsea.hackathon.Services.Impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/list")
public class TaskListController {

    private final TaskRepository repository;
    private final TaskServiceImpl service;

    @Autowired
    public TaskListController(TaskRepository repository,
                              TaskServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }

    @ModelAttribute(name = "tasks")
    public List<TaskEntity> entityList(){return repository.getAllByWorkedFalse();}

    @GetMapping
    public String showList(Model model){
        model.addAttribute("tasks", entityList());
        return "TaskList";
    }

    @PostMapping("take/{id}")
    public String takeTask(@PathVariable Long id){
        service.changeTaskStatus(id);
        return "TaskList";
    }






}
