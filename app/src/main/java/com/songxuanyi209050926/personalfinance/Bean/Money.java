package com.songxuanyi209050926.personalfinance.Bean;

/*
收入和支出（包括但不限于以下字段：项目名称，金额，录入时间，类别(信用卡/现金)
 */
public class Money {
    private int mid;
    private int oof;
    private String type;
    private String date;
    private String remake;
    private String proName;
    private String username;

    public Money() {

    }

    public Money(String username, int oof, String proName, String type, String remake, String date) {
        this.username = username;
        this.proName = proName;
        this.type = type;
        this.remake = remake;
        this.date = date;
        this.oof = oof;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getOof() {
        return oof;
    }

    public void setOof(int oof) {
        this.oof = oof;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
