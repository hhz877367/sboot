package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@TableName(value = "student")
public class Student extends Dog implements Serializable  {


    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String sname;
    private String age;
    private String score;
    private Date birth;
    private Integer sex;
    private Integer count;
    private Integer version;

    public String lianxi;

    public  Student creStudent(int i){
        Student student = new Student();
        if(i%2==0){
            student.setSex(0);
            return  student;
        }else {
            student.setSex(1);
            return  student;
        }
    }

}
