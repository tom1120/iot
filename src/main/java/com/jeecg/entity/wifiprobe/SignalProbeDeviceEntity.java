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
 * @Description: 信号探测设备表
 * @author zhangdaihao
 * @date 2017-09-21 15:20:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "signal_probe_device", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SignalProbeDeviceEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**信号设备探测类型*/
	private java.lang.Integer signDeviceType;
	/**信号设备标识参数*/
	private java.lang.String signDeviceParam;
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
	 *@return: java.lang.Integer  信号设备探测类型
	 */
	@Column(name ="SIGN_DEVICE_TYPE",nullable=false,precision=10,scale=0)
	public java.lang.Integer getSignDeviceType(){
		return this.signDeviceType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  信号设备探测类型
	 */
	public void setSignDeviceType(java.lang.Integer signDeviceType){
		this.signDeviceType = signDeviceType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信号设备标识参数
	 */
	@Column(name ="SIGN_DEVICE_PARAM",nullable=false,length=255)
	public java.lang.String getSignDeviceParam(){
		return this.signDeviceParam;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信号设备标识参数
	 */
	public void setSignDeviceParam(java.lang.String signDeviceParam){
		this.signDeviceParam = signDeviceParam;
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
