package com.kito.k6.entity;/**
 * Created by zhaoyipc on 2017/9/15.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/15-9:17
 */
@Table(name = "hrstaffinfo",catalog = "ekito6")
@Entity
public class HrStaffInfo implements Serializable{
    private int staffId;
    private String staffCode;
    private String name;
    private String sexCode;
    private String id;
    private Timestamp birthDate;
    private Integer age;
    private String nationCode;
    private String politicalLandscapeCode;
    private String bloodTypeCode;
    private String maritalStatusCode;
    private Integer birthplaceCityId;
    private Integer houseCityId;
    private Timestamp graduationDate;
    private String educationCode;
    private String degreeCode;
    private String degree;
    private String subject;
    private String familyAddress;
    private String familyZip;
    private String nowAddress;
    private String nowZip;
    private String familyPhone;
    private String mobile;
    private String officePhone;
    private String email;
    private String photo;
    private BigDecimal stature;
    private BigDecimal avoirdupois;
    private Timestamp createDate;
    private Integer operaterId;
    private int isBlack;
    private String description;
    private Integer introductionId;
    private Integer isUnion;
    private boolean status;
    private String remark;
    private String qq;
    private String weixinCode;
    private String mobileWifiMac;
    private Integer mobileWifiMacFlag;

    @Id
    @Column(name = "StaffId", nullable = false)
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    @Basic
    @Column(name = "StaffCode", nullable = false, length = 30)
    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SexCode", nullable = true, length = 4)
    public String getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    @Basic
    @Column(name = "ID", nullable = true, length = 30)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BirthDate", nullable = true)
    public Timestamp getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "NationCode", nullable = true, length = 4)
    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    @Basic
    @Column(name = "PoliticalLandscapeCode", nullable = true, length = 4)
    public String getPoliticalLandscapeCode() {
        return politicalLandscapeCode;
    }

    public void setPoliticalLandscapeCode(String politicalLandscapeCode) {
        this.politicalLandscapeCode = politicalLandscapeCode;
    }

    @Basic
    @Column(name = "BloodTypeCode", nullable = true, length = 4)
    public String getBloodTypeCode() {
        return bloodTypeCode;
    }

    public void setBloodTypeCode(String bloodTypeCode) {
        this.bloodTypeCode = bloodTypeCode;
    }

    @Basic
    @Column(name = "MaritalStatusCode", nullable = true, length = 4)
    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    @Basic
    @Column(name = "BirthplaceCityId", nullable = true)
    public Integer getBirthplaceCityId() {
        return birthplaceCityId;
    }

    public void setBirthplaceCityId(Integer birthplaceCityId) {
        this.birthplaceCityId = birthplaceCityId;
    }

    @Basic
    @Column(name = "HouseCityId", nullable = true)
    public Integer getHouseCityId() {
        return houseCityId;
    }

    public void setHouseCityId(Integer houseCityId) {
        this.houseCityId = houseCityId;
    }

    @Basic
    @Column(name = "GraduationDate", nullable = true)
    public Timestamp getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Timestamp graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Basic
    @Column(name = "EducationCode", nullable = true, length = 4)
    public String getEducationCode() {
        return educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    @Basic
    @Column(name = "DegreeCode", nullable = true, length = 4)
    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
    }

    @Basic
    @Column(name = "Degree", nullable = true, length = 100)
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "Subject", nullable = true, length = 100)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "FamilyAddress", nullable = true, length = 200)
    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    @Basic
    @Column(name = "FamilyZip", nullable = true, length = 30)
    public String getFamilyZip() {
        return familyZip;
    }

    public void setFamilyZip(String familyZip) {
        this.familyZip = familyZip;
    }

    @Basic
    @Column(name = "NowAddress", nullable = true, length = 200)
    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    @Basic
    @Column(name = "NowZip", nullable = true, length = 30)
    public String getNowZip() {
        return nowZip;
    }

    public void setNowZip(String nowZip) {
        this.nowZip = nowZip;
    }

    @Basic
    @Column(name = "FamilyPhone", nullable = true, length = 30)
    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    @Basic
    @Column(name = "Mobile", nullable = true, length = 30)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "OfficePhone", nullable = true, length = 30)
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Photo", nullable = true, length = 100)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "Stature", nullable = true, precision = 2)
    public BigDecimal getStature() {
        return stature;
    }

    public void setStature(BigDecimal stature) {
        this.stature = stature;
    }

    @Basic
    @Column(name = "Avoirdupois", nullable = true, precision = 2)
    public BigDecimal getAvoirdupois() {
        return avoirdupois;
    }

    public void setAvoirdupois(BigDecimal avoirdupois) {
        this.avoirdupois = avoirdupois;
    }

    @Basic
    @Column(name = "CreateDate", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "OperaterId", nullable = true)
    public Integer getOperaterId() {
        return operaterId;
    }

    public void setOperaterId(Integer operaterId) {
        this.operaterId = operaterId;
    }

    @Basic
    @Column(name = "IsBlack", nullable = false)
    public int getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(int isBlack) {
        this.isBlack = isBlack;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "IntroductionId", nullable = true)
    public Integer getIntroductionId() {
        return introductionId;
    }

    public void setIntroductionId(Integer introductionId) {
        this.introductionId = introductionId;
    }

    @Basic
    @Column(name = "IsUnion", nullable = true)
    public Integer getIsUnion() {
        return isUnion;
    }

    public void setIsUnion(Integer isUnion) {
        this.isUnion = isUnion;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Basic
    @Column(name = "Remark", nullable = true, length = 250)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "QQ", nullable = true, length = 50)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "WeixinCode", nullable = true, length = 100)
    public String getWeixinCode() {
        return weixinCode;
    }

    public void setWeixinCode(String weixinCode) {
        this.weixinCode = weixinCode;
    }

    @Basic
    @Column(name = "mobile_wifi_mac", nullable = true, length = 50)
    public String getMobileWifiMac() {
        return mobileWifiMac;
    }

    public void setMobileWifiMac(String mobileWifiMac) {
        this.mobileWifiMac = mobileWifiMac;
    }

    @Basic
    @Column(name = "mobile_wifi_mac_flag", nullable = true)
    public Integer getMobileWifiMacFlag() {
        return mobileWifiMacFlag;
    }

    public void setMobileWifiMacFlag(Integer mobileWifiMacFlag) {
        this.mobileWifiMacFlag = mobileWifiMacFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HrStaffInfo that = (HrStaffInfo) o;

        if (staffId != that.staffId) return false;
        if (isBlack != that.isBlack) return false;
        if (status != that.status) return false;
        if (staffCode != null ? !staffCode.equals(that.staffCode) : that.staffCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (sexCode != null ? !sexCode.equals(that.sexCode) : that.sexCode != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (nationCode != null ? !nationCode.equals(that.nationCode) : that.nationCode != null) return false;
        if (politicalLandscapeCode != null ? !politicalLandscapeCode.equals(that.politicalLandscapeCode) : that.politicalLandscapeCode != null)
            return false;
        if (bloodTypeCode != null ? !bloodTypeCode.equals(that.bloodTypeCode) : that.bloodTypeCode != null)
            return false;
        if (maritalStatusCode != null ? !maritalStatusCode.equals(that.maritalStatusCode) : that.maritalStatusCode != null)
            return false;
        if (birthplaceCityId != null ? !birthplaceCityId.equals(that.birthplaceCityId) : that.birthplaceCityId != null)
            return false;
        if (houseCityId != null ? !houseCityId.equals(that.houseCityId) : that.houseCityId != null) return false;
        if (graduationDate != null ? !graduationDate.equals(that.graduationDate) : that.graduationDate != null)
            return false;
        if (educationCode != null ? !educationCode.equals(that.educationCode) : that.educationCode != null)
            return false;
        if (degreeCode != null ? !degreeCode.equals(that.degreeCode) : that.degreeCode != null) return false;
        if (degree != null ? !degree.equals(that.degree) : that.degree != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        if (familyAddress != null ? !familyAddress.equals(that.familyAddress) : that.familyAddress != null)
            return false;
        if (familyZip != null ? !familyZip.equals(that.familyZip) : that.familyZip != null) return false;
        if (nowAddress != null ? !nowAddress.equals(that.nowAddress) : that.nowAddress != null) return false;
        if (nowZip != null ? !nowZip.equals(that.nowZip) : that.nowZip != null) return false;
        if (familyPhone != null ? !familyPhone.equals(that.familyPhone) : that.familyPhone != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (officePhone != null ? !officePhone.equals(that.officePhone) : that.officePhone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (photo != null ? !photo.equals(that.photo) : that.photo != null) return false;
        if (stature != null ? !stature.equals(that.stature) : that.stature != null) return false;
        if (avoirdupois != null ? !avoirdupois.equals(that.avoirdupois) : that.avoirdupois != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (operaterId != null ? !operaterId.equals(that.operaterId) : that.operaterId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (introductionId != null ? !introductionId.equals(that.introductionId) : that.introductionId != null)
            return false;
        if (isUnion != null ? !isUnion.equals(that.isUnion) : that.isUnion != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (weixinCode != null ? !weixinCode.equals(that.weixinCode) : that.weixinCode != null) return false;
        if (mobileWifiMac != null ? !mobileWifiMac.equals(that.mobileWifiMac) : that.mobileWifiMac != null)
            return false;
        if (mobileWifiMacFlag != null ? !mobileWifiMacFlag.equals(that.mobileWifiMacFlag) : that.mobileWifiMacFlag != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = staffId;
        result = 31 * result + (staffCode != null ? staffCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sexCode != null ? sexCode.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (nationCode != null ? nationCode.hashCode() : 0);
        result = 31 * result + (politicalLandscapeCode != null ? politicalLandscapeCode.hashCode() : 0);
        result = 31 * result + (bloodTypeCode != null ? bloodTypeCode.hashCode() : 0);
        result = 31 * result + (maritalStatusCode != null ? maritalStatusCode.hashCode() : 0);
        result = 31 * result + (birthplaceCityId != null ? birthplaceCityId.hashCode() : 0);
        result = 31 * result + (houseCityId != null ? houseCityId.hashCode() : 0);
        result = 31 * result + (graduationDate != null ? graduationDate.hashCode() : 0);
        result = 31 * result + (educationCode != null ? educationCode.hashCode() : 0);
        result = 31 * result + (degreeCode != null ? degreeCode.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (familyAddress != null ? familyAddress.hashCode() : 0);
        result = 31 * result + (familyZip != null ? familyZip.hashCode() : 0);
        result = 31 * result + (nowAddress != null ? nowAddress.hashCode() : 0);
        result = 31 * result + (nowZip != null ? nowZip.hashCode() : 0);
        result = 31 * result + (familyPhone != null ? familyPhone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (officePhone != null ? officePhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (stature != null ? stature.hashCode() : 0);
        result = 31 * result + (avoirdupois != null ? avoirdupois.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (operaterId != null ? operaterId.hashCode() : 0);
        result = 31 * result + isBlack;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (introductionId != null ? introductionId.hashCode() : 0);
        result = 31 * result + (isUnion != null ? isUnion.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (weixinCode != null ? weixinCode.hashCode() : 0);
        result = 31 * result + (mobileWifiMac != null ? mobileWifiMac.hashCode() : 0);
        result = 31 * result + (mobileWifiMacFlag != null ? mobileWifiMacFlag.hashCode() : 0);
        return result;
    }
}
