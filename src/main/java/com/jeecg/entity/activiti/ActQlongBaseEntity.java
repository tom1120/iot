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
 * @Description: 流程定义基础功能
 * @author zhangdaihao
 * @date 2017-05-25 14:56:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "act_qlong_base", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ActQlongBaseEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**流程名称*/
	private java.lang.String activitiName;
	/**流程key*/
	private java.lang.String activitiKey;
	/**流程类型*/
	private java.lang.String activitiType;
	/**是否部署*/
	private java.lang.Integer isDeployed;
	/**创建者*/
	private java.lang.String createBy;
	/**创建者名称*/
	private java.lang.String createName;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新者*/
	private java.lang.String updateBy;
	/**更新者名称*/
	private java.lang.String updateName;
	/**更新日期*/
	private java.util.Date updateDate;
	/**备注*/
	private java.lang.String note;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
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
	 *@return: java.lang.String  流程名称
	 */
	@Column(name ="ACTIVITI_NAME",nullable=true,length=100)
	public java.lang.String getActivitiName(){
		return this.activitiName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程名称
	 */
	public void setActivitiName(java.lang.String activitiName){
		this.activitiName = activitiName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程key
	 */
	@Column(name ="ACTIVITI_KEY",nullable=true,length=50)
	public java.lang.String getActivitiKey(){
		return this.activitiKey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程key
	 */
	public void setActivitiKey(java.lang.String activitiKey){
		this.activitiKey = activitiKey;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程类型
	 */
	@Column(name ="ACTIVITI_TYPE",nullable=true,length=4)
	public java.lang.String getActivitiType(){
		return this.activitiType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程类型
	 */
	public void setActivitiType(java.lang.String activitiType){
		this.activitiType = activitiType;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否部署
	 */
	@Column(name ="IS_DEPLOYED",nullable=true,precision=10,scale=0)
	public java.lang.Integer getIsDeployed(){
		return this.isDeployed;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否部署
	 */
	public void setIsDeployed(java.lang.Integer isDeployed){
		this.isDeployed = isDeployed;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建者
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建者
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建者名称
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建者名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新者
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新者
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新者名称
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新者名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
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
