package com.example.automated.service;

import com.example.automated.repository.TaskListRepository;
import com.example.automated.model.TaskList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    @Autowired
    TaskListService(TaskListRepository taskListRepository){
        this.taskListRepository = taskListRepository;
    }

    public List<TaskList> getListsByBoard(Long boardId){
        return taskListRepository.findByBoardId(boardId);
    }

    public TaskList createTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public void deleteTaskList(Long id) {
        taskListRepository.deleteById(id);
    }
}
