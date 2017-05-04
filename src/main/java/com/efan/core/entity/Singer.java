package com.efan.core.entity;

/**
 * Created by 45425 on 2017/5/4.
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 45425 on 2017/5/4.
 */
@Entity
@Table(name="efan_singer")
public class Singer implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    歌手名称
    @NotNull
    @Column(length = 20)
    private  String singerName;
    //歌手图片
    @Column(length = 200)
    private  String singerImage;

    //歌曲数量
    private  int songsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImage() {
        return singerImage;
    }

    public void setSingerImage(String singerImage) {
        this.singerImage = singerImage;
    }

    public int getSongsCount() {
        return songsCount;
    }

    public void setSongsCount(int songsCount) {
        this.songsCount = songsCount;
    }

    public String getSingerCategory() {
        return singerCategory;
    }

    public void setSingerCategory(String singerCategory) {
        this.singerCategory = singerCategory;
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

    //歌手图片
    @Column(length = 200)
private  String singerCategory;

    private Boolean isDelete;
    private Long creationUserId;

    private String creationTime;

    private Long modifyUserId;

    private String modifyTime;

}

