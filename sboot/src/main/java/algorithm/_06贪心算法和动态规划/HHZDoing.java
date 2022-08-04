package algorithm._06贪心算法和动态规划;

import java.util.Map;

public class HHZDoing {
    public static void main(String[] args) {
        String a="111";
        int value [] ={6,10,12}; //每个物品价值
        int weigth[] = {1,2,4};	  //每个物品重量

        int w = 5; //背包只能装的重量
        int n = weigth.length;
        int[][] demp=new int[n+1][w+1];

        for(int i=1;i<=n;i++){    //N个物品尝试去装

            for(int j=1;j<=w;j++){  //袋子的重量依次增加
                if(j>=weigth[i-1]){   //判断当前的物品是否能装 。weigth[i-1]代表当前物品重量
                    //能装的话比较    上一次结果   和 这一次物品价值+ 上一次的剩余的物品重量的结果值
                    //                                 demp[i-1]表示上一次  weigth[i-1]代表当前物品重量 j代表递增的重量 相减代表剩余的重量
                    demp[i][j]=Math.max(demp[i-1][j],(value[i-1]+demp[i-1][j-weigth[i-1]]));
                }else {
                    //不能装直接等于上一列二维数组的值
                    demp[i][j]=demp[i-1][j];
                }
            }
        }
        System.out.println(demp[n][w]);

    }
}
