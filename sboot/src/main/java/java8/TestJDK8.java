package java8;

import com.baizhi.entity.Cat;
import java8.stream.UrlModel;
import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestJDK8 {
    public static void main(String[] args) {
        List<UrlModel> list = getUrlModel();
        //先找出来最大和最小,这里可以从数据库里查出来，max函数,效率很快
        Integer mamxTime = list.stream().map(UrlModel::getTime).max(Integer::compareTo).get();
        Integer minTime = list.stream().map(UrlModel::getTime).min(Integer::compareTo).get();
        //理想中位数
        int ModelindexValue=(mamxTime+minTime)/2;
        //记录最接近中位数的索引
        int index=0;
        int indexValueModel=mamxTime;
        for (int i=0;i<list.size();i++) {
            //求绝对值
            int curIndexValue =Math.abs(ModelindexValue- list.get(i).getTime());
            //用于和理想中位数比较,找出最接近
            if(curIndexValue<mamxTime){
                index=i;
                indexValueModel=curIndexValue;
            }
        }
        //遍历
        for (UrlModel urlModel : list) {
            System.out.println(urlModel.getTime());
        }
        System.out.println("中位数为------"+list.get(index).getTime());
    }
    public static List<UrlModel> getUrlModel(){
        //创建测试数据 10条
        List<UrlModel> urlModels = new ArrayList<>();
        for(int i=0;i<5;i++){
            UrlModel urlModel = new UrlModel();
            Random random = new Random();
            urlModel.setUrlName("url"+i);
            urlModel.setTime(random.nextInt(100));
            urlModels.add(urlModel);
        }
        return urlModels;
    }

}
