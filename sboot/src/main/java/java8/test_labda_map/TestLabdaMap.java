package java8.test_labda_map;

import com.baizhi.entity.Student;
import com.baizhi.entity.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestLabdaMap {
    private static Map<String,Object> map = new HashMap<>();

    public static void main(String[] args) {
        TestLabdaMap testLabdaMap = new TestLabdaMap();
        testLabdaMap.doCreateBean();
        //遍历map后执行labda表达式
        Set<String> strings = map.keySet();
        System.out.println("开始执行业务逻辑-------");
        for (String key : strings) {
            ObjectFactory bean =(ObjectFactory)map.get(key);
            System.out.println(bean.getObject().toString());
        }
    }
    protected Object doCreateBean(){
        addSingletonFactory("zs", () -> getEarlyBeanReference("ls"));
        return  null;
    }
    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        System.out.println("执行addSingletonFactory");
        map.put(beanName,singletonFactory);
    }

    protected Object getEarlyBeanReference(String beanName) {
        System.out.println("put的时候我不执行-----get的时候我才执行");
        return beanName;
    }
}
