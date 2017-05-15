package com.efan.core.secondary;

import javax.persistence.*;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
@Table(name="mediaPathInfo")
public class MediaPathInfo {
    private Long id;
    private String pszIp;
    private String pszPathName;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pszIP", nullable = true, length = 300)
    public String getPszIp() {
        return pszIp;
    }

    public void setPszIp(String pszIp) {
        this.pszIp = pszIp;
    }

    @Basic
    @Column(name = "pszPathName", nullable = true, length = 200)
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

        if (id != that.id) return false;
        if (pszIp != null ? !pszIp.equals(that.pszIp) : that.pszIp != null) return false;
        if (pszPathName != null ? !pszPathName.equals(that.pszPathName) : that.pszPathName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pszIp != null ? pszIp.hashCode() : 0);
        result = 31 * result + (pszPathName != null ? pszPathName.hashCode() : 0);
        return result;
    }
}
