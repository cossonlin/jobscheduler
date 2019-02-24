package com.taiger.isearch.service;

import com.taiger.isearch.persistence.TaskSchedule;
import com.taiger.isearch.persistence.TaskScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@Service
public class ScheduleTaskService {

    private final TaskScheduler scheduler;
    private final TaskScheduleRepo repo;

    // A map for keeping scheduled tasks
    private Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

    @Autowired
    public ScheduleTaskService(TaskScheduler scheduler, TaskScheduleRepo repo) {
        this.scheduler = scheduler;
        this.repo = repo;
    }

    public List<TaskSchedule> loadAllTasks() {
        return repo.findAll();
    }

    @Transactional
    public boolean addTask(Runnable task, int connectorId, String cronExpression) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
        TaskSchedule entity = new TaskSchedule(connectorId, cronExpression);
        entity = repo.save(entity);
        jobsMap.put(entity.getId(), scheduledTask);
        return true;
    }

    @Transactional
    public boolean removeTask(long id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.remove(id);
        }
        return true;
    }

}