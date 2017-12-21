package com.hitechpestcontrol.bills;

/**
 * Created by Spongebob on 12/21/2017.
 */

public class AccountsModel {

    private int bill, travelExpense, chemicalExpense, profit, total;
    private String custName;

    AccountsModel(int bill, int travelExpense, int chemicalExpense, int profit, int total, String custName){
        this.setBill(bill);
        this.setTravelExpense(travelExpense);
        this.setChemicalExpense(chemicalExpense);
        this.setProfit(profit);
        this.setCustName(custName);
        this.setTotal(total);
    }

    public void setBill(int bill){
        this.bill = bill;
    }

    public void setTravelExpense(int travelExpense){
        this.travelExpense = travelExpense;
    }

    public void setChemicalExpense(int chemicalExpense){
        this.chemicalExpense = chemicalExpense;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public void setProfit(int profit){
        this.profit = profit;
    }

    public void setCustName(String custName){
        this.custName = custName;
    }

    public int getBill(){
        return bill;
    }

    public int getTravelExpense(){
        return travelExpense;
    }

    public int getChemicalExpense(){
        return chemicalExpense;
    }

    public int getTotal(){
        return total;
    }

    public int getProfit(){
        return profit;
    }

    public String getCustName(){
        return custName;
    }

}
