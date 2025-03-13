package com.Deva.task_management.repository;

import com.Deva.task_management.model.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

    List<Long> findDependencyIdByTaskId(Long taskId);
}