package com.efan.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.function.BooleanSupplier;

/**
 * Created by 45425 on 2017/5/4.
 */
@Entity
@Table(name="efan_ratio")
public class Ratio implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    倍率
    @NotNull
    @Column(length = 20)
    private  double ratio;
    //营业时间 起始
    @NotNull
    private Integer fromHour;
    //营业时间 起始截至
    @NotNull
    private Integer toHour;
    //周时间
    @Column(length = 20)
    private   String weekTime;
    //相关点位
    @Column(length = 100)
private  String     PointIds;


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

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public Integer getFromHour() {
        return fromHour;
    }

    public void setFromHour(Integer fromHour) {
        this.fromHour = fromHour;
    }

    public Integer getToHour() {
        return toHour;
    }

    public void setToHour(Integer toHour) {
        this.toHour = toHour;
    }

    public String getWeekTime() {
        return weekTime;
    }

    public void setWeekTime(String weekTime) {
        this.weekTime = weekTime;
    }

    public String getPointIds() {
        return PointIds;
    }

    public void setPointIds(String pointIds) {
        PointIds = pointIds;
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

