package com.baizhi.threadModel;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.excelEntity.SourceOutPdfVo;
import com.baizhi.entity.excelEntity.ThreadExcelModel;
import com.baizhi.service.Impl.ExtGaSourceServiceImpl;
import com.baizhi.util.QueryExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestExcutor {
    public static void main(String[] args) {
        {
                List<TestThread> listPdfVo = new ArrayList<TestThread>();
                for(int i=0;i<101;i++){
                    listPdfVo.add(new TestThread());
                }
            int size = listPdfVo.size();
            long startTime = System.currentTimeMillis();
                if (size > 100) {
                    //线程计数器，用于计数线程执行了多少个，线性执行完毕调用await方法唤醒主线程。
                    ThreadPoolExecutor excutor = new ThreadPoolExecutor(8,16,
                            0L, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(3000));
                    for (int i=0;i<size;i++) {
                        excutor.submit(listPdfVo.get(i));
                    }
                    excutor.shutdown();
                }
            long endTime = System.currentTimeMillis();
            System.out.println("任务执行时长" + (endTime - startTime) + "毫秒");
        }
    }
}
