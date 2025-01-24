import com.baizhi.Application;
import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.util.StringUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestDao {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentService studentService;

    //测试selectById
    @Test
    public void tesetSelectById(){
        List<Student> students = studentDao.selectAll();
        if(StringUtils.isEmpty(students)){
            System.out.println(students.toString());
        }else {
            System.out.println("无数据");
            System.out.println("无数据");
            System.out.println("无数据");
            System.out.println("无数据");
        }
    }

    //测试selectOne
    @Test
    public void tesetSelectOne(){
        QueryWrapper<Student> qw = new QueryWrapper<>();
        Student stu = new Student();
        stu.setId(7);
        qw.setEntity(stu);
        Student student = studentDao.selectOne(qw);
        System.out.println(student.toString());

    }
    //测试根据条件查询 selectList方法
    @Test
    public void TestSelectList(){
        Student stu = new Student();
        QueryWrapper<Student> qw = new QueryWrapper<>();
        stu.setAge("24");
        qw.setEntity(stu);
        List<Student> students = studentDao.selectList(qw);
        students.forEach(System.out::println);
    }
    //测试SelectBatchIds
    @Test
    public void TestSelectBatchIds(){
        List<Integer> listIds = new ArrayList<Integer>();
        listIds.add(1);
        listIds.add(2);
        listIds.add(3);
        listIds.add(4);
        listIds.add(5);
        List<Student> students = studentDao.selectBatchIds(listIds);
        students.forEach(System.out::println);
    }
    //测试selectByMap方法
    @Test
    public void TestSelectByMap(){
        HashMap<String, Object> qwMap = new HashMap<>();
        qwMap.put("s_name","张三");
        List<Student> students = studentDao.selectByMap(qwMap);
        students.forEach(System.out::println);
    }


    //测试selectPage方法 效果不是很好，是假分页，一般使用分页插件
    @Test
    public void TestSelectPage(){
        IPage<Student> studentIPage = studentDao.selectPage(new Page<>(1, 10), null);
        List<Student> stu = studentIPage.getRecords();
        stu.forEach(System.out::println);

    }

    //测试通用插入方法
    @Test
    @SneakyThrows
    public void TestDaoInsert() {
        Student stu = new Student();
        stu.setSname("胡宏志");
        stu.setAge("22");
        String sDate="1995-12-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(sDate);
        stu.setBirth(parse);
        stu.setScore("22");
        studentDao.insert(stu);
        System.out.println(stu.getId());
    }

    //测试修改updateById
    @Test
    @SneakyThrows
    public void TestDaoUpdate() {
        Student stu = new Student();
        stu.setId(10);
        stu.setSname("胡宏志1");
        stu.setAge("2222");
        String sDate="1996-12-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(sDate);
        stu.setBirth(parse);
        stu.setScore("22");
        studentDao.updateById(stu);
        System.out.println(stu.getId());
    }
    
    //测试修改updateById
    @Test
    @SneakyThrows
    public void TestUpdate() {

        //设置修改的wehere条件
        UpdateWrapper<Student> wq = new UpdateWrapper<>();
        Student stuWrapper = new Student();
        stuWrapper.setAge("22");
        wq.setEntity(stuWrapper);
        //设置修改成为的内容
        Student stu = new Student();
        stu.setId(10);
        stu.setSname("胡宏志1");
        stu.setAge("2222");
        String sDate="1996-12-29";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(sDate);
        stu.setBirth(parse);
        stu.setScore("22");
        int update = studentDao.update(stu, wq);
        if(update>0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }



    @Test
    public  void TestStudentDao(){
        PageInfo<Student> studentPageInfo = studentService.selectPage(1, 5);
        System.out.println(studentPageInfo);

    }

    @Test
    public  void TestRedisTemplate(){
 /*       Student stu = new Student();
        stu.setSname("zs");
        stu.setAge("5");
        redisTemplate.opsForValue().set("lianxi12",stu);
        System.out.println(redisTemplate.opsForValue().get("lianxi12"));*/
    }

    @Test
    public  void TestRedisUtil(){
        HashMap<String, Object> myhash = new HashMap<>();
        Student stu = new Student();
        stu.setSname("zs");
        stu.setAge("5");
        stu.setScore("90");
        stu.setBirth(new Date());
    }

    /*存放双map结构*/
    @Test
    public  void TestPutObject(){
   /*     HashMap<String, Object> myhash = new HashMap<>();
        Student stu = new Student();
        stu.setSname("zs");
        stu.setAge("5");
        stu.setScore("90");
        stu.setBirth(new Date());
        myhash.put("name",stu.getSname());
        myhash.put("age",stu.getAge());
        myhash.put("score",stu.getScore());
        myhash.put("birth",stu.getBirth());
       HashOperations hashopt = redisTemplate.opsForHash();
        hashopt.put("dept:1:stu:1","2021-3-29",myhash);*/
    }

    /*取出双map结构*/
    @Test
    public  void TestGetObject(){

   /*     HashOperations hashopt = redisTemplate.opsForHash();
        HashMap<String,Student> stu = (HashMap<String,Student>)hashopt.get("dept:1:stu:1", "2021-3-29");
        for (String s : stu.keySet()) {
            System.out.println("key:"+s+"____value:"+stu.get(s));
        }*/
    }


}
