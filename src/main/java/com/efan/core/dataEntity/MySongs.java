package com.efan.core.dataEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 我点过的歌
 */
@Entity
@Table(name="efan_mysongs")
public class MySongs implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户唯一id
    @NotNull
    @Column(length = 50)
    private String userKey;
    //点歌歌名
    @Column(length = 50)
    private  String  songName;
    //演唱者
    @Column(length = 50)
    private  String singer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
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

    private  Boolean state;

    private Long creationUserId;

    private String creationTime;

    private Long modifyUserId;

    private String modifyTime;

}
