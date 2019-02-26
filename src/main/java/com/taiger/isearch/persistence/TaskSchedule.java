package com.taiger.isearch.persistence;

import javax.persistence.*;

@Entity(name = "TASK_SCHEDULE")
public class TaskSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "CONNECTOR_ID")
    private Integer connectorId;
    @Column(name = "SCHEDULE")
    private String schedule;

    private TaskSchedule() {
    }

    public TaskSchedule(Integer connectorId, String schedule) {
        this.connectorId = connectorId;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
