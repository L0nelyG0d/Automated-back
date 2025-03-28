package com.example.automated.repository;

import com.example.automated.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByListId(Long listId);
    List<Task> findByAssignedToId(Long userId);
}