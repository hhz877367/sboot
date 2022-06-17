package com.hhzspring.config;

import org.assertj.core.util.introspection.Introspection;
import org.springframework.beans.factory.InitializingBean;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HhzAnnotationConfigApplicationContext {

    private Map<String,BeanDefinition> beanDefinitionMap=new HashMap<>();
    private Map<String, Object> singletonObjects = new HashMap<>();

    public HhzAnnotationConfigApplicationContext(Class className) {
        scan(className);
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();
        Object instance = null;
        try {
            instance = clazz.getConstructor().newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(HhzAutowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    public void scan(Class className) {
        //因为HhzComScan为类注解，这里就不用遍历

        HhzComScan hhzComScan = (HhzComScan) className.getAnnotation(HhzComScan.class);
        String path = hhzComScan.value().replace(".", "/");
        ClassLoader classLoader = HhzAnnotationConfigApplicationContext.class.getClassLoader();
        URL resource = classLoader.getResource(path);
        List<String> clazzPathList=new ArrayList<String>();
        //如果HhzComScan没有写路径默认扫描全路径，写了只扫描对应包下的路径
         clazzPathList = getClazzPathList(resource.getFile(),clazzPathList);
        for (String pathUrl : clazzPathList) {
            try {
                Class<?> clazz = classLoader.loadClass(pathUrl);
                //得到HhzComponent注解的class对象
                if(clazz.isAnnotationPresent(HhzComponent.class)){
                    BeanDefinition beanDefinition = new BeanDefinition();
                    String name = clazz.getSimpleName();
                    String beanName = Introspector.decapitalize(name);
                    beanDefinition.setType(clazz);
                    //放入单例、多例信息
                    if(clazz.isAnnotationPresent(HhzScope.class)){
                        HhzScope hhzScope = clazz.getAnnotation(HhzScope.class);
                        String scopeValue = hhzScope.value();
                        if(scopeValue.equals("")){
                            scopeValue="singleton";
                        }
                        beanDefinition.setScope(scopeValue);
                    }else {
                        beanDefinition.setScope("singleton");
                    }
                    //放入BeanName信息
                    HhzComponent hhzComponent = clazz.getAnnotation(HhzComponent.class);
                    String hhzComponentValue = hhzComponent.value();
                    if(!hhzComponentValue.equals("")){
                        beanName=hhzComponentValue;
                    }
                    beanDefinitionMap.put(beanName,beanDefinition);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            Object singletonBean = singletonObjects.get(beanName);
            //还没来得及创建的情况，Bean对象里嵌套有别的Bean对象
            if (singletonBean == null) {
                singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            // 原型
            Object prototypeBean = createBean(beanName, beanDefinition);
            return prototypeBean;
        }
    }

    public static void main(String[] args) {
        URL resource = HhzAnnotationConfigApplicationContext.class.getClassLoader().getResource("");
        System.out.println(resource);
    }

    private List<String> getClazzPathList(String path,List<String> listPath){
        File file = new File(path);
        if(file.isDirectory()){
            for (File listFile : file.listFiles()) {

                String absolutePath = listFile.getAbsolutePath();
                if(file.isDirectory()){
                    getClazzPathList(absolutePath,listPath);
                }
                if(!absolutePath.contains(".class")){
                    continue;
                }
                int startIndex = absolutePath.indexOf("classes");
                int endIndex = absolutePath.indexOf(".class");
                absolutePath = absolutePath.substring(startIndex+8, endIndex);
                absolutePath = absolutePath.replace("\\", ".");
                listPath.add(absolutePath);
            }
        }
        return listPath;
    }



}
