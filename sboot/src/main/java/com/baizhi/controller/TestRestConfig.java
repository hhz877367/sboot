package com.baizhi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.Student;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestRestConfig {
     @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/testRestTemplate")
    public AjaxResult insertStu(){
        System.out.println(restTemplate.toString());
        System.out.println("测试testRestTemplate");
        String url="http://localhost:8082/sboot/insertStu";
        R result = restTemplate.getForObject(url, R.class);
        return AjaxResult.success(result);
    }

    public static void main(String[] args) {
        String[] split = "2022102017105695240004.2022102017105682770005".split("\\.");
        System.out.println(split.length);
        System.out.println(split.toString());
    }

}
