package com.baizhi.dao;

import com.baizhi.entity.Student;
import com.baizhi.entity.Train;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface TrainDao extends BaseMapper<Train> {

    Train  getTrainById(String id);



    //添加训练后返回主键
    int insertTrain(Train train);


}
