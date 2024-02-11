package com.azovsea.hackathon.Controllers;

import com.azovsea.hackathon.Entities.TaskEntity;
import com.azovsea.hackathon.Repos.TaskRepository;
import com.azovsea.hackathon.Services.Impl.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/tasks")
@Slf4j
public class TaskUploadController {

    private final String UPLOAD_DIR = "src/main/resources/static/TaskFiles/";

    private final TaskRepository repository;
    private final TaskServiceImpl service;

    @Autowired
    public TaskUploadController(TaskRepository repository,
                                TaskServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }

    @ModelAttribute
    private TaskEntity task(){return new TaskEntity();}


    @GetMapping("/")
    public String showTaskForm(Model model) {
        model.addAttribute("taskBody", task());
        return "TaskUpload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @ModelAttribute("taskBody") TaskEntity task) {

        task.setTaskUploadTime();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());


        // Сохранение файла
        try {
            Path path = Paths.get(UPLOAD_DIR + task.getTaskUploadTime() + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            service.setInfoTask(task, String.valueOf(path),
                    task.getTaskTitle(),
                    task.getTaskDescription(),
                    task.getTaskCategory());
            repository.save(task);

            log.info("Saved: {} in path :{}", file,path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
