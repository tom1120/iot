package com.jeecg.entity.activiti;

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
 * @Description: 流程事件
 * @author zhangdaihao
 * @date 2017-06-12 11:33:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "act_ru_event_subscr", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ActRuEventSubscrEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**rev*/
	private java.lang.Integer rev;
	/**eventType*/
	private java.lang.String eventType;
	/**eventName*/
	private java.lang.String eventName;
	/**executionId*/
	private java.lang.String executionId;
	/**procInstId*/
	private java.lang.String procInstId;
	/**activityId*/
	private java.lang.String activityId;
	/**configuration*/
	private java.lang.String configuration;
	/**created*/
	private java.util.Date created;
	/**procDefId*/
	private java.lang.String procDefId;
	/**tenantId*/
	private java.lang.String tenantId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID_",nullable=false,length=64)
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
	 *@return: java.lang.Integer  rev
	 */
	@Column(name ="REV_",nullable=true,precision=10,scale=0)
	public java.lang.Integer getRev(){
		return this.rev;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  rev
	 */
	public void setRev(java.lang.Integer rev){
		this.rev = rev;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  eventType
	 */
	@Column(name ="EVENT_TYPE_",nullable=false,length=255)
	public java.lang.String getEventType(){
		return this.eventType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  eventType
	 */
	public void setEventType(java.lang.String eventType){
		this.eventType = eventType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  eventName
	 */
	@Column(name ="EVENT_NAME_",nullable=true,length=255)
	public java.lang.String getEventName(){
		return this.eventName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  eventName
	 */
	public void setEventName(java.lang.String eventName){
		this.eventName = eventName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  executionId
	 */
	@Column(name ="EXECUTION_ID_",nullable=true,length=64)
	public java.lang.String getExecutionId(){
		return this.executionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  executionId
	 */
	public void setExecutionId(java.lang.String executionId){
		this.executionId = executionId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  procInstId
	 */
	@Column(name ="PROC_INST_ID_",nullable=true,length=64)
	public java.lang.String getProcInstId(){
		return this.procInstId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  procInstId
	 */
	public void setProcInstId(java.lang.String procInstId){
		this.procInstId = procInstId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  activityId
	 */
	@Column(name ="ACTIVITY_ID_",nullable=true,length=64)
	public java.lang.String getActivityId(){
		return this.activityId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  activityId
	 */
	public void setActivityId(java.lang.String activityId){
		this.activityId = activityId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  configuration
	 */
	@Column(name ="CONFIGURATION_",nullable=true,length=255)
	public java.lang.String getConfiguration(){
		return this.configuration;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  configuration
	 */
	public void setConfiguration(java.lang.String configuration){
		this.configuration = configuration;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  created
	 */
	@Column(name ="CREATED_",nullable=false)
	public java.util.Date getCreated(){
		return this.created;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  created
	 */
	public void setCreated(java.util.Date created){
		this.created = created;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  procDefId
	 */
	@Column(name ="PROC_DEF_ID_",nullable=true,length=64)
	public java.lang.String getProcDefId(){
		return this.procDefId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  procDefId
	 */
	public void setProcDefId(java.lang.String procDefId){
		this.procDefId = procDefId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  tenantId
	 */
	@Column(name ="TENANT_ID_",nullable=true,length=255)
	public java.lang.String getTenantId(){
		return this.tenantId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  tenantId
	 */
	public void setTenantId(java.lang.String tenantId){
		this.tenantId = tenantId;
	}
}
