package org.jeecgframework.core.quartz;/**
 * Created by zhaoyipc on 2017/6/26.
 */

import org.quartz.JobDetail;

import java.util.List;

/**
 * @author zhaoyi
 * @date 2017-06-2017/6/26-15:34
 */
public class JobDetailList {
    private List<JobDetail> detailList;

    public List<JobDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<JobDetail> detailList) {
        this.detailList = detailList;
    }
}
