package org.jeecgframework.web.cronwork;

/**
 * Created by zhaoyipc on 2017/6/16.
 */
public interface FineReportSendService {
    /**
     * 国内业务战区新产品月度完成率
     * @return
     */
    boolean executeNewProductSend();

    /**
     * 销售统计表
     * @return
     */
    boolean executeSaleImgSend();
    /**
     * 生产基地入库统计
     * @return
     */
    boolean executeFactoryProductInSend();
    /**
     * 成品仓实时库存
     * @return
     */
    boolean executeProductRealtimeStoreSend();
    /**
     * 渠道实时库龄分析
     * @return
     */
    boolean executeChannelRealtimeStoreAgeSend();
    /**
     * 国贸业务看灯管理
     * @return
     */
    boolean executeForeignTradeLightSend();
}
