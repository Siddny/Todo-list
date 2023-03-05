package com.sunflashtech.interview.repositories;

import com.sunflashtech.interview.dtos.TaskResponseDTO;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sunflashtech.interview.models.Task;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  Task findByName(String name);

  @Transactional
  void deleteByName(String name);

//  Task findByPriorityLevel(String priorityLevel);

//    public List<TaskResponseDTO> findByCompletedTrue();

}
