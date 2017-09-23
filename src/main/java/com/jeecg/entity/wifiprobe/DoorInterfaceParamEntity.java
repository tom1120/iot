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
 * @Description: 门禁接口参数表
 * @author zhangdaihao
 * @date 2017-09-21 15:21:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "door_interface_param", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DoorInterfaceParamEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**门禁ip*/
	private java.lang.String ipaddr;
	/**门禁控制系数*/
	private java.lang.Integer ndoor;
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
	 *@return: java.lang.String  门禁ip
	 */
	@Column(name ="IPADDR",nullable=false,length=50)
	public java.lang.String getIpaddr(){
		return this.ipaddr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  门禁ip
	 */
	public void setIpaddr(java.lang.String ipaddr){
		this.ipaddr = ipaddr;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  门禁控制系数
	 */
	@Column(name ="NDOOR",nullable=false,precision=10,scale=0)
	public java.lang.Integer getNdoor(){
		return this.ndoor;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  门禁控制系数
	 */
	public void setNdoor(java.lang.Integer ndoor){
		this.ndoor = ndoor;
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
