package com.baizhi.util;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HHzTest {
  @Resource
  private StudentDao studentDao;
  @Test
  public void selectAll(){
    List<Student> students = studentDao.selectAll();
    for(Student s:students){
      System.out.println(s.toString());
    }
  }
  @Test
  public void testInsertCopy(){
      boolean b = studentDao.inserCopy();
      System.out.println();
      System.out.println(b);
    }
}
