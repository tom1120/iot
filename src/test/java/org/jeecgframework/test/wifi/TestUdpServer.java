package org.jeecgframework.test.wifi;/**
 * Created by zhaoyipc on 2017/9/12.
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/12-15:19
 */
public class TestUdpServer {
    public static final int MAX_MSG_LEN = 1600; //

    public static void start(int port) {
        try {
            DatagramSocket udp = new DatagramSocket(port);
            DatagramPacket dPacket;
            byte[] echo = new byte[1];
            echo[0] = (byte) 1;
            while (true) {
                dPacket = new DatagramPacket(new byte[MAX_MSG_LEN], MAX_MSG_LEN);
                udp.receive(dPacket);
                String result = new String(dPacket.getData(), 0, dPacket.getLength());
                System.out.println(result + " " + new Date(System.currentTimeMillis()).toLocaleString());
                //返回一个字节给探针设备
                InetAddress addr = dPacket.getAddress();
                dPacket = new DatagramPacket(echo, echo.length);
                dPacket.setAddress(addr);
                udp.send(dPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        start(9002);

    }


}
