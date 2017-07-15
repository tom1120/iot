package com.jeecg.entity.aliyuniot;

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
 * @Description: 阿里云iot产品表
 * @author zhangdaihao
 * @date 2017-07-14 08:53:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ProductInfo", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class ProductInfoEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**产品名称*/
	private java.lang.String productname;
	/**产品key*/
	private java.lang.String productkey;
	/**产品类型id*/
	private java.lang.Long catid;
	/**产品描述*/
	private java.lang.String productdesc;
	/**创建时间*/
	private java.lang.String gmtcreate;
	/**修改时间*/
	private java.lang.String gmtmodified;
	/**产品密钥*/
	private java.lang.String productsecret;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=150)
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
	 *@return: java.lang.String  产品名称
	 */
	@Column(name ="PRODUCTNAME",nullable=true,length=200)
	public java.lang.String getProductname(){
		return this.productname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品名称
	 */
	public void setProductname(java.lang.String productname){
		this.productname = productname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品key
	 */
	@Column(name ="PRODUCTKEY",nullable=true,length=150)
	public java.lang.String getProductkey(){
		return this.productkey;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品key
	 */
	public void setProductkey(java.lang.String productkey){
		this.productkey = productkey;
	}
	/**
	 *方法: 取得java.lang.Object
	 *@return: java.lang.Object  产品类型id
	 */
	@Column(name ="CATID",nullable=true,length=16777215)
	public java.lang.Long getCatid(){
		return this.catid;
	}

	/**
	 *方法: 设置java.lang.Object
	 *@param: java.lang.Object  产品类型id
	 */
	public void setCatid(java.lang.Long catid){
		this.catid = catid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品描述
	 */
	@Column(name ="PRODUCTDESC",nullable=true,length=200)
	public java.lang.String getProductdesc(){
		return this.productdesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品描述
	 */
	public void setProductdesc(java.lang.String productdesc){
		this.productdesc = productdesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建时间
	 */
	@Column(name ="GMTCREATE",nullable=true,length=50)
	public java.lang.String getGmtcreate(){
		return this.gmtcreate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建时间
	 */
	public void setGmtcreate(java.lang.String gmtcreate){
		this.gmtcreate = gmtcreate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改时间
	 */
	@Column(name ="GMTMODIFIED",nullable=true,length=50)
	public java.lang.String getGmtmodified(){
		return this.gmtmodified;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改时间
	 */
	public void setGmtmodified(java.lang.String gmtmodified){
		this.gmtmodified = gmtmodified;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产品密钥
	 */
	@Column(name ="PRODUCTSECRET",nullable=true,length=200)
	public java.lang.String getProductsecret(){
		return this.productsecret;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产品密钥
	 */
	public void setProductsecret(java.lang.String productsecret){
		this.productsecret = productsecret;
	}
}
