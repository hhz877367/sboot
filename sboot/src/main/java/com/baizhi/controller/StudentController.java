package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.GtCollect;
import com.baizhi.entity.Person;
import com.baizhi.entity.Student;
import com.baizhi.entity.Train;
import com.baizhi.service.StudentService;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController extends BaseController{

  @Resource
  private StudentService studentService;

  @Resource
  RedisTemplate redisTemplate;

  private HashMap<String,String> map=new HashMap<String,String>();


  @GetMapping("/selectStudnrtAll")
  //http://localhost:8082/sboot/selectStudnrtAll user 123
  @Page
  public AjaxResult selectStudnrtAll(Integer pageNum,Integer pageSize){
    try {
      Thread.sleep(10000);
      System.out.println(Thread.currentThread().getName()+"-----"+"当前线程");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
    for(int i=0;i<100;i++){
      Student student = new Student();
      student.setSname("张三"+i);
      student.setAge("100"+i);
      studentService.insert(student);
    }



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
