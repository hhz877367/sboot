package com.baizhi.util;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RefreshUtil {

    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); } // 加载 OpenCV 库
    public static void main(String[] args) {
        while (true){
            try {
                Thread.sleep(1000);
                reFreshPhone();
                getImgUrl();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static Integer imgIndex = 0;

    //自动保存图片
    public static void reFreshPhone() {
        try {
            // 创建一个Robot实例
            Robot robot = new Robot();

            // 获取屏幕尺寸
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(screenSize);

            // 截取屏幕
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);

            // 获取当前时间戳，用于生成文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = sdf.format(new Date());
            String fileName = imgIndex + ".png";
            imgIndex++;
            // 定义保存路径
            File directory = new File("E:\\mhxy_img");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File imageFile = new File(directory, fileName);

            // 将截图保存到文件
            ImageIO.write(screenFullImage, "png", imageFile);

            System.out.println("屏幕截图已保存到: " + imageFile.getAbsolutePath());

        } catch (AWTException | IOException ex) {
            System.err.println("发生错误: " + ex);
        }
    }

    //获取 图片B在A 中的 位置
    public static void  getImgUrl(){
        // 读取大图片 A 和小图片 B
        Mat imgA = Imgcodecs.imread("E:\\mhxy_img\\19.png");
        Mat imgB = Imgcodecs.imread("E:\\mhxy_base\\b.png");

        // 转换为灰度图像（可选）
        Mat grayA = new Mat();
        Mat grayB = new Mat();
        Imgproc.cvtColor(imgA, grayA, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(imgB, grayB, Imgproc.COLOR_BGR2GRAY);

        // 执行模板匹配
        Mat result = new Mat();
        Imgproc.matchTemplate(grayA, grayB, result, Imgproc.TM_CCOEFF_NORMED);

        // 找到最佳匹配位置
        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
        org.opencv.core.Point matchLoc = mmr.maxLoc;

        // 输出匹配位置
        System.out.println("匹配位置: (" + matchLoc.x + ", " + matchLoc.y + ")");
    }
}
