package com.tw.test.task.model;

import com.tw.test.task.entity.BaseEntity;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.entity.UserEntityPo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskShareModel extends BaseEntity {

    private UserEntityPo userEntity;

    private Long shareId;

    private List<TaskEntityPo> taskEntitys;

}
