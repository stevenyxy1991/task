package com.tw.test.task.reposery;

import com.tw.test.task.dao.IUserDao;
import com.tw.test.task.entity.TaskEntityPo;
import com.tw.test.task.entity.UserEntityPo;
import com.tw.test.task.model.UserModel;
import com.tw.test.task.model.UserOutModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserRepositoryImplTest {

    @InjectMocks
    public UserRepositoryImpl userRepository;

    @Mock
    public TaskRepositoryImpl taskRepository;

    @Mock
    public IUserDao userDao;

    private UserModel model = new UserModel();

    private UserOutModel userOutModel = new UserOutModel();

    @BeforeEach
    void init(){
        LocalDateTime time = LocalDateTime.now();
        UserEntityPo userEntityPo = new UserEntityPo(1L,"Jonh",45,time);
        TaskEntityPo taskEntityPo = new TaskEntityPo(1L,"working",time,time.plusDays(20),"working",time,"","");
        TaskEntityPo taskEntityPo1 = new TaskEntityPo(2L,"running",time,time.plusDays(20),"running",time,"","");
        List<TaskEntityPo> taskEntityPos = new ArrayList<>(2);
        taskEntityPos.add(taskEntityPo);
        taskEntityPos.add(taskEntityPo1);
        model.setUserEntity(userEntityPo);
        model.setTaskEntitys(taskEntityPos);
        userOutModel.setTaskEntitys(taskEntityPos);
        userOutModel.setUserEntity(userEntityPo);
        when(userDao.save(any())).thenReturn(userEntityPo);
        when(userDao.getById(any())).thenReturn(userEntityPo);
        when(taskRepository.findAllByRelation(any())).thenReturn(taskEntityPos);
    }



    @Test
    void saveUserTask() {
        userRepository.save(model);
        assertEquals(model.getTaskEntitys().get(0).getRelation(),"#1#false");
    }

    @Test
    void getByIdInfo() {
        UserOutModel outModel = userRepository.getByIdInfo(1L);
        Assert.assertEquals(outModel,userOutModel);
    }
}