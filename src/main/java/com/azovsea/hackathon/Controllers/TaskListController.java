package com.azovsea.hackathon.Controllers;

import com.azovsea.hackathon.Entities.TaskEntity;
import com.azovsea.hackathon.Repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/list")
public class TaskListController {

    private final TaskRepository repository;

    @Autowired
    public TaskListController(TaskRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute(name = "tasks")
    public List<TaskEntity> entityList(){return repository.getAllByWorkedFalse();}

    @GetMapping
    public String showList(Model model){
        model.addAttribute("tasks", entityList());
        return "TaskList";
    }

    @PostMapping
    public String takeTask(){
        return "redirect:/TaskList";
    }

}
