/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servermovie;

import java.io.Serializable;

/**
 *
 * @author youcee
 */
public class MovieOrder implements Task,Serializable{
    private int quantity;
    private double unitPrice;
    private final double tax;
    private double totalBill;
    
    public MovieOrder(){
        tax = 30;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    @Override
    public String toString() {
        return "MovieOrder{" + "quantity=" + quantity + ", unitPrice=" + unitPrice + ", tax=" + tax + ", totalBill=" + totalBill + '}';
    }

    /**
     *
     */
    @Override
    public void executeTask() {
        double pretax = getQuantity() * getUnitPrice();
        double actualTax = pretax*(tax/100);
        double totalp = actualTax + pretax;
        setTotalBill(totalp);
    }

    @Override
    public String getResult() {
        return toString();
    }
       
}
