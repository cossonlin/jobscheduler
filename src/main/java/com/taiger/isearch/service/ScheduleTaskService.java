package com.taiger.isearch.service;

import com.taiger.isearch.persistence.TaskSchedule;
import com.taiger.isearch.persistence.TaskScheduleRepo;
import com.taiger.isearch.task.Task1;
import com.taiger.isearch.task.Task2;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
@Transactional(rollbackOn = Exception.class)
public class ScheduleTaskService {

    private static Map<Long, ScheduledFuture<?>> jobsMap = new ConcurrentHashMap<>();
    private final TaskScheduleRepo repo;
    private TaskScheduler scheduler;

    public ScheduleTaskService(TaskScheduler scheduler, TaskScheduleRepo repo) {
        this.scheduler = scheduler;
        this.repo = repo;
    }

    @PostConstruct
    private void scheduleAllTasksFromDb() throws Exception {
        List<TaskSchedule> taskSchedules = loadAllTasks();
        Runnable task;
        for (TaskSchedule taskSchedule : taskSchedules) {
            switch (taskSchedule.getConnectorId()) {
                case 1:
                    task = new Task1();
                    break;
                case 2:
                    task = new Task2();
                    break;
                default:
                    throw new Exception();
            }
            ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(taskSchedule.getSchedule()));
            jobsMap.put(taskSchedule.getId(), scheduledTask);
        }
    }

    public List<TaskSchedule> loadAllTasks() {
        return repo.findAll();
    }

    public boolean addTask(Runnable task, int connectorId, String cronExpression) {
        TaskSchedule entity = new TaskSchedule(connectorId, cronExpression);
        entity = repo.save(entity);
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, new CronTrigger(cronExpression));
        jobsMap.put(entity.getId(), scheduledTask);
        return true;
    }

    public boolean removeTask(long id) {
        boolean result = false;
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            repo.deleteById(id);
            scheduledTask.cancel(true);
            jobsMap.remove(id);
            result = true;
        }
        return result;
    }
}