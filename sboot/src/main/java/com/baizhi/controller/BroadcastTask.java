package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.Student;
import com.baizhi.entity_train.Video;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/*@RestController*/
public class BroadcastTask {
/*    @Resource
    RedisTemplate redisTemplate;*/

//    @Scheduled(cron = "*/10 * * * * ?")
 /*   public void testTask() {
        System.out.println("现在有训练任务在进行");
        //获取语音播报配置信息
        List<Video> videoList = getVideo();
        Video video = videoList.stream().filter(e -> e.getType() == "m1").collect(Collectors.toList()).get(0);
        //获取人员列表
        System.out.println(video);

    }

    private List<Video> getVideo() {
        ArrayList<Video> list = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            Video video = new Video();
            video.setMes("语音1");
            video.setUrl("url1");
            if (i == 0) {
                video.setType("m1");
            } else {
                video.setType("p1");
            }
            list.add(video);
        }

        return list;
    }

    //把即将训练人员放入redis为期为训练时长加一个小时
    @GetMapping("/inserRedis")
    public AjaxResult inserRedis() {
        ListOperations listOperations = redisTemplate.opsForList();
        System.out.println(listOperations);
        return AjaxResult.success("定时任务执行成功");
    }*/
}