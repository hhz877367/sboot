package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@TableName(value = "stu")
public class Student implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "s_name")
    private String sname;

    private String age;
    private String score;
    private Date birth;

    private int count;

}
