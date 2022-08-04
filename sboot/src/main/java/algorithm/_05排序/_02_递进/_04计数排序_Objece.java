package algorithm._05排序._02_递进;

import com.baizhi.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class _04计数排序_Objece {

    public static void main(String[] args) {

        // 只适用于数据能转换为int整型的数据排序，如果数值太大，超过数组的内存下标，则不适用。通常适用于数据散列百万以内，数据量亿级别的排序。
        // 计数排序的思想,利用数组下标，插入一遍就使其排好，效率 为 O(n),java最快排序，没有之一。
        // 数组下标当作分数，，值作为出现次数

        ArrayList<Person>  arr = getIntArr();;

        long l1 = System.currentTimeMillis();
        ArrayList<Person> result = countSoft(arr,1000);
        long l = System.currentTimeMillis();
        System.out.println("排序消耗时长"+(l-l1));

        System.out.println("----------验证结果");
        int i1 = new Random().nextInt(10000);
        for(int i=0;i<result.size();i++){
            i1++; //防止i1等于0报错
            if(i%i1==0){  //抽样检测数据正确性
                System.out.println(result.get(i).toString());
            }
        }

    }
    // maxScore 成绩的最大值，用于初始化数组
    public static  ArrayList<Person> countSoft(ArrayList<Person>  arr,int maxScore){
        int length=arr.size();
        //创建一个大小一样的数组,进行存放最终结果
        ArrayList<Person> listResult = new ArrayList<>();
        //创建一个大小一样的数组,进行数值与下标反转
        Person[] count = new Person[maxScore];
        //遍历一次
        for (Person person : arr) {
            Person p= count[person.getAge()];
            if(p==null){
                p = new Person();
                ArrayList<Person> list = new ArrayList<>();
                list.add(person);
                p.setList(list);
            }else {
                p.getList().add(person);
            }
            count[person.getAge()]=p;
        }
        //count的数据插入数据到result，在次遍历count
        for (Person person : count) {
            if(person!=null){  //某一个分数的成绩没有出现过
            List<Person> list = person.getList();
                for (Person person1 : list) {
                    listResult.add(person1);
                }
            }
        }
        return listResult;
    }

    public  static  ArrayList<Person> getIntArr(){
        ArrayList<Person> a=new ArrayList(100);
        long l = System.currentTimeMillis();
        Random random = new Random();
        for(int i=0;i<10000000;i++){  // 10万对象28毫秒  100万对象   74毫秒  1000万对象 787毫秒
            Person person = new Person();
            person.setName("zs"+i);
            person.setAge(random.nextInt(1000));
            a.add(person);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("创建对象消耗时间"+(l1-l));
        return a;
    }
}
