package com.baizhi.service.Impl;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.Student;
import com.baizhi.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert(Student s) {
        studentDao.insert(s);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> selectAll() {
        QueryWrapper<Student> qw = new QueryWrapper<>();
        Student model = new Student();
        model.setId(4);
        qw.setEntity(model);
        List<Student> students = studentDao.selectList(qw);
        return students;
    }


    @Override
   public PageInfo<Student> selectPage(int curpage,int pageSize){
       PageHelper.startPage(curpage,pageSize);
       List<Student> students = studentDao.selectAll();
       return new PageInfo<>(students);
   }
    //@Scheduled(cron = " */1 * * * * *")
    public void testScheduled(){

        System.out.println("进入方法--------------");
    }
    @Override
    public boolean inserCopy() {
        return false;
    }
}
