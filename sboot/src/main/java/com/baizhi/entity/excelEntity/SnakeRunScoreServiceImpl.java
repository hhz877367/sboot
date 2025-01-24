package com.baizhi.entity.excelEntity;

import com.baizhi.service.SourceScoreService;

public class SnakeRunScoreServiceImpl implements SourceScoreService {
    /**
     * 计算项目分数根据成绩pa
     *
     * @param age 年龄
     * @param gender 性别
     * @param number 成绩(个数或者秒、毫秒)
     */
    @Override
    public Integer getSourceScore(int age, Integer gender, Integer number) {
        int score = 0;
        int[] sourceArray = new int[0];
        if (number > 0) {
            // 年龄下标
            int ageIndex = getIndex(SnakeRunConsts.AGE_ARRAY, age);
            if (gender == 0) {
                sourceArray = SnakeRunConsts.SOURCE_ARRAY_0[ageIndex];
            } else if (gender == 1) {
                sourceArray = SnakeRunConsts.SOURCE_ARRAY_1[ageIndex];
            }
            // 成绩下标
            int sourceIndex = getRunIndex(sourceArray, number);
            // 分数
            score = SnakeRunConsts.SCORE_ARRAY[sourceIndex];
            // 成绩上限
            int sourceEnd = sourceArray[sourceArray.length - 1];
            if (number.intValue() <= sourceEnd) {
                // 30米*2蛇形跑超岀100分后每递减0.1秒增加1分
                score = 100 + (sourceEnd - number) / 100;
            }
        } else {
            score = 0;
        }
        return score;
    }

    @Override
    public Integer getSourceScoreIndex(int age, Integer gender, Integer number) {
        // 年龄下标
        int[] sourceArray = new int[0];
        // 年龄下标
        int ageIndex = getIndex(SnakeRunConsts.AGE_ARRAY, age);
        if (gender == 0) {
            sourceArray = SnakeRunConsts.SOURCE_ARRAY_0[ageIndex];
        } else if (gender == 1) {
            sourceArray = SnakeRunConsts.SOURCE_ARRAY_1[ageIndex];
        }
        // 成绩下标
        int sourceIndex = getRunIndex(sourceArray, number);
        return sourceIndex;
    }

    public static int getIndex(int[] array, int number) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if (number <= array[i]) {
                    index = i;
                }
            } else {
                if (number > array[i - 1] && number <= array[i]) {
                    index = i;
                }
            }
        }
        if (index == -1) {
            index = array.length - 1;
        }
        return index;
    }

    public static int getRunIndex(int[] array, int number) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if (number > array[i]) {
                    index = i;
                }
            } else {
                if (number > array[i] && number <= array[i - 1]) {
                    index = i;
                }
            }
        }
        if (index == -1) {
            index = array.length;
        }
        return index;
    }

    public static int getScoreIndex(int[] array, int number) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                if (number < array[i]) {
                    index = i;
                }
            } else {
                if (number >= array[i - 1] && number < array[i]) {
                    index = i;
                }
            }
        }
        if (index == -1) {
            index = array.length;
        }
        return index;
    }
}
