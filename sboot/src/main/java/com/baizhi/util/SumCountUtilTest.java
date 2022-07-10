package com.baizhi.util;

import com.baizhi.entity.Cat;
import com.baizhi.entity.Student;

import java.util.*;
import java.util.stream.Collectors;

public class SumCountUtilTest {
    public static void main(String[] args) {
        List<Student> list = getList();

        long countSexMan = list.stream().filter(student -> student.getSex()==1).count();

        long l = System.currentTimeMillis();
        Map<String, List<Student>> collect1 = list.stream().collect(Collectors.groupingBy(Student::getSname));
        long l1 = System.currentTimeMillis();
        System.out.println("消耗时间"+(l1-l));
        //配合map遍历一次，计算各个值出现次数
        long l2 = System.currentTimeMillis();
        HashMap<String ,Integer> map = new HashMap<>();
        for (Student stu : list) {
            //第一次取为Null
            if(map.get(stu.getSname())==null){
                map.put(stu.getSname(),1);
            }else {
                map.put(stu.getSname()+"",map.get(stu.getSname())+1);
            }
        }
        System.out.println("消耗时间"+(l2-l1));
        //遍历统计的结果
        Set<Map.Entry<String, List<Student>>> entries = collect1.entrySet();
        for(Map.Entry<String, List<Student>>  entry:entries){
            System.out.println("key="+entry.getKey()+"----value="+entry.getValue().size());
        }
    }

    //创建10万随机数据量集合
    public static List<Student> getList() {
        List<Student> integers = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            Random random = new Random();
            Student student = new Student();
            student.setCount(random.nextInt(100));
            student.setSname("zs"+random.nextInt(2));
            integers.add(student);
        }
        return integers;
    }

}
