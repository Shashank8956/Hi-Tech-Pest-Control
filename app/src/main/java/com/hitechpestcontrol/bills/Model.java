package com.hitechpestcontrol.bills;


public class Model {

    private String name, treat, date;
    private int amount;

    Model(String name, String treat, String date, int amount)
    {
        this.setName(name, treat, date, amount);
    }

    public String getName() {
        return name;
    }

    public String getTreat() {
        return treat;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public void setName(String name, String treat, String date, int amount) {
        this.name = name;
        this.treat = treat;
        this.date = date;
        this.amount = amount;
    }


}