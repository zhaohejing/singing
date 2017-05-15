package com.efan.core.secondary;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
public class MovieStyleInfo {
    private Integer id;
    private String pszName;

    @Id
    @Column(name = "ID", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pszName", nullable = true, length = -1)
    public String getPszName() {
        return pszName;
    }

    public void setPszName(String pszName) {
        this.pszName = pszName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieStyleInfo that = (MovieStyleInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (pszName != null ? !pszName.equals(that.pszName) : that.pszName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pszName != null ? pszName.hashCode() : 0);
        return result;
    }
}
