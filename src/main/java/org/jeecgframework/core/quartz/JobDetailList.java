package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import org.quartz.JobDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-15:34
 */
public class JobDetailList implements Serializable{
    private List<JobDetail> detailList=new ArrayList<>();

    public List<JobDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<JobDetail> detailList) {
        this.detailList = detailList;
    }
}
