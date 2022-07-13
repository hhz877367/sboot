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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@EnableScheduling
public class StudentController extends BaseController implements ApplicationContextAware {

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
    testComponentScan.test();

    //String message = applicationContext.getMessage("hhz", null, new Locale("en"));
    applicationEventPublisher.publishEvent(new HHZLisiterObject("哈哈哈，我是被监听的"));
    applicationContext.publishEvent("12345");
    map.put("aa","aa");
    ServletRequestAttributes request = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    request.getRequest().getParameter("pageNum");
    Train train = new Train();
    Class  t= Train.class;
    Class aClass = train.getClass();
    System.out.println(t==aClass);
    System.out.println(t.equals(aClass));
    System.out.println(studentService.toString());
    List<Student> students = studentService.selectAll();
    return getDataTable(students);
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
    Person person = new Person();
    person.setAge(11);
    person.setName("zs");
    person.setId("11");
    redisTemplate.opsForValue().set("gmz-screen:2",person);
    return AjaxResult.success("操作成功");
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
