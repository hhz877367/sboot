package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/*import org.springframework.security.web.PortResolverImpl;*/

import java.io.Serializable;

@Data
@TableName(value = "gt_train")
public class Train implements Serializable {
    @TableId(value = "train_id",type = IdType.AUTO)
    private String trainId;
    private String startTime;
    private String project;
    private String endTime;
    private int issueFlag;
}
