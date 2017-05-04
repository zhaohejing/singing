package com.efan.core.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 45425 on 2017/5/4.
 */
@Entity
@Table(name="efan_song")
public class Song implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    歌曲名称
    @NotNull
    @Column(length = 20)
    private  String songName;
    //歌曲编号
    @NotNull
    @Column(length = 200)
    private  String songNum;
    //歌手
    @Column(length = 50)
    private  String singer;
//歌曲分类
    @Column(length = 50)
    private  String songCategory;
    //语种
    @Column(length = 20)
    private String language;

    private Boolean isDelete;
    //是否常用
    private  Boolean isCommon;

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

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongNum() {
        return songNum;
    }

    public void setSongNum(String songNum) {
        this.songNum = songNum;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongCategory() {
        return songCategory;
    }

    public void setSongCategory(String songCategory) {
        this.songCategory = songCategory;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getCommon() {
        return isCommon;
    }

    public void setCommon(Boolean common) {
        isCommon = common;
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
