/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orderclient;

import java.io.Serializable;

/**
 *
 * @author youcee
 */
public class BookOrder implements Task,Serializable{
    
    private int quantity;
    private double unitPrice;
    private final double tax;
    private double totalBill;
    
    public BookOrder(){
        tax = 10;
    }
  
    /**
     *
     */
    @Override
    public void executeTask() {
        double pretax = getQuantity() * getUnitPrice();
        double theTax = pretax*(tax/100);
        double total = pretax + theTax;
        setTotalBill(total);
    }

    @Override
    public String getResult() {
        return toString();
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
        return "BookOrder{" + "quantity=" + quantity + ", unitPrice=" + unitPrice + ", tax=" + tax + ", totalBill=" + totalBill + '}';
    }
    
       
}
