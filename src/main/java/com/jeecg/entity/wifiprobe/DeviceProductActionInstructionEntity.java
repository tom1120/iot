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
 * @Description: 设备产品行为定义表
 * @author zhangdaihao
 * @date 2017-09-22 08:26:44
 * @version V1.0   
 *
 */
@Entity
@Table(name = "device_product_action_instruction", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DeviceProductActionInstructionEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**产品设备类型*/
	private java.lang.Integer deviceProductType;
	/**行动key*/
	private java.lang.String actionKey;
	/**行动描述*/
	private java.lang.String actionDesc;
	/**行动指令json*/
	private java.lang.String actionInstructionJson;

	private Set<DeviceActionEntity> deviceActionEntities;
	/**行为类型*/
	private Integer actionType;

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
	 *@return: java.lang.Integer  产品设备类型
	 */
	@Column(name ="DEVICE_PRODUCT_TYPE",nullable=false,precision=10,scale=0)
	public java.lang.Integer getDeviceProductType(){
		return this.deviceProductType;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  产品设备类型
	 */
	public void setDeviceProductType(java.lang.Integer deviceProductType){
		this.deviceProductType = deviceProductType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行动key
	 */
	@Column(name ="ACTION_KEY",nullable=false,length=50)
	public java.lang.String getActionKey(){
		return this.actionKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行动key
	 */
	public void setActionKey(java.lang.String actionKey){
		this.actionKey = actionKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行动描述
	 */
	@Column(name ="ACTION_DESC",nullable=false,length=50)
	public java.lang.String getActionDesc(){
		return this.actionDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行动描述
	 */
	public void setActionDesc(java.lang.String actionDesc){
		this.actionDesc = actionDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行动指令json
	 */
	@Column(name ="ACTION_INSTRUCTION_JSON",nullable=false,length=2000)
	public java.lang.String getActionInstructionJson(){
		return this.actionInstructionJson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行动指令json
	 */
	public void setActionInstructionJson(java.lang.String actionInstructionJson){
		this.actionInstructionJson = actionInstructionJson;
	}

	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.REFRESH},mappedBy = "deviceProductActionInstructionEntity")
	public Set<DeviceActionEntity> getDeviceActionEntities() {
		return deviceActionEntities;
	}

	public void setDeviceActionEntities(Set<DeviceActionEntity> deviceActionEntities) {
		this.deviceActionEntities = deviceActionEntities;
	}

	@Column(name="action_type",nullable = false)
	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
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
