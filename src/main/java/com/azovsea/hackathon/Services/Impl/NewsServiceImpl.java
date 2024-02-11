package com.azovsea.hackathon.Services.Impl;


import com.azovsea.hackathon.Entities.NewsEntity;
import com.azovsea.hackathon.Repos.NewsRepository;
import com.azovsea.hackathon.Services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository repository;



    @Autowired
    public NewsServiceImpl(NewsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(NewsEntity n) {
        repository.save(n);
    }

    @Override
    public List<NewsEntity> getAllNotModerated() {
        return repository.getAllByModeratedFalse();
    }

    @Override
    public List<NewsEntity> getAllModerated() {
        return repository.getAllByModeratedTrue();
    }

    @Override
    public void setInfo(NewsEntity entity,
                        String title,
                        String desc,
                        String link,
                        String tag,
                        boolean b) {
        entity.setModerated(b);
        entity.setNewsTitle(title);
        entity.setNewsDescription(desc);
        entity.setAttachmentLink(link);
        entity.setNewsTag(tag);
    }

    @Override
    public void changeStatus(Long id, boolean b) {
        repository.changeStatus(id, b);
    }
}

