package com.taiger.isearch.controller;

import com.taiger.isearch.persistence.TaskSchedule;
import com.taiger.isearch.service.ScheduleTaskService;
import com.taiger.isearch.task.Task1;
import com.taiger.isearch.task.Task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/taskschedule")
public class TaskSchedulerController {

    @Autowired
    private ScheduleTaskService service;

    @GetMapping(path = "/loadAllSchedules", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadAllSchedules() {
        List<TaskSchedule> result = service.loadAllTasks();
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/addSchedule1", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule1(@RequestParam(name = "cronExpression") String cronExpression) throws IOException {
        boolean result = service.addTask(new Task1(), 1, cronExpression);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/addSchedule2", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule2(@RequestParam(name = "cronExpression") String cronExpression) throws IOException {
        boolean result = service.addTask(new Task2(), 2, cronExpression);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/deleteSchedule/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSchedule(@PathVariable long id) {
        boolean result = service.removeTask(id);
        return ResponseEntity.ok(result);
    }
}
