package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/7/1.
 */

import org.jeecgframework.core.quartz.pojo.QrtzTriggers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-07-2017/7/1-14:45
 */
public class QrtzTriggersList implements Serializable{
    private List<QrtzTriggers> cronTaskList=new ArrayList<>();

    public List<QrtzTriggers> getCronTaskList() {
        return cronTaskList;
    }

    public void setCronTaskList(List<QrtzTriggers> cronTaskList) {
        this.cronTaskList = cronTaskList;
    }
}
