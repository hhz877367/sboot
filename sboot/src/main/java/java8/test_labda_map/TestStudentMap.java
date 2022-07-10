package java8.test_labda_map;

import org.springframework.beans.factory.ObjectFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TestStudentMap {

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
            addSingletonFactory("zs", () -> getEarlyBeanReference("zs"));
            return  null;
        }
        protected void addSingletonFactory(String beanName, MyFactoryBean singletonFactory){
            System.out.println("执行addSingletonFactory");
            map.put(beanName,singletonFactory);
        }

        protected Object getEarlyBeanReference(String beanName) {
            System.out.println("测试这里我不会------执行getEarlyBeanReference");
            return "ls";
        }
}
