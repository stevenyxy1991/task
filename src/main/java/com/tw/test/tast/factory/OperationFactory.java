package com.tw.test.tast.factory;

import com.tw.test.tast.reposery.ITaskRepository;
import com.tw.test.tast.reposery.IUserRepository;
import com.tw.test.tast.util.OperationType;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OperationFactory implements ApplicationContextAware {

    private static Map<OperationType, ITaskRepository> sortTaskBeanMap = new ConcurrentHashMap<>(16);
    private static Map<OperationType, IUserRepository> sortUserBeanMap = new ConcurrentHashMap<>(16);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ITaskRepository> map = applicationContext.getBeansOfType(ITaskRepository.class);
        map.forEach((key, value) -> sortTaskBeanMap.put(value.operationDef(), value));
        Map<String, IUserRepository> mapUser = applicationContext.getBeansOfType(IUserRepository.class);
        mapUser.forEach((key, value) -> sortUserBeanMap.put(value.operationDef(), value));
    }

    public final ITaskRepository getOperationService(OperationType operationType){
        if (Optional.ofNullable(sortTaskBeanMap.get(operationType)).isPresent()){
            return sortTaskBeanMap.get(operationType);
        }
        throw new RuntimeException("can not find operation service");
    }

    public final IUserRepository getOperationUserService(OperationType operationType){
        if (Optional.ofNullable(sortUserBeanMap.get(operationType)).isPresent()){
            return sortUserBeanMap.get(operationType);
        }
        throw new RuntimeException("can not find operation service");
    }
}
