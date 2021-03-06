package com.izhuantou.damain.p2p;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.izhuantou.damain.BasePojo;

/**
 * AppBanner资源信息表
 * 
 * @author aries
 * @version 1.0
 */
@Table(name = "p2p_pageappbanner")
public class P2pPageAppBanner extends BasePojo {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4320343881502355592L;

	@Id
    @Column(name = "OID")
    private String OID;

    /**
     * app首页轮播标题
     */
    private String name;
    /**
     * 跳转地址
     */
    private String appurl;

    /**
     * banner缩略图
     */
    @Column(name = "picimgOID")
    private String picimgOID;
    /**
     * banner图片路径
     */
    private String picimg;
    /**
     * 轮播广告类型(0：已下线，1：投放中)
     */
    private Integer state;
    /**
     * 投放起始时间
     */
    private Date startTime;
    /**
     * 投放终止时间
     */
    private Date endTime;
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
     * 是否有效(1:有效 0:删除 )
     * 2018-06-20添加0,1区分 假删操作
     */
    private Boolean valid;

    /**
     * 添加的时间
     */
    @Column(name = "addDateTime")
    private Date addDateTime;
    /**
     * 更新的时间
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

    /**
     * 父结点OID
     */
    @Column(name = "parentOID")
    private String parentOID;

    /**
     * 父结点结点Class
     */
    @Column(name = "branchEntity")
    private String branchEntity;
    /**
     * 日期路径
     */
    @Column(name = "datePath")
    private String datePath;
    
    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
    
    public String getAppurl() {
		return appurl;
	}

	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}

	public String getPicimg() {
	return picimg;
    }

    public void setPicimg(String picimg) {
	this.picimg = picimg;
    }

    public String getPicimgOID() {
	return picimgOID;
    }

    public void setPicimgOID(String picimgOID) {
	this.picimgOID = picimgOID;
    }

    public String getDatePath() {
	return datePath;
    }

    public void setDatePath(String datePath) {
	this.datePath = datePath;
    }

    public String getParentOID() {
	return parentOID;
    }

    public void setParentOID(String parentOID) {
	this.parentOID = parentOID;
    }

    public String getBranchEntity() {
	return branchEntity;
    }

    public void setBranchEntity(String branchEntity) {
	this.branchEntity = branchEntity;
    }

    @Override
	public String toString() {
		return "P2pPageBanner [OID=" + OID + ", name=" + name + ", appurl=" + appurl + ", picimgOID=" + picimgOID + ", picimg=" + picimg + ", state=" + state + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", describe0=" + describe0 + ", NO=" + NO + ", addUserOID=" + addUserOID
				+ ", updUserOID=" + updUserOID + ", valid=" + valid + ", addDateTime=" + addDateTime + ", updDateTime="
				+ updDateTime + ", refresh=" + refresh + ", version=" + version + ", parentOID=" + parentOID
				+ ", branchEntity=" + branchEntity + ", datePath=" + datePath + "]";
	}

}
