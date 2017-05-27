package com.efan.utils;

/**
 * Created by xuenianxiang on 2017/5/19.
 */
public class TokenSingleton {

    private static TokenSingleton instance;
    public static synchronized TokenSingleton getInstance() {
        if (instance == null) {
            instance = new TokenSingleton();
        }
        return instance;
    }

    private String wxToken;
private  String  access_Token;
private  Long access_Time;

    public String getAccess_Token() {
        return access_Token;
    }

    public void setAccess_Token(String access_Token) {
        this.access_Token = access_Token;
    }

    public Long getAccess_Time() {
        return access_Time;
    }

    public void setAccess_Time(Long access_Time) {
        this.access_Time = access_Time;
    }

    private long tokenTime;
    private long ticketTime;
    private  String ticket;
    public long getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(long tokenTime) {
        this.tokenTime = tokenTime;
    }

    public long getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(long ticketTime) {
        this.ticketTime = ticketTime;
    }



    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }


    private TokenSingleton (){}


    public String getWxToken() {
        return wxToken;
    }

    public void setWxToken(String wxToken) {
        this.wxToken = wxToken;
    }


}

