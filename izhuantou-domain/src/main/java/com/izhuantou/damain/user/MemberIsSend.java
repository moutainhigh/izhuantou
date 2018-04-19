package com.izhuantou.damain.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.izhuantou.damain.BasePojo;

/**
 * 信息发送记录表
 * 
 * @author dear
 * @version 1.0
 */
@Table(name = "member_issend")
public class MemberIsSend extends BasePojo {

    /**
     * 
     */
    private static final long serialVersionUID = -10524470978733167L;

    /**
     * OID
     */
    @Id
    @Column(name = "OID")
    private String OID;
    /**
     * 用户登陆名称
     */
    private String name;
    /**
     * 客户OID
     */
    @Column(name = "clientOID")
    private String clientOID;
    /**
     * 计数
     */
    @Column(name = "sendCount")
    private String sendCount;
    /**
     * 描述
     */
    private String describe0;
    /**
     * 序号
     */
    @Column(name = "NO")
    private Integer NO;
    /**
     * 增加用户OID
     */
    @Column(name = "addUserOID")
    private String addUserOID;
    /**
     * 修改用户OID
     */
    @Column(name = "updUserOID")
    private String updUserOID;
    /**
     * 是否有效
     */
    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 添加的时间
     */
    @Column(name = "updDateTime")
    private Date updDateTime;

    /**
     * 是否更新
     */
    private Boolean refresh;
    /**
     * 版本号
     */
    private Integer version;

    public String getOID() {
	return OID;
    }

    public void setOID(String oID) {
	OID = oID;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getClientOID() {
	return clientOID;
    }

    public void setClientOID(String clientOID) {
	this.clientOID = clientOID;
    }

    public String getSendCount() {
	return sendCount;
    }

    public void setSendCount(String sendCount) {
	this.sendCount = sendCount;
    }

    public String getDescribe0() {
	return describe0;
    }

    public void setDescribe0(String describe0) {
	this.describe0 = describe0;
    }

    public Integer getNO() {
	return NO;
    }

    public void setNO(Integer nO) {
	NO = nO;
    }

    public String getAddUserOID() {
	return addUserOID;
    }

    public void setAddUserOID(String addUserOID) {
	this.addUserOID = addUserOID;
    }

    public String getUpdUserOID() {
	return updUserOID;
    }

    public void setUpdUserOID(String updUserOID) {
	this.updUserOID = updUserOID;
    }

    public Boolean getValid() {
	return valid;
    }

    public void setValid(Boolean valid) {
	this.valid = valid;
    }

    public Date getAddDateTime() {
	return addDateTime;
    }

    public void setAddDateTime(Date addDateTime) {
	this.addDateTime = addDateTime;
    }

    public Date getUpdDateTime() {
	return updDateTime;
    }

    public void setUpdDateTime(Date updDateTime) {
	this.updDateTime = updDateTime;
    }

    public Boolean getRefresh() {
	return refresh;
    }

    public void setRefresh(Boolean refresh) {
	this.refresh = refresh;
    }

    public Integer getVersion() {
	return version;
    }

    public void setVersion(Integer version) {
	this.version = version;
    }

    @Override
    public String toString() {
	return "MemberIsSend [OID=" + OID + ", name=" + name + ", clientOID=" + clientOID + ", sendCount=" + sendCount
		+ ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID + ", updUserOID="
		+ updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime=" + updDateTime
		+ ", refresh=" + refresh + ", version=" + version + "]";
    }

}
