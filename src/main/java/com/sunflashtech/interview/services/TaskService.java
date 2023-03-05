package com.sunflashtech.interview.services;

import com.sunflashtech.interview.dtos.TaskResponseDTO;
import com.sunflashtech.interview.exceptions.CustomException;
import com.sunflashtech.interview.models.Task;
import com.sunflashtech.interview.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class TaskService {
  
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Task createTask(Task taskDTO) {
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setCompleted(taskDTO.getCompleted());
        task.setPriorityLevel(taskDTO.getPriorityLevel());
        taskRepository.save(task);
            return task;
    }

    public void delete(String name) {
      taskRepository.deleteByName(name);
    }

    public void delete(int id){
        taskRepository.deleteById(id);
    }
    
    public Task search(String name) {
      Task task = taskRepository.findByName(name);
      if (task == null) {
        throw new CustomException("The task doesn't exist", HttpStatus.NOT_FOUND);
      }
      return task;
    }
    
    public List<TaskResponseDTO> all(){
        return taskRepository.findAll().stream()
        .map(task -> modelMapper
        .map(task, TaskResponseDTO.class))
        .collect(Collectors.toList());
    }

    public TaskResponseDTO update(int id,TaskResponseDTO request){

        Task task = modelMapper.map(request, Task.class);

        Optional<Task> retrievedTask = taskRepository.findById(id);
        if(!retrievedTask.isPresent()){
            throw new CustomException("Invalid task id", HttpStatus.BAD_REQUEST);
        }

        task.setId(retrievedTask.get().getId());

        return modelMapper.map(taskRepository.save(task), TaskResponseDTO.class);        
    }
    
    public TaskResponseDTO isComplete(int id,TaskResponseDTO request){
        Task task = modelMapper.map(request, Task.class);
        Optional<Task> retrievedTask = taskRepository.findById(id);
        if(!retrievedTask.isPresent()){
            throw new CustomException("Invalid task id", HttpStatus.BAD_REQUEST);
        }
        task.setId(retrievedTask.get().getId());
        task.setCompleted(Boolean.TRUE);
        return modelMapper.map(taskRepository.save(task), TaskResponseDTO.class);        
    }

}
