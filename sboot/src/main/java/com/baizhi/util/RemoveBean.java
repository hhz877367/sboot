package com.baizhi.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class RemoveBean implements ApplicationContextAware {


    private ApplicationContext applicationContext;


    //添加bean
    public void addBean(String beanName, Class<?> beanClass) {
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        if (!beanDefinitionRegistry.containsBeanDefinition(beanName)) {
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
        }
    }

    //移除bean
    public void removeBean(String beanName) {
        System.out.println(applicationContext);
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        beanDefinitionRegistry.getBeanDefinition(beanName);
        beanDefinitionRegistry.removeBeanDefinition(beanName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
