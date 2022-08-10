package com.billboard.client;

import bilboards.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientModel implements Serializable {
    private HashMap<Integer ,Order> listOfOrders;
    public ClientModel(){
        listOfOrders=new HashMap<>();
    }
    public boolean addOrder(Integer orderId, Order order){
         listOfOrders.put(orderId, order);
         return true;
    }
    public boolean removeOrder(Integer orderId, Order order){
        return listOfOrders.remove(orderId, order);
    }
    public HashMap<Integer ,Order> getListOfOrders() {
        return listOfOrders;
    }
}
