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
 * @Description: 信号-门禁-设备行为
 * @author zhangdaihao
 * @date 2017-09-22 08:25:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "signal_door_interactive", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SignalDoorInteractiveEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**信号设备id*/
	private java.lang.String signalProbeDeviceId;
	/**门禁参数id*/
	private java.lang.String doorInterfaceParamId;
	/**设备关联行为id*/
	private java.lang.String deviceActionId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  信号设备id
	 */
	@Column(name ="SIGNAL_PROBE_DEVICE_ID",nullable=false,length=50)
	public java.lang.String getSignalProbeDeviceId(){
		return this.signalProbeDeviceId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  信号设备id
	 */
	public void setSignalProbeDeviceId(java.lang.String signalProbeDeviceId){
		this.signalProbeDeviceId = signalProbeDeviceId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  门禁参数id
	 */
	@Column(name ="DOOR_INTERFACE_PARAM_ID",nullable=false,length=50)
	public java.lang.String getDoorInterfaceParamId(){
		return this.doorInterfaceParamId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门禁参数id
	 */
	public void setDoorInterfaceParamId(java.lang.String doorInterfaceParamId){
		this.doorInterfaceParamId = doorInterfaceParamId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备关联行为id
	 */
	@Column(name ="DEVICE_ACTION_ID",nullable=false,length=50)
	public java.lang.String getDeviceActionId(){
		return this.deviceActionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备关联行为id
	 */
	public void setDeviceActionId(java.lang.String deviceActionId){
		this.deviceActionId = deviceActionId;
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
