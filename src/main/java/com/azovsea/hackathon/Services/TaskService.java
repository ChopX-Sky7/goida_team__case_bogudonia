package com.azovsea.hackathon.Services;

import com.azovsea.hackathon.Entities.TaskEntity;

public interface TaskService {

    void setInfoTask(TaskEntity task,
                     String filePath,
                     String title,
                     String desc,
                     String category);
    void changeTaskStatus(TaskEntity task);


}
