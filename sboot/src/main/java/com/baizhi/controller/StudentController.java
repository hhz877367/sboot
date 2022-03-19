package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.Person;
import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import com.baizhi.util.Utils;
import java.util.Date;
import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
  @Resource
  private StudentService studentService;

  @Resource
  RedisTemplate redisTemplate;



  @GetMapping("/selectStudnrtAll")
  //http://localhost:8082/sboot/selectStudnrtAll user 123
  public AjaxResult selectStudnrtAll(){
    List<Student> students = studentService.selectAll();
    return AjaxResult.success(students);
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
