package com.billboard.manager;

import bilboards.IBillboard;
import bilboards.Order;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerModel implements Serializable {

    private final long serialVersionUID = 1L;
    private int billboardID=1;
    private Map<Integer,IBillboard> listOfBillboards;
    private HashMap<Integer, Order> listOfOrders;
    private Integer orderId = 1;


    public Integer getOrderId(){
        return orderId;
    }

    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    public ManagerModel() {
        listOfBillboards=new HashMap<>();
        listOfOrders=new HashMap<>();
    }
    public int addBillboard(IBillboard billboard){
        listOfBillboards.put(billboardID,billboard);
        return billboardID++;
    }
    public boolean removeBillboard(int billboard){
        if(listOfBillboards.containsKey(billboard)){
            listOfBillboards.remove(billboard);
            return true;
        }
        return false;
    }


    public void addOrder(Order order){
         listOfOrders.put(orderId, order);
    }

    public HashMap<Integer, Order> getListOfOrders(){
        return listOfOrders;
    }

    public Map<Integer, IBillboard> getListOfBillboards() {
        return listOfBillboards;
    }

    public List<Integer> getRegisteredTablesID() {
        return listOfBillboards.keySet().stream().sorted().collect(Collectors.toList());
    }
}
