package com.baizhi.entity.excelEntity;

import com.baizhi.service.SourceScoreService;

public class SitUpsScoreServiceImpl implements SourceScoreService {
    /**
     * 计算项目分数根据成绩pa
     * @param age    年龄
     * @param gender 性别
     * @param number 成绩(个数或者秒、毫秒)
     */
    @Override
    public Integer getSourceScore(int age, Integer gender, Integer number) {
        int score = 0;
        int[] sourceArray = new int[0];
        //年龄下标
        int ageIndex = getIndex(SitUpsConsts.AGE_ARRAY,age);
        if(gender==0){
            sourceArray =SitUpsConsts.SOURCE_ARRAY_0[ageIndex];
        }else if(gender==1){
            sourceArray=SitUpsConsts.SOURCE_ARRAY_1[ageIndex];
        }
        //成绩下标
        int sourceIndex = getScoreIndex(sourceArray,number);
        //分数
        score = SitUpsConsts.SCORE_ARRAY[sourceIndex];
        //成绩上限
        int sourceEnd = sourceArray[sourceArray.length-1];
        if(number.intValue()>sourceEnd){
            //仰卧起坐多2个增1分
            score = 100+(number-sourceEnd)/2;
        }
        return score;
    }

    /**
     * 计算项目分数下标
     * @param age    年龄
     * @param gender 性别
     * @param number 成绩(个数或者秒、毫秒)
     */
    @Override
    public Integer getSourceScoreIndex(int age, Integer gender, Integer number) {
        int[] sourceArray = new int[0];
        //年龄下标
        int ageIndex = getIndex(SitUpsConsts.AGE_ARRAY,age);
        if(gender==0){
            sourceArray =SitUpsConsts.SOURCE_ARRAY_0[ageIndex];
        }else if(gender==1){
            sourceArray=SitUpsConsts.SOURCE_ARRAY_1[ageIndex];
        }
        //成绩下标
        int sourceIndex = getScoreIndex(sourceArray,number);
        return sourceIndex;
    }

    public static int getIndex(int[] array, int number){
        int index = -1;
        for(int i=0;i<array.length;i++){
            if(i==0){
                if(number<=array[i]){
                    index=i;
                }
            }else{
                if(number>array[i-1]&&number<=array[i]){
                    index=i;
                }
            }
        }
        if(index==-1){
            index=array.length-1;
        }
        return index;
    }
    public static int getScoreIndex(int[] array, int number){
        int index = -1;
        for(int i=0;i<array.length;i++){
            if(i==0){
                if(number<array[i]){
                    index=i;
                }
            }else{
                if(number>=array[i-1]&&number<array[i]){
                    index=i;
                }
            }
        }
        if(index==-1){
            index=array.length;
        }
        return index;
    }
}
