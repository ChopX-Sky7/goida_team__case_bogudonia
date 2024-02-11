package com.azovsea.hackathon.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long newsId;
    @Column(columnDefinition = "text")
    private String newsTitle;
    private String newsDescription;
    private String newsTag;
    private String newsUploadTime;
    @Column(columnDefinition = "text")
    private String attachmentLink;
    private boolean moderated;

    public void setNewsUploadTime(){this.newsUploadTime = new SimpleDateFormat("_dd.MM.yyyy_H.mm_").format(new Date());}
}
