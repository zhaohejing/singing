package com.efan.core.secondary;

import javax.persistence.*;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
@Table(name="songStyleInfo")

public class SongStyleInfo {
    private Long id;
    private String pszName;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pszName", nullable = true, length = 200)
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

        SongStyleInfo that = (SongStyleInfo) o;

        if (id != that.id) return false;
        if (pszName != null ? !pszName.equals(that.pszName) : that.pszName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pszName != null ? pszName.hashCode() : 0);
        return result;
    }
}
