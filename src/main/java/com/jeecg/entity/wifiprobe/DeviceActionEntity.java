package com.jeecg.entity.wifiprobe;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 设备关联行为表
 * @author zhangdaihao
 * @date 2017-09-22 08:27:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "device_action", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DeviceActionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**互动设备id*/
//	private java.lang.String interactiveDeviceId;

	private InteractiveDeviceEntity interactiveDeviceEntity;
	/**绑定行为id*/
//	private java.lang.String deviceProductActionInstructionId;
	private DeviceProductActionInstructionEntity deviceProductActionInstructionEntity;

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
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "interactive_device_id")
	public InteractiveDeviceEntity getInteractiveDeviceEntity() {
		return interactiveDeviceEntity;
	}

	public void setInteractiveDeviceEntity(InteractiveDeviceEntity interactiveDeviceEntity) {
		this.interactiveDeviceEntity = interactiveDeviceEntity;
	}


	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  互动设备id
	 */
/*	@Column(name ="INTERACTIVE_DEVICE_ID",nullable=false,length=50)
	public java.lang.String getInteractiveDeviceId(){
		return this.interactiveDeviceId;
	}*/

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  互动设备id
	 */
/*	public void setInteractiveDeviceId(java.lang.String interactiveDeviceId){
		this.interactiveDeviceId = interactiveDeviceId;
	}*/
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  绑定行为id
	 */
//	@Column(name ="DEVICE_PRODUCT_ACTION_INSTRUCTION_ID",nullable=false,length=50)
//	public java.lang.String getDeviceProductActionInstructionId(){
//		return this.deviceProductActionInstructionId;
//	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  绑定行为id
	 */
//	public void setDeviceProductActionInstructionId(java.lang.String deviceProductActionInstructionId){
//		this.deviceProductActionInstructionId = deviceProductActionInstructionId;
//	}

	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinColumn(name = "device_product_action_instruction_id")
	public DeviceProductActionInstructionEntity getDeviceProductActionInstructionEntity() {
		return deviceProductActionInstructionEntity;
	}

	public void setDeviceProductActionInstructionEntity(DeviceProductActionInstructionEntity deviceProductActionInstructionEntity) {
		this.deviceProductActionInstructionEntity = deviceProductActionInstructionEntity;
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
