package com.baizhi.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Aspect
@Component
public class PageAspect  {
    @Before("@annotation(page)")
    public void page(JoinPoint joinPoint, Page page) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> cla = joinPoint.getTarget().getClass();
        Class<?> superclass = cla.getSuperclass();
        if (superclass != null && superclass.equals(BaseController.class)) {
            Method startPage = superclass.getDeclaredMethod("startPage");
            startPage.setAccessible(true);
            Object bean = joinPoint.getTarget();
            startPage.invoke(bean);
        }else{
            throw new NoSuchMethodException(cla.getName() + " Can't find startPage method");
        }
    }
}