package com.kito.util.qr;/**
 * Created by zhaoyipc on 2017/8/17.
 */

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/17-14:16
 */
public class TestQr {
    public static void main(String[] args) {
        try {
            String jsonstring="{\"accessKey\":}";
            QrcodeUtils.gen(jsonstring,"F:/test/qr/1.jpg",400,400);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
