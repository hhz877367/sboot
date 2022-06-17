package com.spring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;

import java.lang.reflect.InvocationTargetException;
public class TestSrping {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

       // ClassLoader classLoader = aClass.newInstance();

        //spring以xml形式创建对象
       // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService",UserService.class);
        userService.test();

   /*     System.out.println(context.getBean("userService"));
        System.out.println(context.getBean("zhouyuBeanPostProcessor"));
        System.out.println(context.getBean("&&zhouyuBeanPostProcessor"));*/
   /*     UserService userService = (UserService)context.getBean("&&zhouyuBeanPostProcessor");
        System.out.println(userService.toString());
        userService.test();*/
        //ClassUtils.convertClassNameToResourcePath(getEnvironment().resolveRequiredPlaceholders(basePackage))



        //  Integer integer = userService.inObject();
  /*      Method[] methods = userService.getClass().getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(Autowired.class)){
                method.invoke(userService,null);
            }
        }*/
    }
}
