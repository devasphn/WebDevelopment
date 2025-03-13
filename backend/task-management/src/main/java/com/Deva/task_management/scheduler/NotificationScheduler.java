package com.Deva.task_management.scheduler;

import com.Deva.task_management.model.Task;
import com.Deva.task_management.repository.TaskRepository;
import com.Deva.task_management.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class NotificationScheduler {

    private static final Logger logger = LoggerFactory.getLogger(NotificationScheduler.class);

    private final TaskRepository taskRepository;
    private final NotificationService notificationService;

    @Autowired
    public NotificationScheduler(TaskRepository taskRepository,
                                 NotificationService notificationService) {
        this.taskRepository = taskRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void checkUpcomingDeadlines() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourFromNow = now.plus(1, ChronoUnit.HOURS);

        List<Task> upcomingTasks = taskRepository.findByDeadlineBetween(now, oneHourFromNow);

        for (Task task : upcomingTasks) {
            String message = "Task '" + task.getTitle() + "' is due soon!";
            // Create in-app notification
            notificationService.createNotification(task.getUser_id(), message);
            logger.info("Created in-app notification for task: {}", task.getTitle());
        }
    }
}