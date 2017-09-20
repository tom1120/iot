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
 * @Description: 上报wifi探针信息
 * @author zhangdaihao
 * @date 2017-09-19 17:54:37
 * @version V1.0   
 *
 */
@Entity
@Table(name = "wifiprobe_upload_info", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class WifiprobeUploadInfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**探针信息*/
	private java.lang.String probeInfo;
	/**mac地址*/
	private java.lang.String mac;
	/**强度值*/
	private java.lang.Integer rssi;
	/**上报时间*/
	private java.lang.String uploadTime;
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
	 *@return: java.lang.String  探针信息
	 */
	@Column(name ="PROBE_INFO",nullable=false,length=50)
	public java.lang.String getProbeInfo(){
		return this.probeInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  探针信息
	 */
	public void setProbeInfo(java.lang.String probeInfo){
		this.probeInfo = probeInfo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  mac地址
	 */
	@Column(name ="MAC",nullable=false,length=50)
	public java.lang.String getMac(){
		return this.mac;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  mac地址
	 */
	public void setMac(java.lang.String mac){
		this.mac = mac;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  强度值
	 */
	@Column(name ="RSSI",nullable=false,precision=10,scale=0)
	public java.lang.Integer getRssi(){
		return this.rssi;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  强度值
	 */
	public void setRssi(java.lang.Integer rssi){
		this.rssi = rssi;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上报时间
	 */
	@Column(name ="UPLOAD_TIME",nullable=true,length=50)
	public java.lang.String getUploadTime(){
		return this.uploadTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上报时间
	 */
	public void setUploadTime(java.lang.String uploadTime){
		this.uploadTime = uploadTime;
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
