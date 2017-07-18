package org.jeecgframework.test.code;/**
 * Created by zhaoyipc on 2017/7/15.
 */

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/15-16:21
 */
public class CodeTest {
    @Test
    public void testOx2Str(){
        String str="王江波是一个小朋友，HOHOHOHO";
        System.out.println(enUnicode(str));
        //打印出   738B6C5F6CE2662F4E004E2A5C0F670B53CBFF0C0048004F0048004F0048004F0048004F
        str="738B6C5F6CE2662F4E004E2A5C0F670B53CBFF0C0048004F0048004F0048004F0048004F";
        System.out.println(deUnicode(str));
        //打印出   王江波是一个小朋友，HOHOHOHO

        str="230D0A23467269204A756C2030372031333A35363A33352043535420323031370D0A";
        System.out.println(deUnicode(str));
    }

    public static String deUnicode(String content){//将16进制数转换为汉字
        String enUnicode=null;
        String deUnicode=null;
        for(int i=0;i<content.length();i++){
            if(enUnicode==null){
                enUnicode=String.valueOf(content.charAt(i));
            }else{
                enUnicode=enUnicode+content.charAt(i);
            }
            if(i%4==3){
                if(enUnicode!=null){
                    if(deUnicode==null){
                        deUnicode=String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
                    }else{
                        deUnicode=deUnicode+String.valueOf((char)Integer.valueOf(enUnicode, 16).intValue());
                    }
                }
                enUnicode=null;
            }

        }
        return deUnicode;
    }
    public static String enUnicode(String content){//将汉字转换为16进制数
        String enUnicode=null;
        for(int i=0;i<content.length();i++){
            if(i==0){
                enUnicode=getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
            }else{
                enUnicode=enUnicode+getHexString(Integer.toHexString(content.charAt(i)).toUpperCase());
            }
        }
        return enUnicode;
    }
    private static String getHexString(String hexString){
        String hexStr="";
        for(int i=hexString.length();i<4;i++){
            if(i==hexString.length())
                hexStr="0";
            else
                hexStr=hexStr+"0";
        }
        return hexStr+hexString;
    }





}
