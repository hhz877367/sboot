package com.baizhi.dao;

import com.baizhi.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface StudentDao extends BaseMapper<Student> {
     List<Student> selectAll();
     //表复制语句
    boolean inserCopy();
}
