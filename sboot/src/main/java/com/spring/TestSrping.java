package com.spring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class TestSrping {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

       // ClassLoader classLoader = aClass.newInstance();

        //spring以xml形式创建对象
       // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        System.out.println("开始getBean之前");
        OrderService orderService = (OrderService)context.getBean("orderService");
        System.out.println("orderService--"+orderService);

     UserService userService = (UserService)context.getBean("userService");
        userService.test();

         /*  UserService userService2 = (UserService)context.getBean("userService");
        userService2.test();*/

        OrderService orderService2 = context.getBean("orderService",OrderService.class);
        System.out.println(orderService2.toString());

        TestConfiguration testConfiguration = context.getBean("testConfiguration",TestConfiguration.class);
        testConfiguration.test();

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
