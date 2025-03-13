package com.Deva.task_management.service;

import com.Deva.task_management.model.Task;
import com.Deva.task_management.repository.TaskRepository;
import com.Deva.task_management.repository.TaskDependencyRepository; // Import the new repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskDependencyRepository taskDependencyRepository; // Inject the new repository

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskDependencyRepository taskDependencyRepository) {
        this.taskRepository = taskRepository;
        this.taskDependencyRepository = taskDependencyRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setDeadline(taskDetails.getDeadline());
        task.setPriority(taskDetails.getPriority());
        task.setUser_id(taskDetails.getUser_id());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean taskExists(Long id) {
        return taskRepository.existsById(id);
    }

    public List<Long> getTaskDependencies(Long taskId) {  // Modified to return Long IDs
        return taskDependencyRepository.findDependencyIdByTaskId(taskId);
    }
}
