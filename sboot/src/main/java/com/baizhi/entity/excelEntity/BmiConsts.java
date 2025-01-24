package com.baizhi.entity.excelEntity;

import com.baizhi.util.Utils;

import java.util.HashMap;
import java.util.Map;

public class BmiConsts {
    /**
     * 年龄
     */
    public static final int[] AGE_ARRAY = {24,29,39,49,59};

    /**
     * bmi正常值下限
     */
    public static final float BMI = 18.5f;

    /**
     * bmi正常值上限数组(男)
     */
    public static final float[] BMI_ARRAY_1 = { 25.9f, 26.9f, 27.9f, 28.9f, 29.4f, 29.9f };

    /**
     * bmi正常值上限数组(女)
     */
    public static final float[] BMI_ARRAY_0 = { 23.9f, 24.9f, 25.9f, 26.9f, 27.4f, 27.9f };

    public static void main(String[] args) {
        Map<String, String> bmiDesc = getBmiDesc(24, 1, 180D, 59.9D);
    }

    public static Map<String,String> getBmiDesc(Integer age,Integer sex,Double height,Double weight){
        Map<String, String> result = new HashMap<>();
        if(age==null || height==null || weight==null){
            return result;
        }
        float bmival = 0f;
        if (age < 24) {
            if (sex==1) { // 男
                bmival = 25.9f;
            } else { // 女
                bmival = 23.9f;
            }
        } else {
            int arrayIndex = GetIndexUtils.getIndex(BmiConsts.AGE_ARRAY, age); // 下标
            float[] bis;
            if (sex==1) {
                bis = BmiConsts.BMI_ARRAY_1;
            } else {
                bis = BmiConsts.BMI_ARRAY_0;
            }
            bmival = bis[arrayIndex];
        }
        Double bmiDouble = Utils.getIntByObjectOne( weight * 10000 / (height * height));
        //直接拿double 和float 进行比较，相同的值，double 自动大于float
        Float bmiFload=new Float(bmiDouble);
        if (bmiFload > bmival) {
            result.put("bmiDesc","不合格");
        } else if (bmiFload < BmiConsts.BMI) {
            result.put("bmiDesc","不合格");
        } else {
            result.put("bmiDesc","合格");
        }
        Double bmi = Utils.getIntByObjectOne(bmiDouble);
        result.put("bmi",bmi.toString());
        return  result;
    }
}
