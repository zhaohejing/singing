package com.efan.core.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 45425 on 2017/5/4.
 */
@Entity
@Table(name="efan_point")
public class Point  implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    点位名称
    @NotNull
    @Column(length = 20)
    private  String pointName;
    //机构名称
    @Column(length = 200)
    @NotNull
    private  String orgName;
    //机构id
    @NotNull
    private  int orgId;
    //省份
    @Column(length = 50)
    private  String province;
    //区县
    @Column(length = 50)
    private String  county;
    //地址
    @Column(length = 50)
    private  String address;
    //坐标
    @Column(length = 50)
    @NotNull
     private  String latitude;
    //联系电话
    @Column(length = 50)
    private  String mobile;
    //营业时间 起始
    @NotNull
    private Integer fromHour;
    //营业时间 起始截至
    @NotNull
    private Integer toHour;
    //运营商
    @Column(length = 50)
    @NotNull
    private  String operator;
    //4g卡号
    @Column(length = 50)
    @NotNull
    private  String fourcard;

    private Boolean isDelete;
    private Long creationUserId;

    private String creationTime;

    private Long modifyUserId;

    private String modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getFromHour() {
        return fromHour;
    }

    public void setFromHour(Integer fromHour) {
        this.fromHour = fromHour;
    }

    public Integer getHour() {
        return toHour;
    }

    public void setHour(Integer hour) {
        this.toHour = hour;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getFourcard() {
        return fourcard;
    }

    public void setFourcard(String fourcard) {
        this.fourcard = fourcard;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Long getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(Long creationUserId) {
        this.creationUserId = creationUserId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}
