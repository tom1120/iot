package org.jeecgframework.test.wifi;/**
 * Created by zhaoyipc on 2017/9/9.
 */

import org.junit.Test;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/9-17:08
 */
public class Distance {

    private Double distance(String rssi){
//        d=10^((ABS(RSSI)-A)/(10*n))
//        通过实验，A值的最佳范围为45—49，n值最佳范围为3.25—4.5，N在15---25。
        Double d;
        int A=49;
        Double n=4.5;
//        d=10^((Integer.valueOf(rssi)-A)/(10*n));

        d=Math.pow(10,(Integer.valueOf(rssi)-A)/(10*n));
        System.out.println("d = " + d);
        return d;
    }
    @Test
    public void testDistance(){
        distance("63");//2m的距离
        distance("50");//1m的距离
        distance("70");//1m的距离
        distance("65");//1m的距离
        distance("80");//1m的距离

    }

}
