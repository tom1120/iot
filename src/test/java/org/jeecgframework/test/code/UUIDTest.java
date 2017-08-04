package org.jeecgframework.test.code;/**
 * Created by zhaoyipc on 2017/8/2.
 */

import org.jeecgframework.core.util.UUIDGenerator;
import org.junit.Test;

import java.util.UUID;

/**
 * @author zhaoyi
 * @date 2017-08-2017/8/2-15:08
 */
public class UUIDTest {
    @Test
    public  void testUUID(){
       UUID uuid= UUID.randomUUID();
        System.out.println("uuid.toString() = " + uuid.toString());
    }
    @Test
    public void testJeecgUUID(){
        String uuid=UUIDGenerator.generate();
        System.out.println("uuid = " + uuid);
    }

}
