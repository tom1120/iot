package org.jeecgframework.test.code;/**
 * Created by zhaoyipc on 2017/8/3.
 */

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/3-13:49
 */
public class ThreadTest {
    private static String msg;


    public static void main(String[] args) {
        msg="测试";
        System.out.println("主线程执行！");
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Thread thread=new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("子线程执行！"+msg);
                    }
                }
        );
        thread.start();

    }
}
