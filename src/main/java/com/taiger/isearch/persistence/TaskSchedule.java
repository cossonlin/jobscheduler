package com.taiger.isearch.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "TASK_SCHEDULE")
public class TaskSchedule {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "CONNECTOR_ID")
    private Integer connectorId;
    @Column(name = "SCHEDULE")
    private String schedule;

    public TaskSchedule(Integer connectorId, String schedule) {
        this.connectorId = connectorId;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }
}
