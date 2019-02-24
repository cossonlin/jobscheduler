package com.taiger.isearch.controller;

import com.taiger.isearch.service.ScheduleTaskService;
import com.taiger.isearch.task.Task1;
import com.taiger.isearch.task.Task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/taskschedule")
public class TaskSchedulerController {

    @Autowired
    private ScheduleTaskService service;

    @PostMapping(path = "/addSchedule1", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule1(@RequestParam String cronExpression) {
        boolean result = service.addTask(new Task1(), 1, cronExpression);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/addSchedule2", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addSchedule2(@RequestParam String cronExpression) {
        boolean result = service.addTask(new Task2(), 2, cronExpression);
        return ResponseEntity.ok(result);
    }
}
