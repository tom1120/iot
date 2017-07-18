package org.jeecgframework.test.code;/**
 * Created by zhaoyipc on 2017/7/15.
 */

import java.io.UnsupportedEncodingException;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/15-16:48
 */
public class CodeTestTwo {
    public static void main(String[] args) throws UnsupportedEncodingException {
//        str2all("0 产品型号描述");
//        str4all("30000900A74EC1548B57F753CF63F08F");

        str4all("230D0A23467269204A756C2030372031333A35363A33352043535420323031370D0A");
    }

    /**
     * 尝试所有编码格式对十六进制数字字符串进行编码
     *
     * @param uStr
     * @throws UnsupportedEncodingException
     */
    public static void str4all(String uStr) throws UnsupportedEncodingException{

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        byte[] bs = new byte[uStr.length()/2];
        for (int i = 0; i < bs.length; i++) {
            bs[i] = (byte) Integer.parseInt(uStr.substring(i*2, i*2+2), 16);
        }

        System.out.println(new String(bs, "utf-8"));
        // 16
        System.out.println(new String(bs, "utf-16")); // 同unicode
        System.out.println(new String(bs, "utf-16le"));
        System.out.println(new String(bs, "x-utf-16le-bom"));
        System.out.println(new String(bs, "utf-16be"));
//  System.out.println(new String(bs, "x-utf-16be-bom")); // UnsupportedEncodingException
        // 32
        System.out.println(new String(bs, "utf-32"));
        System.out.println(new String(bs, "utf-32le"));
        System.out.println(new String(bs, "x-utf-32le-bom"));
        System.out.println(new String(bs, "utf-32be"));
        System.out.println(new String(bs, "x-utf-32le-bom"));
    }
    /**
     * 列出所有编码对应的解码后的十六进制数字字符串
     *
     * @param uStr
     * @throws UnsupportedEncodingException
     */
    public static void str2all(String uStr) throws UnsupportedEncodingException {

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        byte[] bs = new byte[]{};

        bs = uStr.getBytes("utf-8");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        // 16
        bs = uStr.getBytes("utf-16");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("utf-16le");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("x-utf-16le-bom");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("utf-16be");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
//  bs = uStr.getBytes("x-utf-16be-bom"); // UnsupportedEncodingException
        // 32
        bs = uStr.getBytes("utf-32");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("utf-32le");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("x-utf-32le-bom");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("utf-32be");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
        bs = uStr.getBytes("x-utf-32le-bom");
        for(byte b:bs){
            System.out.print(Integer.toHexString(b & 0xff));
        }
        System.out.println();
    }
}
