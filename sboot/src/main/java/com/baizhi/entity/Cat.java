package com.baizhi.entity;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Cat {

    //测试Map筛选
    public String testmap(){
        if(name.contains("1")){
            return name;
        }else {
            return null;
        }
    }

    private String name;
    private Integer age;
    private boolean flag;

    private Date date;
    private String dateStr;

    private LocalDateTime dateTime;

    private Integer sum;

    private Integer count;


}
