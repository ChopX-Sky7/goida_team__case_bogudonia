package com.azovsea.hackathon.Services.Impl;

import com.azovsea.hackathon.Entities.TaskEntity;
import com.azovsea.hackathon.Repos.TaskRepository;
import com.azovsea.hackathon.Services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    @Autowired
    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public void setInfoTask(TaskEntity task,
                            String filePath,
                            String title,
                            String desc,
                            String category) {
        task.setAttachmentLink(filePath);
        task.setTaskTitle(title);
        task.setTaskDescription(desc);
        task.setTaskCategory(category);
        task.setWorked(false);

    }

    @Override
    public void changeTaskStatus(Long id) {
        repo.changeStatus(id, true);
    }
}
