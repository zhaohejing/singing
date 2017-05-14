package com.efan.core.secondary;

/**
 * 电影风格信息
 */
public class MovieStyleInfo {
    //id
    private  Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //名称
    public String getPszName() {
        return pszName;
    }

    public void setPszName(String pszName) {
        this.pszName = pszName;
    }

    private String pszName;
}
