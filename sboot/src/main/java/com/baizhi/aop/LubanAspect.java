package com.baizhi.aop;

import com.baizhi.annotation.Log;
import com.baizhi.entity.Person;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LubanAspect {

    public static void main(String[] args) {
      HashSet <Person> set=new HashSet();
      Person person1 = new Person("zs","1",11);
      Person person2 = new Person("zs","2",12);
      Person person3 = new Person("zs","222",17);
      Person person4 = new Person("zs","4",14);
      Person person5 = new Person("zs","111",15);
      set.add(person1);
      set.add(person2);
      set.add(person3);
      set.add(person4);
      set.add(person5);

      //增强for循坏遍历
      for (Person p:set){
        System.out.println(p.toString());
      }
    }


    @Pointcut("execution (* com.baizhi.service..*.*(..))")
    public void pointCut(){
      System.out.println("切入点............");
    }

    @Before("pointCut()")
    public void beforeAspect(JoinPoint joinPoint) throws Exception {
      Log controllerLog = getAnnotationLog(joinPoint);
      if (controllerLog == null) {
        return;
      }

      System.out.println("before.............");
    }
  /**
   * 是否存在注解，如果存在就获取
   */
  private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
    Signature signature = joinPoint.getSignature();
    MethodSignature methodSignature = (MethodSignature) signature;
    Method method = methodSignature.getMethod();

    if (method != null) {
      return method.getAnnotation(Log.class);
    }
    return null;
  }
}
