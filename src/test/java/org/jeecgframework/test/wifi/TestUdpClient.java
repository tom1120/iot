package org.jeecgframework.test.wifi;/**
 * Created by zhaoyipc on 2017/9/12.
 */

import java.io.IOException;
import java.net.*;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/12-15:21
 */
public class TestUdpClient {

    public static void sendUdp(){
        DatagramSocket ds = null;  //建立套间字udpsocket服务

        try {
            ds = new DatagramSocket(8999);  //实例化套间字，指定自己的port
        } catch (SocketException e) {
            System.out.println("Cannot open port!");
            System.exit(1);
        }

        byte[] buf= "Hello, I am sender A!".getBytes();  //数据
        InetAddress destination = null ;
        try {
            destination = InetAddress.getByName("192.168.100.18");  //需要发送的地址
        } catch (UnknownHostException e) {
            System.out.println("Cannot open findhost!");
            System.exit(1);
        }
        DatagramPacket dp =
                new DatagramPacket(buf, buf.length, destination , 9002);
        //打包到DatagramPacket类型中（DatagramSocket的send()方法接受此类，注意10000是接受地址的端口，不同于自己的端口！）

        try {
            ds.send(dp);  //发送数据
        } catch (IOException e) {
        }
        ds.close();
    }

    public static void main(String[] args) {
        sendUdp();
    }
}
