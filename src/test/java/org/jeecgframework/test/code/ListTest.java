package org.jeecgframework.test.code;/**
 * Created by zhaoyipc on 2017/9/20.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/20-16:45
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>();
        list.add("aa");
        list.add("bb");
        list.add("cc");

        List<String> list2=new ArrayList<String>();
        list2=list;
        for(String s2:list2){
            System.out.println("s2 = " + s2);
        }
        list.clear();
        for(String s2:list2){
            System.out.println("s2 clear = " + s2);
        }

    }
}
