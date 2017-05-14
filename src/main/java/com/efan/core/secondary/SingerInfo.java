package com.efan.core.secondary;

/**
 * Created by 45425 on 2017/5/14.
 */
public class SingerInfo {
    public Integer getUnSingerCode() {
        return unSingerCode;
    }

    public void setUnSingerCode(Integer unSingerCode) {
        this.unSingerCode = unSingerCode;
    }

    public String getPszName() {
        return pszName;
    }

    public void setPszName(String pszName) {
        this.pszName = pszName;
    }

    public String getPszSpell() {
        return pszSpell;
    }

    public void setPszSpell(String pszSpell) {
        this.pszSpell = pszSpell;
    }

    public Integer getwNameWords() {
        return wNameWords;
    }

    public void setwNameWords(Integer wNameWords) {
        this.wNameWords = wNameWords;
    }

    public Integer getwSingerType() {
        return wSingerType;
    }

    public void setwSingerType(Integer wSingerType) {
        this.wSingerType = wSingerType;
    }

    public Integer getwSingerArea() {
        return wSingerArea;
    }

    public void setwSingerArea(Integer wSingerArea) {
        this.wSingerArea = wSingerArea;
    }

    public Integer getwNationality() {
        return wNationality;
    }

    public void setwNationality(Integer wNationality) {
        this.wNationality = wNationality;
    }

    public Integer getUnRanking() {
        return unRanking;
    }

    public void setUnRanking(Integer unRanking) {
        this.unRanking = unRanking;
    }

    public Integer getUnTopRating() {
        return unTopRating;
    }

    public void setUnTopRating(Integer unTopRating) {
        this.unTopRating = unTopRating;
    }

    public Integer getUnSongs() {
        return unSongs;
    }

    public void setUnSongs(Integer unSongs) {
        this.unSongs = unSongs;
    }

    public Integer getDwImageExist() {
        return dwImageExist;
    }

    public void setDwImageExist(Integer dwImageExist) {
        this.dwImageExist = dwImageExist;
    }

    public String getPszProfile() {
        return pszProfile;
    }

    public void setPszProfile(String pszProfile) {
        this.pszProfile = pszProfile;
    }

    //歌手key
    private  Integer     unSingerCode;
    //歌手名称
    private  String pszName;
    //歌手拼音
    private  String pszSpell;
  private Integer  wNameWords;
  private Integer  wSingerType;
  private Integer  wSingerArea;
  private Integer  wNationality;
  private Integer  unRanking;
  private Integer  unTopRating;
  private Integer  unSongs;
  private Integer  dwImageExist;
  private String  pszProfile;

}
