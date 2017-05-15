package com.efan.core.secondary;

import javax.persistence.*;

/**
 * Created by 45425 on 2017/5/15.
 */
@Entity
@Table(name="songInfo")
public class SongInfo {
    private Long id;
    private Long ullSongCode;
    private Long unSongCode;
    private String pszName;
    private String pszSpell;
    private Integer wNameWords;
    private Integer wLanguage;
    private Integer wCategory;
    private Integer wSingers;
    private Integer wVersions;
    private Integer wStyles;
    private String arrSingers;
    private String arrVersions;
    private String arrStyles;
    private Integer unDateTime;
    private Integer unRanking;
    private Integer unTopRating;
    private String pszFileName;
    private Integer unFilePath;
    private Integer unDuration;
    private Integer ullFileSize;
    private Integer unMaxBitrate;
    private Integer wEncrypt;
    private String tVideo;
    private String tAccompany;
    private String tOriginal;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ullSongCode", nullable = true)
    public Long getUllSongCode() {
        return ullSongCode;
    }

    public void setUllSongCode(Long ullSongCode) {
        this.ullSongCode = ullSongCode;
    }

    @Basic
    @Column(name = "unSongCode", nullable = true)
    public Long getUnSongCode() {
        return unSongCode;
    }

    public void setUnSongCode(Long unSongCode) {
        this.unSongCode = unSongCode;
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
    @Column(name = "wLanguage", nullable = true)
    public Integer getwLanguage() {
        return wLanguage;
    }

    public void setwLanguage(Integer wLanguage) {
        this.wLanguage = wLanguage;
    }

    @Basic
    @Column(name = "wCategory", nullable = true)
    public Integer getwCategory() {
        return wCategory;
    }

    public void setwCategory(Integer wCategory) {
        this.wCategory = wCategory;
    }

    @Basic
    @Column(name = "wSingers", nullable = true)
    public Integer getwSingers() {
        return wSingers;
    }

    public void setwSingers(Integer wSingers) {
        this.wSingers = wSingers;
    }

    @Basic
    @Column(name = "wVersions", nullable = true)
    public Integer getwVersions() {
        return wVersions;
    }

    public void setwVersions(Integer wVersions) {
        this.wVersions = wVersions;
    }

    @Basic
    @Column(name = "wStyles", nullable = true)
    public Integer getwStyles() {
        return wStyles;
    }

    public void setwStyles(Integer wStyles) {
        this.wStyles = wStyles;
    }

    @Basic
    @Column(name = "arrSingers", nullable = true, length = 200)
    public String getArrSingers() {
        return arrSingers;
    }

    public void setArrSingers(String arrSingers) {
        this.arrSingers = arrSingers;
    }

    @Basic
    @Column(name = "arrVersions", nullable = true, length = 200)
    public String getArrVersions() {
        return arrVersions;
    }

    public void setArrVersions(String arrVersions) {
        this.arrVersions = arrVersions;
    }

    @Basic
    @Column(name = "arrStyles", nullable = true, length = 200)
    public String getArrStyles() {
        return arrStyles;
    }

    public void setArrStyles(String arrStyles) {
        this.arrStyles = arrStyles;
    }

    @Basic
    @Column(name = "unDateTime", nullable = true)
    public Integer getUnDateTime() {
        return unDateTime;
    }

    public void setUnDateTime(Integer unDateTime) {
        this.unDateTime = unDateTime;
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
    @Column(name = "pszFileName", nullable = true, length = 200)
    public String getPszFileName() {
        return pszFileName;
    }

    public void setPszFileName(String pszFileName) {
        this.pszFileName = pszFileName;
    }

    @Basic
    @Column(name = "unFilePath", nullable = true)
    public Integer getUnFilePath() {
        return unFilePath;
    }

    public void setUnFilePath(Integer unFilePath) {
        this.unFilePath = unFilePath;
    }

    @Basic
    @Column(name = "unDuration", nullable = true)
    public Integer getUnDuration() {
        return unDuration;
    }

    public void setUnDuration(Integer unDuration) {
        this.unDuration = unDuration;
    }

    @Basic
    @Column(name = "ullFileSize", nullable = true)
    public Integer getUllFileSize() {
        return ullFileSize;
    }

    public void setUllFileSize(Integer ullFileSize) {
        this.ullFileSize = ullFileSize;
    }

    @Basic
    @Column(name = "unMaxBitrate", nullable = true)
    public Integer getUnMaxBitrate() {
        return unMaxBitrate;
    }

    public void setUnMaxBitrate(Integer unMaxBitrate) {
        this.unMaxBitrate = unMaxBitrate;
    }

    @Basic
    @Column(name = "wEncrypt", nullable = true)
    public Integer getwEncrypt() {
        return wEncrypt;
    }

    public void setwEncrypt(Integer wEncrypt) {
        this.wEncrypt = wEncrypt;
    }

    @Basic
    @Column(name = "tVideo", nullable = true, length = 200)
    public String gettVideo() {
        return tVideo;
    }

    public void settVideo(String tVideo) {
        this.tVideo = tVideo;
    }

    @Basic
    @Column(name = "tAccompany", nullable = true, length = 200)
    public String gettAccompany() {
        return tAccompany;
    }

    public void settAccompany(String tAccompany) {
        this.tAccompany = tAccompany;
    }

    @Basic
    @Column(name = "tOriginal", nullable = true, length = 200)
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

        if (id != songInfo.id) return false;
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
        int result = (int) (id ^ (id >>> 32));
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
