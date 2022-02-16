package com.tw.test.tast.service;

import com.tw.test.tast.DataInit;
import com.tw.test.tast.entity.TaskEntity;
import org.junit.Before;
import org.junit.BeforeClass;
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

import static org.mockito.Mockito.when;


@SpringBootTest
//@ExtendWith(MockitoExtension::class)
@RunWith(SpringRunner.class)
public class MemoryServiceTest {

//    @InjectMocks
//    public MemoryService memoryService;

    @Mock
    public List<TaskEntity> taskInit;

    @BeforeEach
    void init(){
        taskInit = new ArrayList<>();
//        final List<TaskEntity> taskEntities = new ArrayList<>(10);
        taskInit.add(new TaskEntity(2L,"eating", LocalDateTime.now(),LocalDateTime.now().plusDays(10),"",LocalDateTime.now()));
//        when(taskInit).thenReturn(taskEntities);
    }

    @Test
    void save() {
        taskInit.add(new TaskEntity(2L,"eating", LocalDateTime.now(),LocalDateTime.now().plusDays(10),"",LocalDateTime.now()));

        TaskEntity taskEntity = new TaskEntity(1L,"working", LocalDateTime.now(),LocalDateTime.now().plusDays(5),"",LocalDateTime.now());

    }

    @Test
    void update() {
    }
}