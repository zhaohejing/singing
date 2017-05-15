package com.efan.core.secondary;

import javax.persistence.*;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
@Table(name="singerInfo")
public class SingerInfo {
    private Long unSingerCode;
    private String pszName;
    private String pszSpell;
    private Integer wNameWords;
    private Integer wSingerType;
    private Integer wSingerArea;
    private Integer wNationality;
    private Integer unRanking;
    private Integer unTopRating;
    private Integer unSongs;
    private Integer dwImageExist;
    private String pszProfile;

    @Id
    @Column(name = "unSingerCode", nullable = false)
    public Long getUnSingerCode() {
        return unSingerCode;
    }

    public void setUnSingerCode(Long unSingerCode) {
        this.unSingerCode = unSingerCode;
    }

    @Basic
    @Column(name = "pszName", nullable = true, length = 200)
    public String getPszName() {
        return pszName;
    }

    public void setPszName(String pszName) {
        this.pszName = pszName;
    }

    @Basic
    @Column(name = "pszSpell", nullable = true, length = 200)
    public String getPszSpell() {
        return pszSpell;
    }

    public void setPszSpell(String pszSpell) {
        this.pszSpell = pszSpell;
    }

    @Basic
    @Column(name = "wNameWords", nullable = true)
    public Integer getwNameWords() {
        return wNameWords;
    }

    public void setwNameWords(Integer wNameWords) {
        this.wNameWords = wNameWords;
    }

    @Basic
    @Column(name = "wSingerType", nullable = true)
    public Integer getwSingerType() {
        return wSingerType;
    }

    public void setwSingerType(Integer wSingerType) {
        this.wSingerType = wSingerType;
    }

    @Basic
    @Column(name = "wSingerArea", nullable = true)
    public Integer getwSingerArea() {
        return wSingerArea;
    }

    public void setwSingerArea(Integer wSingerArea) {
        this.wSingerArea = wSingerArea;
    }

    @Basic
    @Column(name = "wNationality", nullable = true)
    public Integer getwNationality() {
        return wNationality;
    }

    public void setwNationality(Integer wNationality) {
        this.wNationality = wNationality;
    }

    @Basic
    @Column(name = "unRanking", nullable = true)
    public Integer getUnRanking() {
        return unRanking;
    }

    public void setUnRanking(Integer unRanking) {
        this.unRanking = unRanking;
    }

    @Basic
    @Column(name = "unTopRating", nullable = true)
    public Integer getUnTopRating() {
        return unTopRating;
    }

    public void setUnTopRating(Integer unTopRating) {
        this.unTopRating = unTopRating;
    }

    @Basic
    @Column(name = "unSongs", nullable = true)
    public Integer getUnSongs() {
        return unSongs;
    }

    public void setUnSongs(Integer unSongs) {
        this.unSongs = unSongs;
    }

    @Basic
    @Column(name = "dwImageExist", nullable = true)
    public Integer getDwImageExist() {
        return dwImageExist;
    }

    public void setDwImageExist(Integer dwImageExist) {
        this.dwImageExist = dwImageExist;
    }

    @Basic
    @Column(name = "pszProfile", nullable = true, length = 200)
    public String getPszProfile() {
        return pszProfile;
    }

    public void setPszProfile(String pszProfile) {
        this.pszProfile = pszProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingerInfo that = (SingerInfo) o;

        if (unSingerCode != that.unSingerCode) return false;
        if (pszName != null ? !pszName.equals(that.pszName) : that.pszName != null) return false;
        if (pszSpell != null ? !pszSpell.equals(that.pszSpell) : that.pszSpell != null) return false;
        if (wNameWords != null ? !wNameWords.equals(that.wNameWords) : that.wNameWords != null) return false;
        if (wSingerType != null ? !wSingerType.equals(that.wSingerType) : that.wSingerType != null) return false;
        if (wSingerArea != null ? !wSingerArea.equals(that.wSingerArea) : that.wSingerArea != null) return false;
        if (wNationality != null ? !wNationality.equals(that.wNationality) : that.wNationality != null) return false;
        if (unRanking != null ? !unRanking.equals(that.unRanking) : that.unRanking != null) return false;
        if (unTopRating != null ? !unTopRating.equals(that.unTopRating) : that.unTopRating != null) return false;
        if (unSongs != null ? !unSongs.equals(that.unSongs) : that.unSongs != null) return false;
        if (dwImageExist != null ? !dwImageExist.equals(that.dwImageExist) : that.dwImageExist != null) return false;
        if (pszProfile != null ? !pszProfile.equals(that.pszProfile) : that.pszProfile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (unSingerCode ^ (unSingerCode >>> 32));
        result = 31 * result + (pszName != null ? pszName.hashCode() : 0);
        result = 31 * result + (pszSpell != null ? pszSpell.hashCode() : 0);
        result = 31 * result + (wNameWords != null ? wNameWords.hashCode() : 0);
        result = 31 * result + (wSingerType != null ? wSingerType.hashCode() : 0);
        result = 31 * result + (wSingerArea != null ? wSingerArea.hashCode() : 0);
        result = 31 * result + (wNationality != null ? wNationality.hashCode() : 0);
        result = 31 * result + (unRanking != null ? unRanking.hashCode() : 0);
        result = 31 * result + (unTopRating != null ? unTopRating.hashCode() : 0);
        result = 31 * result + (unSongs != null ? unSongs.hashCode() : 0);
        result = 31 * result + (dwImageExist != null ? dwImageExist.hashCode() : 0);
        result = 31 * result + (pszProfile != null ? pszProfile.hashCode() : 0);
        return result;
    }
}
