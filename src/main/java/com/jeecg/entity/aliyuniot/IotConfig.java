package com.jeecg.entity.aliyuniot;/**
 * Created by zhaoyipc on 2017/8/3.
 */

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

/**
 * 用于显示注入iot配置参数到容器中，以便调用
 * @author zhaoyi
 * @date 2017-08-2017/8/3-17:26
 */
public class IotConfig {
    public static final Properties mergedProps = new Properties();;
    private Resource configLocation;

    public Properties getMergedProps() {
        return mergedProps;
    }

    public void setConfigLocation(Resource configLocation) throws IOException {
        this.configLocation = configLocation;

        if(this.configLocation!=null){
            PropertiesLoaderUtils.fillProperties(mergedProps, this.configLocation);
        }

    }

    public IotConfig() {

    }




}
