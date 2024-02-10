package com.azovsea.hackathon.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @Column(columnDefinition = "text")
    private String taskDescription;
    private String taskTitle;
    private String taskUploadTime;
    private String taskCategory;
    @Column(columnDefinition = "text")
    private String attachmentLink;
    private boolean worked;

    public void setTaskUploadTime(){this.taskUploadTime = new SimpleDateFormat("_dd.MM.yyyy_H.mm").format(new Date());}


}
