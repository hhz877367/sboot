package com.baizhi.service;

import com.baizhi.entity.GtCollect;
import com.baizhi.entity.Student;
import com.baizhi.entity.TaskPlanPojo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    //导入Student
     void insert(Student s);
    //分页查询
     List<Student> selectAll();

     PageInfo<Student> selectPage(int curpage, int pageSize);

    //Util excel导入  导出
    boolean inserCopy();

    List<GtCollect> selectGtCollect(int curPage,int pageSize);



}
