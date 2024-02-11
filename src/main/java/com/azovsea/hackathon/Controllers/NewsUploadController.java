package com.azovsea.hackathon.Controllers;


import com.azovsea.hackathon.Entities.NewsEntity;
import com.azovsea.hackathon.Services.Impl.NewsServiceImpl;
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
@Slf4j
@RequestMapping("/news")
public class NewsUploadController {

    private final String UPLOAD_DIR = "src/main/resources/static/NewsFiles/";

    private final NewsServiceImpl service;

    @Autowired
    public NewsUploadController(NewsServiceImpl service) {
        this.service = service;
    }

    @ModelAttribute
    public NewsEntity getNewEntity(){return new NewsEntity();}

    @GetMapping
    public String showForm(Model model){
        model.addAttribute("newsBody", getNewEntity());
        return "NewsForm";
    }

    @PostMapping("/add")
    public String uploadNews(@RequestParam("file") MultipartFile file,
                             @ModelAttribute("newsBody") NewsEntity news){
        news.setNewsUploadTime();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // Сохранение файла
        try {
            Path path = Paths.get(UPLOAD_DIR + news.getNewsUploadTime() + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            service.setInfo(news,
                    news.getNewsTitle(),
                    news.getNewsDescription(),
                    String.valueOf(path),
                    news.getNewsTag(), false);
            service.save(news);
            log.info("Saved: {} in path :{}", file, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/list";
    }

}
