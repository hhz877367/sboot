package com.baizhi.entity.excelEntity;

import com.github.pagehelper.util.StringUtil;

public class SeeLeaveConsts {
    public static Integer getSeaLeaveScore(Integer age,String sex,Integer seaLeave,Integer runTime){
        Integer score=0;
        if(age==null || StringUtil.isEmpty(sex) || (!sex.equals("男") &&!sex.equals("女")) || runTime==null || runTime<=0){
            return  score;
        }
        int[] seaLeaveArray = getSeaLeaveArray(seaLeave, age, sex);
        Integer scoreIndex = getRunIndex(seaLeaveArray, runTime);
        score= SCORE_ARRAY[scoreIndex];
        if(score>=100){
            Integer maxSource=seaLeaveArray[seaLeaveArray.length-1];
            Integer diffScoure=(maxSource-runTime)/5000;
            score=score+diffScoure;
        }
        return score;
    }
    //军体通用五项查询，返回对应成绩数组
    public static int[] getSeaLeaveArray(Integer seaLeave, Integer age, String sex) {
        SeeLeaveConsts seeLeaveConsts = new SeeLeaveConsts();
        int ageIndex = SeeLeaveConsts.getIndex(SeeLeaveConsts.AGE_ARRAY, age);
        int[] sourceArray = null;
        int[] result = new int[10];
        if (seaLeave == null || age == null && StringUtil.isEmpty(sex)) {
            return sourceArray;
        }
        //判断 性别 和海拔
        if ("男".equals(sex)) {
            if (seaLeave <= 2000) {
                result = RacesConsts.SOURCE_ARRAY_1[ageIndex];
            } else if (seaLeave <= 3000) {
                sourceArray = SOURCE_ARRAY_1[ageIndex];
                //计算秒数
                Integer diff = ((seaLeave - 2001) / 100) * 8 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else if (seaLeave <= 3500) {
                sourceArray = SOURCE_ARRAY_11[ageIndex];
                Integer diff = ((seaLeave - 3001) / 100) * 10 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else if (seaLeave < 4000) {
                sourceArray = SOURCE_ARRAY_11[ageIndex];
                Integer diff = ((seaLeave - 3501) / 100) * 12 * 1000 + 52 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else {
                sourceArray = SOURCE_ARRAY_1[SOURCE_ARRAY_1.length - 1];
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] +100 * 1000 ;
                }
            }
        } else {
            if (seaLeave <= 2000) {
                result = RacesConsts.SOURCE_ARRAY_0[ageIndex];
            } else if (seaLeave <= 3000) {
                sourceArray = seeLeaveConsts.SOURCE_ARRAY_0[ageIndex];
                Integer diff = ((seaLeave - 2001) / 100) * 8 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else if (seaLeave <= 3500) {
                sourceArray = SOURCE_ARRAY_01[ageIndex];
                Integer diffTime = ((seaLeave - 3001) / 100);
                Integer diff = diffTime * 10 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else if (seaLeave < 4000) {
                sourceArray = SOURCE_ARRAY_01[ageIndex];
                Integer diff = ((seaLeave - 3501) / 100) * 12 * 1000 + 52 * 1000;
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + diff;
                }
            } else {
                sourceArray = SOURCE_ARRAY_01[ageIndex];
                for (int i = 0; i < sourceArray.length; i++) {
                    result[i] = sourceArray[i] + 100 * 1000 ;
                }
            }
        }
        return result;
    }
    /**
     * 年龄
     */
    public static final int[] AGE_ARRAY = {24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60};

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

    /**
     * 分数
     */
    public static final int[] SCORE_ARRAY = {0, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};

    /**
     * 成绩(毫秒)男 2001-3000米的
     */
    public static final int[][] SOURCE_ARRAY_1 = {
            {832000, 827000, 822000, 802000, 787000, 772000, 757000, 742000, 727000, 702000},
            {868000, 863000, 858000, 838000, 823000, 808000, 793000, 778000, 763000, 738000},
            {900000, 895000, 884000, 874000, 859000, 844000, 829000, 814000, 799000, 774000},
            {940000, 935000, 930000, 910000, 895000, 880000, 865000, 850000, 835000, 810000},
            {960000, 955000, 950000, 945000, 931000, 916000, 901000, 886000, 871000, 846000},
            {1018000, 1013000, 1008000, 988000, 973000, 958000, 943000, 928000, 913000, 888000},
            {1060000, 1055000, 1050000, 1030000, 1015000, 1000000, 985000, 970000, 955000, 930000},
            {1102000, 1097000, 1092000, 1072000, 1057000, 1042000, 1027000, 1012000, 997000, 972000},
            {1144000, 1139000, 1134000, 1114000, 1099000, 1088000, 1069000, 1054000, 1039000, 1014000},
            {1186000, 1181000, 1176000, 1156000, 1141000, 1130000, 1111000, 1096000, 1081000, 1056000},
            {1228000, 1223000, 1218000, 1198000, 1183000, 1172000, 1153000, 1138000, 1123000, 1098000},
            {1270000, 1265000, 1260000, 1240000, 1225000, 1214000, 1195000, 1180000, 1165000, 1140000},
            {1312000, 1307000, 1302000, 1282000, 1267000, 1256000, 1237000, 1222000, 1197000, 1182000}
    };
    /**
     * 成绩(毫秒)男 3001-4000米的
     */
    public static final int[][] SOURCE_ARRAY_11 = {
            {904000, 899000, 894000, 874000, 859000, 844000, 829000, 814000, 799000, 774000},
            {940000, 935000, 930000, 910000, 895000, 880000, 865000, 850000, 835000, 810000},
            {960000, 955000, 950000, 945000, 931000, 916000, 901000, 886000, 871000, 846000},
            {1018000, 1013000, 1008000, 988000, 973000, 958000, 943000, 928000, 913000, 888000},
            {1060000, 1055000, 1050000, 1030000, 1015000, 1000000, 985000, 970000, 955000, 930000},
            {1102000, 1097000, 1092000, 1072000, 1057000, 1042000, 1027000, 1012000, 997000, 972000},
            {1144000, 1139000, 1134000, 1114000, 1099000, 1088000, 1069000, 1054000, 1039000, 1014000},
            {1186000, 1181000, 1176000, 1156000, 1141000, 1130000, 1111000, 1096000, 1081000, 1056000},
            {1228000, 1223000, 1218000, 1198000, 1183000, 1172000, 1153000, 1138000, 1123000, 1098000},
            {1270000, 1265000, 1260000, 1240000, 1225000, 1214000, 1195000, 1180000, 1165000, 1140000},
            {1312000, 1307000, 1302000, 1282000, 1267000, 1256000, 1237000, 1222000, 1197000, 1182000},
            {1354000, 1349000, 1344000, 1326000, 1309000, 1298000, 1279000, 1266000, 1239000, 1224000},
            {1396000, 1391000, 1386000, 1368000, 1351000, 1340000, 1311000, 1308000, 1281000, 1266000},
    };
    /**
     * 成绩(毫秒)女  2001-3000米的
     */
    public static final int[][] SOURCE_ARRAY_0 = {
            {983000, 978000, 973000, 953000, 938000, 923000, 908000, 893000, 878000, 853000},
            {1022000, 1017000, 1012000, 992000, 977000, 962000, 947000, 932000, 917000, 892000},
            {1061000, 1056000, 1051000, 1031000, 1016000, 1001000, 986000, 971000, 956000, 931000},
            {1100000, 1095000, 1090000, 1070000, 1055000, 1040000, 1025000, 1010000, 995000, 970000},
            {1139000, 1134000, 1129000, 1109000, 1094000, 1079000, 1064000, 1049000, 1034000, 1009000},
            {1178000, 1173000, 1168000, 1148000, 1133000, 1118000, 1103000, 1088000, 1073000, 1048000},
            {1217000, 1212000, 1207000, 1187000, 1172000, 1157000, 1142000, 1127000, 1112000, 1087000},
            {1256000, 1251000, 1246000, 1226000, 1211000, 1196000, 1181000, 1166000, 1151000, 1126000},
            {1295000, 1290000, 1285000, 1265000, 1250000, 1235000, 1220000, 1205000, 1190000, 1165000},
            {1334000, 1329000, 1324000, 1304000, 1289000, 1274000, 1259000, 1244000, 1229000, 1204000},
            {1373000, 1368000, 1363000, 1343000, 1328000, 1313000, 1298000, 1283000, 1268000, 1243000},
            {1412000, 1407000, 1402000, 1382000, 1367000, 1352000, 1337000, 1322000, 1307000, 1282000},
            {1451000, 1446000, 1441000, 1421000, 1406000, 1391000, 1376000, 1361000, 1346000, 1321000},
    };
    /**
     * 成绩(毫秒)女 3001-4000米的
     */
    public static final int[][] SOURCE_ARRAY_01 = {
            {1061000, 1056000, 1051000, 1031000, 1016000, 1001000, 986000, 971000, 956000, 931000},
            {1100000, 1095000, 1090000, 1070000, 1055000, 1040000, 1025000, 1010000, 995000, 970000},
            {1139000, 1134000, 1129000, 1109000, 1094000, 1079000, 1064000, 1049000, 1034000, 1009000},
            {1178000, 1173000, 1168000, 1148000, 1133000, 1118000, 1103000, 1088000, 1073000, 1048000},
            {1217000, 1212000, 1207000, 1187000, 1172000, 1157000, 1142000, 1127000, 1112000, 1087000},
            {1256000, 1251000, 1246000, 1226000, 1211000, 1196000, 1181000, 1166000, 1151000, 1126000},
            {1295000, 1290000, 1285000, 1265000, 1250000, 1235000, 1220000, 1205000, 1190000, 1165000},
            {1334000, 1329000, 1324000, 1304000, 1289000, 1274000, 1259000, 1244000, 1229000, 1204000},
            {1373000, 1368000, 1363000, 1343000, 1328000, 1313000, 1298000, 1283000, 1268000, 1243000},
            {1412000, 1407000, 1402000, 1382000, 1367000, 1352000, 1337000, 1322000, 1307000, 1282000},
            {1451000, 1446000, 1441000, 1421000, 1406000, 1391000, 1376000, 1361000, 1346000, 1321000},
            {1490000, 1485000, 1480000, 1460000, 1445000, 1430000, 1415000, 1400000, 1385000, 1360000},
            {1529000, 1524000, 1529000, 1499000, 1484000, 1469000, 1454000, 1439000, 1424000, 1399000},
    };

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
}
