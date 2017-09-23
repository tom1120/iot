package com.jeecg.entity.wifiprobe;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 互动设备表
 * @author zhangdaihao
 * @date 2017-09-21 15:21:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "interactive_device", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class InteractiveDeviceEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**产品key*/
	private java.lang.String productKey;
	/**设备推送主题*/
	private java.lang.String topicfullname;
	/**备注*/
	private java.lang.String note;

	private Set<DeviceActionEntity> deviceActionEntities;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品key
	 */
	@Column(name ="PRODUCT_KEY",nullable=false,length=50)
	public java.lang.String getProductKey(){
		return this.productKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品key
	 */
	public void setProductKey(java.lang.String productKey){
		this.productKey = productKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备推送主题
	 */
	@Column(name ="TOPICFULLNAME",nullable=false,length=100)
	public java.lang.String getTopicfullname(){
		return this.topicfullname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备推送主题
	 */
	public void setTopicfullname(java.lang.String topicfullname){
		this.topicfullname = topicfullname;
	}
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "interactiveDeviceEntity")
	public Set<DeviceActionEntity> getDeviceActionEntities() {
		return deviceActionEntities;
	}

	public void setDeviceActionEntities(Set<DeviceActionEntity> deviceActionEntities) {
		this.deviceActionEntities = deviceActionEntities;
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
