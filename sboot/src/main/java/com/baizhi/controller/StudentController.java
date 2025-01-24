package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.GtCollect;
import com.baizhi.entity.Person;
import com.baizhi.entity.Student;
import com.baizhi.entity.Train;
import com.baizhi.service.StudentService;

import javax.annotation.Resource;

import com.baizhi.springLisiter.HHZLisiterObject;
import com.baizhi.xxjob.TestComponentScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@EnableScheduling
public class StudentController extends BaseController implements ApplicationContextAware {
  private int j=0;

  private ApplicationContext applicationContext;

  @Resource
  private TestComponentScan testComponentScan;

  @Autowired
  private  List<StudentService> StudentService2;

  @Autowired
  private Map<String,StudentService> StudentService3;


  @Resource
  private  StudentService studentService;
  @Resource
  RedisTemplate redisTemplate;

  private HashMap<String,String> map=new HashMap<String,String>();

  @Autowired
  private  ApplicationEventPublisher applicationEventPublisher;

  @GetMapping("/selectStudnrtAll")
  //http://localhost:8082/sboot/selectStudnrtAll user 123
  @Page
  public AjaxResult selectStudnrtAll(Integer pageNum,Integer pageSize){
    List<Student> students = studentService.selectAll();
    return  AjaxResult.success(students);
  }


  @GetMapping("/insertStu")
  public AjaxResult insertStu(){
      Student student = new Student();
      student.setSname("张三"+1);
      student.setAge("100"+1);
      studentService.insert(student);
    return AjaxResult.success("插入成功");
  }


  @GetMapping("/addRedis")
  public  AjaxResult addRedis(){
    for( j=0;j<1000;j++){
      new Thread(()->{
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        int threadname=j;
        for(int i=0;i<1000;i++){
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          Object o = redisTemplate.opsForValue().get("trainusering:"+Thread.currentThread().getName());
          if(o==null){
            GtCollect person = new GtCollect();
            person.setHeartbeat(11);
            person.setImei("zs");
            person.setType(11);
            List<GtCollect> list = new ArrayList<>();
            person.setList(list);
            System.out.println("这里需要在查依次数据库");
            redisTemplate.opsForValue().setIfAbsent("trainusering:"+Thread.currentThread().getName(),person,1000, TimeUnit.SECONDS);
          }else {
            GtCollect person = (GtCollect)o;
            person.setType(i);
            List<GtCollect> list = person.getList();
            GtCollect personing = new GtCollect();
            personing.setHeartbeat(new Random().nextInt(100));
            list.add(personing);
            person.setListsize(list.size());
            redisTemplate.opsForValue().set("trainusering:"+Thread.currentThread().getName(),person);
          }
        }

      }).start();
    }


    return AjaxResult.success("操作成功");
  }

  @GetMapping("/getRedisByPerson")
  public AjaxResult getRedisByPerson(){
    long l = System.currentTimeMillis();
    Map<String,String> map=new HashMap<>();
      Set keys = redisTemplate.keys("trainusering*");
      for (Object key : keys) {
        Person person =(Person) redisTemplate.opsForValue().get(key);
        List<Person> list = person.getList();
        int sum=0;
        for (Person person1 : list) {
          sum=sum+person1.getAge();
        }
        String result=sum+"";
        map.put(key.toString(),result);
      }
    long l1 = System.currentTimeMillis();
    System.out.println("進入方法"+(l1-l));
    return AjaxResult.success(map);
  }

  @GetMapping("/getRedis/{id}")
  public AjaxResult getRedis(@PathVariable("id") String id){
    Object o = redisTemplate.opsForValue().get(id);
    String result =null;
    if(o!=null){
      result=(String) o;
    }
    System.out.println("進入方法");
    return AjaxResult.success(result);
  }
  @GetMapping("/selectGtCollect")
  public  AjaxResult selectAll(int curPage,int pageSize ){
    List<GtCollect> gtCollects = studentService.selectGtCollect(curPage, pageSize);
    return AjaxResult.success(gtCollects);
  }


  @GetMapping("/setCountThread")
  public  AjaxResult setCountThread(){
    studentService.setCountThread();
    return AjaxResult.success("修改成功");
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext=applicationContext;
  }



    /* @GetMapping("/user")
    public  String selectAll(){
        System.out.println("进入Select All");
        return "hello word";
    }
    @GetMapping("/selectStudnrtAll")
    public  List<Student> selectStudnrtAll(){
        List<Student> students = studentService.selectAll();
        return students;
    }
    //测试postman
    @PostMapping("/postMain")
    public Map<String,String> postMain(@RequestBody Student stu){
        System.out.println(stu.toString());
        System.out.println("进入Select CCC");
        return new HashMap(){{put("code","0");}};
    }

    //测试文件上传
    @Value("${filePath}")//获取springboot配置文件中的变量,把配置文件中的变量赋值给该属性
    private String filePath;

    @PostMapping("/upload")
    public Map<String,Object> upload(MultipartFile headImg, String username) throws IOException, IOException {
        headImg.transferTo(new File(filePath,headImg.getOriginalFilename()));
        return new HashMap(){{put("code",0);put("newFileName","xxxx");}};
    }*/
}
