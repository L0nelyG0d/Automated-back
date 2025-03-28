package com.example.automated.controller;

import com.example.automated.model.TaskList;
import com.example.automated.service.TaskListService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
@RestController
@RequestMapping("/taskList")
public class TaskListController {

    private final TaskListService taskListService;

    @Autowired
    TaskListController(TaskListService taskListService){
        this.taskListService = taskListService;
    }

    @GetMapping("/getList/{id}")
    public ResponseEntity<List<TaskList>> getListsByBoard(@PathVariable Long id) {
        List<TaskList> taskLists = taskListService.getListsByBoard(id);

        if (taskLists.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(taskLists);
    }

    @PostMapping("/addAList")
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList){
        TaskList task = taskListService.createTaskList(taskList);

        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/deleteTheList/{id}")
    public ResponseEntity<Void> DeleteTaskList(@PathVariable Long id){
        try{
            taskListService.deleteTaskList(id);
            return ResponseEntity.noContent().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
