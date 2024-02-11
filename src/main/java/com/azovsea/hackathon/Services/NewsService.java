package com.azovsea.hackathon.Services;

import com.azovsea.hackathon.Entities.NewsEntity;

import java.util.List;

public interface NewsService {


    void save(NewsEntity n);

    List<NewsEntity> getAllNotModerated();
    List<NewsEntity> getAllModerated();

    void setInfo(NewsEntity entity,
                 String title,
                 String desc,
                 String link,
                 String category,
                 boolean b);

    void changeStatus(Long id, boolean b);

}
