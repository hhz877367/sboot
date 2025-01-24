package java8.stream;

import com.baizhi.entity.Cat;
import com.baizhi.util.DateUtils;
import com.baizhi.util.Utils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {
    public static void main(String[] args) {
        List<Cat> list = getObjectList();
        List<String> list2 = getStringList();

        //普通分组写法
        HashMap<String, List<Cat>> modelHashMap = new HashMap<>();
        for (Cat cat : list) {
            if(cat.getName()!=null){
                if(modelHashMap.get(cat.getName())==null){
                    List<Cat> modelCatList = new ArrayList<>();
                    modelCatList.add(cat);
                    modelHashMap.put(cat.getName(),modelCatList);
                }else {
                    List<Cat> cats = modelHashMap.get(cat.getName());
                    cats.add(cat);
                }
            }
        }

        //遍历

        for(  Map.Entry<String, List<Cat>>  c :modelHashMap.entrySet()){
            List<Cat> cats = modelHashMap.get(c.getKey());
             System.out.println(c.getKey()+"-----"+cats.toString());
        }
        //遍历结果，验证是否正确

        //高级写法 按照类型分组
        Map<String, List<Cat>> collect = list.stream().filter(e->e.getName()!=null).collect(Collectors.groupingBy(Cat::getName));

        //遍历
        System.out.println("高级写法结果验证-------------------");
        for(  Map.Entry<String, List<Cat>>  c :collect.entrySet()){
            List<Cat> cats = collect.get(c.getKey());
           System.out.println(c.getKey()+"-----"+cats.toString());
        }
        //reduce​的使用
        //普通写法
        int sum=0;
        for (Cat cat : list) {
            sum=sum+cat.getAge();
        }
        System.out.println("sum=="+sum);
        //简化集合内求和
        Integer reduce= list.stream().map(Cat::getAge).reduce(10,Integer::sum);
        System.out.println("reduce="+reduce);
        //普通算法
        int age=list.get(0).getAge();
        for(int i=1;i<list.size();i++){
            if(age>list.get(i).getAge()){
                age=list.get(i).getAge();
            }
        }
        System.out.println("min_age="+age);

        //map筛选
        List<String> collect4 = list.stream().filter(e->e.getName()!=null).map(e -> e.testmap()).collect(Collectors.toList());
        System.out.println("----测试testmap-----");
        collect4.stream().forEach(System.out::print);

        //返回流中最小age
        Integer integer = list.stream().map(Cat::getAge ).min(Integer::compareTo).get();
        System.out.println(integer);

        //条件判断
        //符合一个 返回 true
        boolean b = list.stream().anyMatch(e -> e.getAge() == 0 || e.getName() == null);
        System.out.println(b);
        //所有的条件都符合，返回true
        boolean b1 = list.stream().allMatch(e -> e.getAge() > 0);
        System.out.println(b1);
        //所有的条件都不负荷，返回true
        boolean b2 = list.stream().noneMatch(e -> e.getAge() == 0);
        System.out.println(b2);

        //返回流中第一个数据
        System.out.println("流中第一个数据"+list.stream().findFirst());
        //返回流中任意一个数据
        System.out.println("流中任意一个数据"+list.stream().findAny());


        //过滤 普通字符串类型

        List<String> collecStr = list2.stream().filter(e -> e != null && e.contains("1")).collect(Collectors.toList());
        collecStr.stream().forEach(System.out::println);
        //过滤自定义类型
        List<String> collect1 = list.stream().filter(e->e.getName()!=null && e.getAge()==8 ).map(s->s.getName()).collect(Collectors.toList());
        collect1.stream().forEach(e->{
            System.out.println(e.toString());
        });
        //连接流  把所有名字取出来，根据z 分割,每个名字为一个流，最后连接成一个流，转换成 List<String>  输出
        List<String> collectFlaMap = list.stream().filter(e->e.getName()!=null).flatMap(e -> Arrays.stream(e.getName().split("z"))).collect(Collectors.toList());
        collectFlaMap.stream().forEach(System.out::println);
        System.out.println("==========================");
        List<Integer> collect2 = Stream.of(2, 4, 6, 8, 10).flatMap(e -> Stream.of(8,8,8,8,8,8,e)).collect(Collectors.toList());
        collect2.stream().forEach(System.out::println);
        System.out.println("++++++++++++++");
        List<Integer> collect3 = Stream.of(2, 4, 6, 8, 10).collect(Collectors.toList());
        collect3.stream().forEach(System.out::println);
    }

    public static List<String> getStringList(){
        List<String> list = new ArrayList<>();
        list.add("张三111");
        list.add("李四");
        list.add("321");
        list.add("das");
        list.add("张三");
        list.add("李四");
        list.add("22222");
        list.add(null);
        return list;
    }

    public static   List<Cat>  getObjectList(){
        List<Cat> list = new ArrayList<>();
        for(int i=0;i<100;i++){
            Cat cat = new Cat();
            cat.setName("zs"+i);
            if(i%3==0){
                //制造相同名字
                cat.setName("zs3");
            }
            if(i==0){
                //设置初始sum
                cat.setSum(0);
            }
            Random random = new Random();
            //设置随机age ,并且age最小为1，最大为11
            cat.setAge(random.nextInt(10)+1);
            cat.setCount(i);
            if(i%2==0){
                cat.setFlag(true);
                //制造名字为空的异常数据
                cat.setName(null);
            }else {
                cat.setFlag(false);
            }
            //每增加 i，天数延迟一天
            cat.setDateTime(DateUtils.getAddDay(i,null));
            //每增加 i，天数延迟一天
            cat.setDate(DateUtils.plusDateByInt(new Date(), i));
            cat.setDateStr(Utils.getStringDateByForm(cat.getDate()));
            //模拟cat的id
            cat.setCount(i);
            if(i!=0){
                //模拟求和
                cat.setSum(list.get(i-1).getSum()+i);
            }
            list.add(cat);
        }
        return list;
    }
}
