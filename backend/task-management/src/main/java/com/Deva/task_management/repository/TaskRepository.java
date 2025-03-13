package com.Deva.task_management.repository;

import com.Deva.task_management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    List<Task> findByDeadlineBetween(LocalDateTime start, LocalDateTime end);
}
