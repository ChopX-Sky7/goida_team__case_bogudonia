package com.azovsea.hackathon.Repos;

import com.azovsea.hackathon.Entities.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    TaskEntity getByTaskCategory(String category);
    TaskEntity getByTaskId(Long id);
    List<TaskEntity> getAllByWorkedFalse();

    @Modifying
    @Transactional
    @Query("UPDATE TaskEntity t SET t.worked = :work WHERE t.taskId = :id")
    void changeStatus(@Param("id") Long taskId,
                      @Param("work") boolean work);



}
