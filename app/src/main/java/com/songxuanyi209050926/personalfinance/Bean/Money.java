package com.songxuanyi209050926.personalfinance.Bean;
/*
收入和支出（包括但不限于以下字段：项目名称，金额，录入时间，类别(信用卡/现金)
 */
public class Money {

    private int expenditures;
    private int revenue;
    private String type;
    private String date;
    private String proName;
    private int total;
    private int balance;
    public int getExpenditures() {
        return expenditures;
    }

    public void setExpenditures(int expenditures) {
        this.expenditures = expenditures;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
