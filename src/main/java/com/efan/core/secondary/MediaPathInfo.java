package com.efan.core.secondary;

/**
 * 视频路径信息
 */
public class MediaPathInfo {
    private  Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPszIP() {
        return pszIP;
    }

    public void setPszIP(String pszIP) {
        this.pszIP = pszIP;
    }

    public String getPszPathName() {
        return pszPathName;
    }

    public void setPszPathName(String pszPathName) {
        this.pszPathName = pszPathName;
    }

    private  String pszIP;
    private  String pszPathName;
}
