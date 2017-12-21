package com.hitechpestcontrol.bills;


public class Model {

    private String name, treat, date, contact;
    private int amount, bill;

    Model(int bill, String date, String name, String treat, String contact, int amount)
    {
        this.setBill(bill);
        this.setDate(date);
        this.setName(name);
        this.setTreat(treat);
        this.setContact(contact);
        this.setAmount(amount);
    }

    public int getBill() {
        return bill;
    }

    public String getContact() {
        return contact;
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

    public void setBill(int bill) {
        this.bill = bill;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}