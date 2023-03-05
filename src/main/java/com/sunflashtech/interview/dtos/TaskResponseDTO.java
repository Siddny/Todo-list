package com.sunflashtech.interview.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.sunflashtech.interview.models.Task.PriorityLevel;

@Setter
@Getter
public class TaskResponseDTO {
    
  @ApiModelProperty(position = 0)
  private Integer id;
  
  @ApiModelProperty(position = 1)
  private String name;
  
  @ApiModelProperty(position = 2)
  private String description;
  
  @ApiModelProperty(position = 3)
  private boolean completed;

//  @ApiModelProperty(position = 3)
//  private PriorityLevel priorityLevel;

}
