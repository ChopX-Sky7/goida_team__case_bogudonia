package com.azovsea.hackathon.Repos;

import com.azovsea.hackathon.Entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    public TaskEntity getByTaskCategory(String category);
    public TaskEntity getByTaskId(Long id);
    public List<TaskEntity> getAllByWorkedFalse();


}