package com.sunflashtech.interview.controllers;

import com.sunflashtech.interview.dtos.TaskResponseDTO;
import com.sunflashtech.interview.models.Task;
import com.sunflashtech.interview.services.TaskService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import javax.validation.Valid;


@RestController
@RequestMapping("api/task")
@Api(tags = "task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/create")
    @ApiOperation(value = "${TaskController.createTask}")
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 422, message = "Taskname is already in use")})
    public Task createTask(@ApiParam("Create Task") @RequestBody TaskResponseDTO task) {
      return taskService.createTask(modelMapper.map(task, Task.class));
    }

    @DeleteMapping(value = "/delete/{name}")
    @ApiOperation(value = "${TaskController.delete}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 404, message = "The task doesn't exist"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public String delete(@ApiParam("Taskname Deleted Successfully") @PathVariable String name) {
      taskService.delete(name);
      return name;
    }
    
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(value = "${Task.delete}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 404, message = "The user doesn't exist"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public String delete(@ApiParam("id") @PathVariable int id) {
      taskService.delete(id);
      return "Success";
    }

    @GetMapping(value = "/{name}")
    @ApiOperation(value = "${TaskController.search}", response = TaskResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 404, message = "The task doesn't exist"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public TaskResponseDTO search(@ApiParam("Taskname") @PathVariable String name) {
      return modelMapper.map(taskService.search(name), TaskResponseDTO.class);
    }

    @GetMapping("/all")
    @ApiOperation(value = "${Task.all}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something is wrong with submitted request"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public List<TaskResponseDTO> all() {
      return taskService.all();
    }
    
    @PutMapping(value = "/update/{id}")
    @ApiOperation(value = "${Task.update}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 404, message = "The user doesn't exist"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public TaskResponseDTO update(@ApiParam("id") @PathVariable int id,@RequestBody @Valid TaskResponseDTO request) {
      return taskService.update(id,request);
    }
      
    @PutMapping(value = "/is-complete/{id}")
    @ApiOperation(value = "${Task.update}", authorizations = { @Authorization(value="apiKey") })
    @ApiResponses(value = {//
        @ApiResponse(code = 400, message = "Something went wrong"), //
        @ApiResponse(code = 403, message = "Access denied"), //
        @ApiResponse(code = 404, message = "The user doesn't exist"), //
        @ApiResponse(code = 500, message = "Internal server error")})
    public TaskResponseDTO isComplete(@ApiParam("id") @PathVariable int id,@RequestBody @Valid TaskResponseDTO request) {
      return taskService.isComplete(id,request);
    }
}
