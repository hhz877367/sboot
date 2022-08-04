package com.baizhi.service.Impl;

import com.baizhi.dao.StudentDao;
import com.baizhi.entity.GtCollect;
import com.baizhi.entity.Student;
import com.baizhi.entity.TaskPlanPojo;
import com.baizhi.service.StudentService;
import com.baizhi.util.PageUtils;
import com.baizhi.util.Utils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
@Service
public class StudentServiceImpl implements StudentService{

    @Resource
    private StudentDao studentDao;

    @Resource
    private StudentService studentService;



    /*
    * isolation  隔离级别  主要是set到连接里，数据库对应的
    * propagation 传播机制
    *  需求1  在执行a() 方法时，需要开启新的事务
    *mandatory 强制事务执行，如果不存在事务，则会抛异常
    *required_new 不管当前事务是否存在，都会新建一个事务
    * nested 如果当前存在事务，就嵌套当前事务中去执行，如果当前没有事务，那么就新建一个事务，类似 REQUIRE_NEW这个样一个传播行为
    *required  默认最友好 存在则加入，不存在则创建
    *supports  表示支持当前当前的事务，如果当前不存在事务，就以非事务的方式去执行
    *
    * not_support  表示当前如果有事务，则挂起当前事务，并且不开启新的事务
    * NEVER     从不支持事务，不存在则报错
    * */
    @Override
    @Transactional
    public void insert(Student s) {
                s.setAge("1");
                studentDao.insert(s);
                studentDao.insert(s);
                s.setAge("2");
                studentDao.insert(s);
                s.setAge("3");
                try {
                    studentService.testa();
                }catch (Exception e){
                     e.printStackTrace();
                }

                s.setAge("1");
                studentDao.insert(s);
                studentDao.insert(s);
                s.setAge("2");
                studentDao.insert(s);
                s.setAge("3");
            /*int c=1/0;*/
    }

    @Override
    /*
    *
    * */
    @Transactional(propagation = Propagation.SUPPORTS)
    public void testa() {
        Student student = new Student();
        student.setAge("bbbbbb");
        studentDao.insert(student);
    }

    @Override
    public List<Student> selectAll() {
        List<Student> students = studentDao.selectAll();
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

    @Override
    public List<GtCollect> selectGtCollect(int curPage,int pageSize) {
        Date timeStart = new Date();
        PageUtils.pageSetUp(curPage, pageSize);
        List<GtCollect> taskList = studentDao.selectGtCollect();
        Date timeEnd1 = new Date();
        long timeDiffByDate1 = Utils.getTimeDiffByDate(timeStart, timeEnd1);
        System.out.println("第一个sql消耗时间为"+timeDiffByDate1);
        List<GtCollect> gtCollects = studentDao.selectGtCollect2(curPage * pageSize, pageSize);
        Date timeEnd2 = new Date();
        long timeDiffByDate2 = Utils.getTimeDiffByDate(timeEnd1, timeEnd2);
        System.out.println("第二个sql消耗时间为"+timeDiffByDate2);
        return taskList;
    }

    @Override
    public void setCountThread() {
        for(int i=0;i<100;i++){
            new Thread(()->{
                    try{
                        Student student = studentDao.selectById("1");
                        student.setCount(student.getCount()+1);
                        Student where = new Student();
                        where.setId(1);
                        where.setVersion(student.getVersion());
                        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
                        wrapper.setEntity(where);
                        student.setVersion(student.getVersion()+1);
                        int update = studentDao.update(student, wrapper);
                        if(update==0){
                            System.out.println("更新失败");
                            try {

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            System.out.println("更新成功");

                        }

                    }catch (Exception e){

                    }finally {
                    }
                }).start();
            }
    }
}
