package com.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
@Component
public class HHInstationAwareBeanPoseProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
      //  System.out.println(beanName+"实例化前+HHInstationAwareBeanPoseProcessor");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       // System.out.println(beanName+"实例化后+HHInstationAwareBeanPoseProcessor");
        return bean;
    }
}
