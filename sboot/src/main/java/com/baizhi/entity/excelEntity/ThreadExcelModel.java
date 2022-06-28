package com.baizhi.entity.excelEntity;

import com.baizhi.service.ExtGaSourceService;
import com.baizhi.service.Impl.ExtGaSourceServiceImpl;
import com.baizhi.util.CreateWord;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Data
public class ThreadExcelModel extends Thread {

    private int count; //每个线程处理任务的个数,用于和listPdfVo对比，测试算法是否正确

    private int threadCount;//当前线程对应的for索引

    private List<SourceOutPdfVo> listPdfVo;//每个线程处理任务集合

    private CountDownLatch latch; //线程计数器，当所有的线程执行完，主线程最后执行，保证线程执行顺序

    private String uuid; //生成文件的UUID


    @Override
    public void run() {
        String name = Thread.currentThread().getName();
       System.out.println("当前线程开始执行"+name);
       ExtGaSourceServiceImpl extGaSourceService = new ExtGaSourceServiceImpl();
       extGaSourceService.getPdfByListSourceOutPdfVo(listPdfVo,uuid,name,1+"");
        latch.countDown();
    }
}
