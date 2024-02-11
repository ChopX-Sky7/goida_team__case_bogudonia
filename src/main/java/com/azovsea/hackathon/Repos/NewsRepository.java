package com.azovsea.hackathon.Repos;

import com.azovsea.hackathon.Entities.NewsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {


    NewsEntity getByNewsId(Long id);
    List<NewsEntity> getAllByNewsTag(String tag);
    List<NewsEntity> getAllByModeratedFalse();
    List<NewsEntity> getAllByModeratedTrue();

    @Modifying
    @Transactional
    @Query("UPDATE NewsEntity n SET n.moderated = :work WHERE n.newsId = :id")
    void changeStatus(@Param("id") Long taskId,
                      @Param("work") boolean work);



}
