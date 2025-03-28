package com.example.automated.repository;

import com.example.automated.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    List<TaskList> findByBoardId(Long boardId);
}