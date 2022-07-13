package com.spring.aspaect;


import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;




// 代理对象
class PersonInvocationHandler implements InvocationHandler {

    public static void main(String[] args) {
        ManPerson target = new ManPerson();
        ManPerson proxy = (ManPerson) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new PersonInvocationHandler(target));
        proxy.eat();
        proxy.sleep();
    }

    private Object target;

    public PersonInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        System.out.println("start");
        Object result = method.invoke(target, args);
        System.out.println("end");

        return result;
    }
}

// 目标对象
class ManPerson implements IPerson {
    @Override
    public void eat(){
        System.out.println("吃饭中......");
    }

    @Override
    public void sleep(){
        System.out.println("睡觉中......");
    }
}

// 目标对象接口
interface IPerson{
    void eat();
    void sleep();
}
