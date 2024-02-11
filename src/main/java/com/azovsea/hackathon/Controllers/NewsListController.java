package com.azovsea.hackathon.Controllers;

import com.azovsea.hackathon.Entities.NewsEntity;
import com.azovsea.hackathon.Services.Impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/newslist")
public class NewsListController {

    private final NewsServiceImpl service;


    @Autowired
    public NewsListController(NewsServiceImpl service) {
        this.service = service;
    }

    @ModelAttribute
    List<NewsEntity> getNewsList(){return service.getAllNotModerated();}

    @ModelAttribute
    List<NewsEntity> getModeratedList(){return service.getAllModerated();}

    @GetMapping
    public String showList(Model model){
        model.addAttribute("newsList", getNewsList());
        return "NewsList";
    }

    @PostMapping("/moder/{id}")
    public String moderPost(@PathVariable Long id){
        service.changeStatus(id, true);
        return "Home";
    }

    @GetMapping("/moderated")
    public String showModeratedNews(Model model){
        model.addAttribute("moderatedList", getModeratedList());
        return "NewsModerated";
    }
}
