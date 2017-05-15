package com.efan.core.secondary;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
public class SongInfo {
    private Short id;
    private Short ullSongCode;
    private Short unSongCode;
    private String pszName;
    private String pszSpell;
    private Short wNameWords;
    private Short wLanguage;
    private Short wCategory;
    private Short wSingers;
    private Short wVersions;
    private Short wStyles;
    private String arrSingers;
    private String arrVersions;
    private String arrStyles;
    private Short unDateTime;
    private Short unRanking;
    private Short unTopRating;
    private String pszFileName;
    private Short unFilePath;
    private Short unDuration;
    private Short ullFileSize;
    private Short unMaxBitrate;
    private Short wEncrypt;
    private String tVideo;
    private String tAccompany;
    private String tOriginal;

    @Id
    @Column(name = "ID", nullable = true)
    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ullSongCode", nullable = true)
    public Short getUllSongCode() {
        return ullSongCode;
    }

    public void setUllSongCode(Short ullSongCode) {
        this.ullSongCode = ullSongCode;
    }

    @Basic
    @Column(name = "unSongCode", nullable = true)
    public Short getUnSongCode() {
        return unSongCode;
    }

    public void setUnSongCode(Short unSongCode) {
        this.unSongCode = unSongCode;
    }

    @Basic
    @Column(name = "pszName", nullable = true, length = -1)
    public String getPszName() {
        return pszName;
    }

    public void setPszName(String pszName) {
        this.pszName = pszName;
    }

    @Basic
    @Column(name = "pszSpell", nullable = true, length = -1)
    public String getPszSpell() {
        return pszSpell;
    }

    public void setPszSpell(String pszSpell) {
        this.pszSpell = pszSpell;
    }

    @Basic
    @Column(name = "wNameWords", nullable = true)
    public Short getwNameWords() {
        return wNameWords;
    }

    public void setwNameWords(Short wNameWords) {
        this.wNameWords = wNameWords;
    }

    @Basic
    @Column(name = "wLanguage", nullable = true)
    public Short getwLanguage() {
        return wLanguage;
    }

    public void setwLanguage(Short wLanguage) {
        this.wLanguage = wLanguage;
    }

    @Basic
    @Column(name = "wCategory", nullable = true)
    public Short getwCategory() {
        return wCategory;
    }

    public void setwCategory(Short wCategory) {
        this.wCategory = wCategory;
    }

    @Basic
    @Column(name = "wSingers", nullable = true)
    public Short getwSingers() {
        return wSingers;
    }

    public void setwSingers(Short wSingers) {
        this.wSingers = wSingers;
    }

    @Basic
    @Column(name = "wVersions", nullable = true)
    public Short getwVersions() {
        return wVersions;
    }

    public void setwVersions(Short wVersions) {
        this.wVersions = wVersions;
    }

    @Basic
    @Column(name = "wStyles", nullable = true)
    public Short getwStyles() {
        return wStyles;
    }

    public void setwStyles(Short wStyles) {
        this.wStyles = wStyles;
    }

    @Basic
    @Column(name = "arrSingers", nullable = true, length = -1)
    public String getArrSingers() {
        return arrSingers;
    }

    public void setArrSingers(String arrSingers) {
        this.arrSingers = arrSingers;
    }

    @Basic
    @Column(name = "arrVersions", nullable = true, length = -1)
    public String getArrVersions() {
        return arrVersions;
    }

    public void setArrVersions(String arrVersions) {
        this.arrVersions = arrVersions;
    }

    @Basic
    @Column(name = "arrStyles", nullable = true, length = -1)
    public String getArrStyles() {
        return arrStyles;
    }

    public void setArrStyles(String arrStyles) {
        this.arrStyles = arrStyles;
    }

    @Basic
    @Column(name = "unDateTime", nullable = true)
    public Short getUnDateTime() {
        return unDateTime;
    }

    public void setUnDateTime(Short unDateTime) {
        this.unDateTime = unDateTime;
    }

    @Basic
    @Column(name = "unRanking", nullable = true)
    public Short getUnRanking() {
        return unRanking;
    }

    public void setUnRanking(Short unRanking) {
        this.unRanking = unRanking;
    }

    @Basic
    @Column(name = "unTopRating", nullable = true)
    public Short getUnTopRating() {
        return unTopRating;
    }

    public void setUnTopRating(Short unTopRating) {
        this.unTopRating = unTopRating;
    }

    @Basic
    @Column(name = "pszFileName", nullable = true, length = -1)
    public String getPszFileName() {
        return pszFileName;
    }

    public void setPszFileName(String pszFileName) {
        this.pszFileName = pszFileName;
    }

    @Basic
    @Column(name = "unFilePath", nullable = true)
    public Short getUnFilePath() {
        return unFilePath;
    }

    public void setUnFilePath(Short unFilePath) {
        this.unFilePath = unFilePath;
    }

    @Basic
    @Column(name = "unDuration", nullable = true)
    public Short getUnDuration() {
        return unDuration;
    }

    public void setUnDuration(Short unDuration) {
        this.unDuration = unDuration;
    }

    @Basic
    @Column(name = "ullFileSize", nullable = true)
    public Short getUllFileSize() {
        return ullFileSize;
    }

    public void setUllFileSize(Short ullFileSize) {
        this.ullFileSize = ullFileSize;
    }

    @Basic
    @Column(name = "unMaxBitrate", nullable = true)
    public Short getUnMaxBitrate() {
        return unMaxBitrate;
    }

    public void setUnMaxBitrate(Short unMaxBitrate) {
        this.unMaxBitrate = unMaxBitrate;
    }

    @Basic
    @Column(name = "wEncrypt", nullable = true)
    public Short getwEncrypt() {
        return wEncrypt;
    }

    public void setwEncrypt(Short wEncrypt) {
        this.wEncrypt = wEncrypt;
    }

    @Basic
    @Column(name = "tVideo", nullable = true, length = -1)
    public String gettVideo() {
        return tVideo;
    }

    public void settVideo(String tVideo) {
        this.tVideo = tVideo;
    }

    @Basic
    @Column(name = "tAccompany", nullable = true, length = -1)
    public String gettAccompany() {
        return tAccompany;
    }

    public void settAccompany(String tAccompany) {
        this.tAccompany = tAccompany;
    }

    @Basic
    @Column(name = "tOriginal", nullable = true, length = -1)
    public String gettOriginal() {
        return tOriginal;
    }

    public void settOriginal(String tOriginal) {
        this.tOriginal = tOriginal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongInfo songInfo = (SongInfo) o;

        if (id != null ? !id.equals(songInfo.id) : songInfo.id != null) return false;
        if (ullSongCode != null ? !ullSongCode.equals(songInfo.ullSongCode) : songInfo.ullSongCode != null)
            return false;
        if (unSongCode != null ? !unSongCode.equals(songInfo.unSongCode) : songInfo.unSongCode != null) return false;
        if (pszName != null ? !pszName.equals(songInfo.pszName) : songInfo.pszName != null) return false;
        if (pszSpell != null ? !pszSpell.equals(songInfo.pszSpell) : songInfo.pszSpell != null) return false;
        if (wNameWords != null ? !wNameWords.equals(songInfo.wNameWords) : songInfo.wNameWords != null) return false;
        if (wLanguage != null ? !wLanguage.equals(songInfo.wLanguage) : songInfo.wLanguage != null) return false;
        if (wCategory != null ? !wCategory.equals(songInfo.wCategory) : songInfo.wCategory != null) return false;
        if (wSingers != null ? !wSingers.equals(songInfo.wSingers) : songInfo.wSingers != null) return false;
        if (wVersions != null ? !wVersions.equals(songInfo.wVersions) : songInfo.wVersions != null) return false;
        if (wStyles != null ? !wStyles.equals(songInfo.wStyles) : songInfo.wStyles != null) return false;
        if (arrSingers != null ? !arrSingers.equals(songInfo.arrSingers) : songInfo.arrSingers != null) return false;
        if (arrVersions != null ? !arrVersions.equals(songInfo.arrVersions) : songInfo.arrVersions != null)
            return false;
        if (arrStyles != null ? !arrStyles.equals(songInfo.arrStyles) : songInfo.arrStyles != null) return false;
        if (unDateTime != null ? !unDateTime.equals(songInfo.unDateTime) : songInfo.unDateTime != null) return false;
        if (unRanking != null ? !unRanking.equals(songInfo.unRanking) : songInfo.unRanking != null) return false;
        if (unTopRating != null ? !unTopRating.equals(songInfo.unTopRating) : songInfo.unTopRating != null)
            return false;
        if (pszFileName != null ? !pszFileName.equals(songInfo.pszFileName) : songInfo.pszFileName != null)
            return false;
        if (unFilePath != null ? !unFilePath.equals(songInfo.unFilePath) : songInfo.unFilePath != null) return false;
        if (unDuration != null ? !unDuration.equals(songInfo.unDuration) : songInfo.unDuration != null) return false;
        if (ullFileSize != null ? !ullFileSize.equals(songInfo.ullFileSize) : songInfo.ullFileSize != null)
            return false;
        if (unMaxBitrate != null ? !unMaxBitrate.equals(songInfo.unMaxBitrate) : songInfo.unMaxBitrate != null)
            return false;
        if (wEncrypt != null ? !wEncrypt.equals(songInfo.wEncrypt) : songInfo.wEncrypt != null) return false;
        if (tVideo != null ? !tVideo.equals(songInfo.tVideo) : songInfo.tVideo != null) return false;
        if (tAccompany != null ? !tAccompany.equals(songInfo.tAccompany) : songInfo.tAccompany != null) return false;
        if (tOriginal != null ? !tOriginal.equals(songInfo.tOriginal) : songInfo.tOriginal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ullSongCode != null ? ullSongCode.hashCode() : 0);
        result = 31 * result + (unSongCode != null ? unSongCode.hashCode() : 0);
        result = 31 * result + (pszName != null ? pszName.hashCode() : 0);
        result = 31 * result + (pszSpell != null ? pszSpell.hashCode() : 0);
        result = 31 * result + (wNameWords != null ? wNameWords.hashCode() : 0);
        result = 31 * result + (wLanguage != null ? wLanguage.hashCode() : 0);
        result = 31 * result + (wCategory != null ? wCategory.hashCode() : 0);
        result = 31 * result + (wSingers != null ? wSingers.hashCode() : 0);
        result = 31 * result + (wVersions != null ? wVersions.hashCode() : 0);
        result = 31 * result + (wStyles != null ? wStyles.hashCode() : 0);
        result = 31 * result + (arrSingers != null ? arrSingers.hashCode() : 0);
        result = 31 * result + (arrVersions != null ? arrVersions.hashCode() : 0);
        result = 31 * result + (arrStyles != null ? arrStyles.hashCode() : 0);
        result = 31 * result + (unDateTime != null ? unDateTime.hashCode() : 0);
        result = 31 * result + (unRanking != null ? unRanking.hashCode() : 0);
        result = 31 * result + (unTopRating != null ? unTopRating.hashCode() : 0);
        result = 31 * result + (pszFileName != null ? pszFileName.hashCode() : 0);
        result = 31 * result + (unFilePath != null ? unFilePath.hashCode() : 0);
        result = 31 * result + (unDuration != null ? unDuration.hashCode() : 0);
        result = 31 * result + (ullFileSize != null ? ullFileSize.hashCode() : 0);
        result = 31 * result + (unMaxBitrate != null ? unMaxBitrate.hashCode() : 0);
        result = 31 * result + (wEncrypt != null ? wEncrypt.hashCode() : 0);
        result = 31 * result + (tVideo != null ? tVideo.hashCode() : 0);
        result = 31 * result + (tAccompany != null ? tAccompany.hashCode() : 0);
        result = 31 * result + (tOriginal != null ? tOriginal.hashCode() : 0);
        return result;
    }
}
