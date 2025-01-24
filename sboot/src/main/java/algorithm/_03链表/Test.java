package algorithm._03链表;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> m1 = new HashMap<>();
        m1.put("张三","111");
        m1.put("张三1","111");
        m1.put("张三2","111");
        m1.put("张三3","111");
        Set<Map.Entry<String,String>> set= m1.entrySet();
        //java 8 提供的方法
        Map<String, String> m2 = set.stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(m2.get("张三"));
        System.out.println(m2.get("张三1"));
        System.out.println(m2.get("张三2"));
        System.out.println(m2.get("张三3"));
    }
}
