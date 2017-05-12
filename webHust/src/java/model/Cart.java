/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author linhphan
 */
public class Cart implements Serializable {
    
    private HashMap<String, Item> cartItems;
    
    public Cart() {
        cartItems = new HashMap<String, Item>();
    }

    public HashMap<String, Item> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<String, Item> cartItems) {
        this.cartItems = cartItems;
    }
    
    
    // them vao gio hang
    public void addToCart(String key, Item item) {
       
       if(cartItems.containsKey(key)) {
           int quantityOld = item.getQuantity();
           item.setQuantity(quantityOld+1);
           cartItems.put(item.getProduct().getPID(), item) ;
       }
       else {
           cartItems.put(item.getProduct().getPID(), item) ;
       }
   }
   
    // xoa san pham khoi gio hang
    public void removeToCart(String PID) {
        
        if(cartItems.containsKey(PID)) {
            cartItems.remove(PID);
        }
    }
   
    // tinh tong san pham 
    public int countItem() {
        int count = 0;
        count = cartItems.size();
        return count;
    }
    
    // tinh tong tien
    public int totalMoney() {
        int money =0;
        for(Map.Entry<String, Item> list : cartItems.entrySet()) {
            money += list.getValue().getProduct().getPPirce() * list.getValue().getQuantity();
        }
        return money;
    }
    
    
}
