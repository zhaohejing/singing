package com.efan.core.secondary;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
public class MediaPathInfo {
    private Integer id;
    private String pszIp;
    private String pszPathName;

    @Id
    @Column(name = "ID", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pszIP", nullable = true, length = -1)
    public String getPszIp() {
        return pszIp;
    }

    public void setPszIp(String pszIp) {
        this.pszIp = pszIp;
    }

    @Basic
    @Column(name = "pszPathName", nullable = true, length = -1)
    public String getPszPathName() {
        return pszPathName;
    }

    public void setPszPathName(String pszPathName) {
        this.pszPathName = pszPathName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaPathInfo that = (MediaPathInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (pszIp != null ? !pszIp.equals(that.pszIp) : that.pszIp != null) return false;
        if (pszPathName != null ? !pszPathName.equals(that.pszPathName) : that.pszPathName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pszIp != null ? pszIp.hashCode() : 0);
        result = 31 * result + (pszPathName != null ? pszPathName.hashCode() : 0);
        return result;
    }
}
