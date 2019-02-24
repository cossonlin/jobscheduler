package com.taiger.isearch.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskScheduleRepo extends JpaRepository<TaskSchedule, Long> {
}
