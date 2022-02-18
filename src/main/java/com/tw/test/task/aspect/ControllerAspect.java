package com.tw.test.task.aspect;

import com.tw.test.task.model.Context;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: task
 * @description: AOP
 * @author: Mr.Wang
 * @create: 2022-02-18 14:28
 **/
@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private Context context;

    @Pointcut("execution(* com.tw.test.task.controller..*.*(..))")
    public void requestLogAop() {
    }

    @Before("requestLogAop()")
    public void before(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String type = attributes.getRequest().getHeader("optionType");
        int start = Integer.getInteger(attributes.getRequest().getHeader("pageStart"),0);
        int size = Integer.getInteger(attributes.getRequest().getHeader("pageSize"),0);
        if (0 != start && 0 != start){
            Pageable pageable = PageRequest.of(start - 1, size);
            context.getContext().put("pageable",pageable);
        }
        context.getContext().put("optionType",type);
    }

}
