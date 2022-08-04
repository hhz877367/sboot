package com.baizhi.controller;

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


}
