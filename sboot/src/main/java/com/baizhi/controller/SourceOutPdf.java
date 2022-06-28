package com.baizhi.controller;

import com.baizhi.constant.AjaxResult;
import com.baizhi.entity.excelEntity.SourceOutPdfVo;
import com.baizhi.entity.excelEntity.ThreadExcelModel;
import com.baizhi.service.ExtGaSourceService;
import com.baizhi.service.Impl.ExtGaSourceServiceImpl;
import com.baizhi.util.QueryExcel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/sourceOutPdf")
public class SourceOutPdf {

    private static String sourcePath="D:\\temp\\";

    /**
     * 军事体育考核上传文件得到list集合放入redis中
     *
     * @author hhz
     * @since 2021-7-24
     */
    @SneakyThrows
    @PostMapping("/getRedisKey")
    public AjaxResult getRedisKey(MultipartFile file) {
        ExtGaSourceServiceImpl extGaSourceService = new ExtGaSourceServiceImpl();
        log.info("任务开始执行-------------");
        long startTime = System.currentTimeMillis();
        if (file != null) {
            List<SourceOutPdfVo> listPdfVo = QueryExcel.getListByIs(file.getInputStream(), SourceOutPdfVo.class);
            if (listPdfVo != null) {
                Map<String, Object> map = extGaSourceService.getPdfByListSourceOutPdfVo(listPdfVo,startTime+"","zs","1");
            }
        }
        long endTime = System.currentTimeMillis();
       // System.out.println("任务执行时长" + (endTime - startTime) + "毫秒");
        return AjaxResult.success("操作完成");
    }
    /*
     *测定
     *
     * */

    @SneakyThrows
    @PostMapping("/getRedisKeyThread")
    public AjaxResult getRedisKeyThread(MultipartFile file) {
        ExtGaSourceServiceImpl extGaSourceService = new ExtGaSourceServiceImpl();
        log.info("任务开始执行-------------");
        long startTime = System.currentTimeMillis();
        if (file != null) {
            //生成目录的UUID
            String uuid = System.currentTimeMillis() + "";
            //创建存放word文档的目录
            String makdirFile = sourcePath + "querysourcedownload/" + uuid;
            File fileWordDir = new File(makdirFile);
            boolean mkdir = fileWordDir.mkdir();
            if (!mkdir) {
                return AjaxResult.success("生成失败");
            }
            //解析excel生成集合对象
            List<SourceOutPdfVo> listPdfVo = QueryExcel.getListByIs(file.getInputStream(), SourceOutPdfVo.class);
            int size = listPdfVo.size();
            for (int j = 0; j < size; j++) {
                listPdfVo.get(j).setId(j + "");
            }
            if (size > 1000) {
                CountDownLatch latch = new CountDownLatch(1);
                if (listPdfVo != null) {
                    for (int i = 0; i < 8; i++) {
                        //把100个，以及100个以上的任务平均分给8个线程去干活
                        ThreadExcelModel thread = new ThreadExcelModel();
                        thread.setCount(size/8);
                        thread.setThreadCount(i);
                        thread.setListPdfVo(setWork(listPdfVo, i));
                        thread.setLatch(latch);
                        thread.setUuid(uuid);
                        thread.start();
                    }
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                extGaSourceService.getPdfByListSourceOutPdfVo(listPdfVo,uuid,"zs","1");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("任务执行时长" + (endTime - startTime) + "毫秒");
        return AjaxResult.success("操作完成");
    }

    private static List<SourceOutPdfVo> setWork(List<SourceOutPdfVo> listPdfVo, Integer threadIndex) {
        int size = listPdfVo.size();
        List<SourceOutPdfVo> list = new ArrayList<>();
        //得到倍数n，每个线程放n个，或者n+1个，n+1个的情况根据余数判断
        int n = size / 8;
        int m = size % 8;
        List<SourceOutPdfVo> result = new ArrayList<SourceOutPdfVo>();
        int i = 0;
        for (; i < n; i++) {
            result.add(listPdfVo.get(i * 8 + threadIndex));
        }
        if (threadIndex < m) {
            result.add(listPdfVo.get((i * 8) + threadIndex));
        }
        return result;
    }

    /**
    * 使用线程池写法
    *
    * */
    @SneakyThrows
    @PostMapping("/getRedisKeyThreadPool")
    public AjaxResult getRedisKeyThreadPool(MultipartFile file) {
        ExtGaSourceServiceImpl extGaSourceService = new ExtGaSourceServiceImpl();
        log.info("任务开始执行-------------");
        long startTime = System.currentTimeMillis();
        if (file != null) {
            //生成目录的UUID
            String uuid = System.currentTimeMillis() + "";
            //创建存放word文档的目录
            String makdirFile = sourcePath + "querysourcedownload/" + uuid;
            File fileWordDir = new File(makdirFile);
            boolean mkdir = fileWordDir.mkdir();
            if (!mkdir) {
                return AjaxResult.success("生成失败");
            }
            //解析excel生成集合对象
            List<SourceOutPdfVo> listPdfVo = QueryExcel.getListByIs(file.getInputStream(), SourceOutPdfVo.class);
            int size = listPdfVo.size();
            for (int j = 0; j < size; j++) {
                listPdfVo.get(j).setId(j + "");
            }
            if (size > 100) {
                //线程计数器，用于计数线程执行了多少个，线性执行完毕调用await方法唤醒主线程。
                CountDownLatch latch = new CountDownLatch(size);
                ThreadPoolExecutor excutor = new ThreadPoolExecutor(8,16,
                        0L, TimeUnit.MICROSECONDS,new LinkedBlockingDeque<>(3000));
                for (int i=0;i<size;i++) {
                    List<SourceOutPdfVo> list = new ArrayList<>();
                    ThreadExcelModel thread = new ThreadExcelModel();
                    thread.setCount(size/8);
                    thread.setThreadCount(i);
                    list.add(listPdfVo.get(i));
                    thread.setListPdfVo(list);
                    thread.setLatch(latch);
                    thread.setUuid(uuid);
                    //直接把任务放到线程池里执行，分配任务的活交给线程池
                    excutor.submit(thread);
                }
                latch.await();
            } else {
                extGaSourceService.getPdfByListSourceOutPdfVo(listPdfVo,uuid,"zs","1");
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("任务执行时长" + (endTime - startTime) + "毫秒");
        return AjaxResult.success("操作完成");
    }
}
