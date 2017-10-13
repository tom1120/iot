package com.jeecg.entity.wifiprobe;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**   
 * @Title: Entity
 * @Description: wifi探针参数设置
 * @author zhangdaihao
 * @date 2017-09-21 10:10:17
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wifiprobe_sys_param", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class WifiprobeSysParamEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**缓存数*/
	private java.lang.Integer cacheNumber;
	/**临界强度值*/
	private java.lang.Integer rssi;
	/**最小临界值*/
	private java.lang.Integer rssiMin;
	/**打开客户端后延时*/
	private java.lang.Integer afterOpenClientDelayTime;
	/**关闭客户端前延时*/
	private java.lang.Integer beforeCloseClientDelayTime;
	/**系统参数刷新时间*/
	private java.lang.String refreshTime;
	/**是否默认参数*/
	private Integer isDefault;
	/**备注*/
	private java.lang.String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=50)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  缓存数
	 */
	@Column(name ="CACHE_NUMBER",nullable=false,precision=10,scale=0)
	public java.lang.Integer getCacheNumber(){
		return this.cacheNumber;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  缓存数
	 */
	public void setCacheNumber(java.lang.Integer cacheNumber){
		this.cacheNumber = cacheNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  临界强度值
	 */
	@Column(name ="RSSI",nullable=false,precision=10,scale=0)
	public java.lang.Integer getRssi(){
		return this.rssi;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  临界强度值
	 */
	public void setRssi(java.lang.Integer rssi){
		this.rssi = rssi;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  最小临界值
	 */
	@Column(name ="RSSI_MIN",nullable=false,precision=10,scale=0)
	public java.lang.Integer getRssiMin(){
		return this.rssiMin;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  最小临界值
	 */
	public void setRssiMin(java.lang.Integer rssiMin){
		this.rssiMin = rssiMin;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  打开客户端后延时
	 */
	@Column(name ="AFTER_OPEN_CLIENT_DELAY_TIME",nullable=true,precision=10,scale=0)
	public java.lang.Integer getAfterOpenClientDelayTime(){
		return this.afterOpenClientDelayTime;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  打开客户端后延时
	 */
	public void setAfterOpenClientDelayTime(java.lang.Integer afterOpenClientDelayTime){
		this.afterOpenClientDelayTime = afterOpenClientDelayTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  关闭客户端前延时
	 */
	@Column(name ="BEFORE_CLOSE_CLIENT_DELAY_TIME",nullable=true,precision=10,scale=0)
	public java.lang.Integer getBeforeCloseClientDelayTime(){
		return this.beforeCloseClientDelayTime;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  关闭客户端前延时
	 */
	public void setBeforeCloseClientDelayTime(java.lang.Integer beforeCloseClientDelayTime){
		this.beforeCloseClientDelayTime = beforeCloseClientDelayTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  系统参数刷新时间
	 */
	@Column(name ="REFRESH_TIME",nullable=true,length=50)
	public java.lang.String getRefreshTime(){
		return this.refreshTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  系统参数刷新时间
	 */
	public void setRefreshTime(java.lang.String refreshTime){
		this.refreshTime = refreshTime;
	}

	@Column(name = "is_default")
	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="NOTE",nullable=true,length=255)
	public java.lang.String getNote(){
		return this.note;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setNote(java.lang.String note){
		this.note = note;
	}
}
