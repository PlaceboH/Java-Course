package com.billboard.client;

import bilboards.IClient;
import bilboards.IManager;
import bilboards.Order;

import javax.swing.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ClientController extends UnicastRemoteObject implements IClient,Serializable {
    private static final long serialVersionUID = 1L;
    private  ClientModel clientModel;
    private  transient ClientView clientView;
    private IManager iManager;
    private Integer orderId;
    public ClientController(ClientModel clientModel, ClientView clientView, IManager iManager) throws RemoteException {
        super();
        this.clientModel = clientModel;
        this.clientView = clientView;
        this.iManager = iManager;
        this.orderId = 0;
        initView();
    }

    public void initView() {

    }

    public void initController() {
        clientView.getSendOrderButton().addActionListener(e -> sendOrderBasedOnUIData());
        clientView.getRemoveOrderButton().addActionListener(e -> removeOrderFromUiData());
        clientView.getWithdrawOrderButton().addActionListener(e -> {
            try {
                withdrawOrderBasedOnUIData();
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
        });
    }

    private void withdrawOrderBasedOnUIData() throws RemoteException {
        Object selectedItem1 = clientView.getOrdersIdComboBox().getSelectedItem();
        if (selectedItem1 == null) return;
        String selectedItem = selectedItem1.toString();
        if (selectedItem != "") {
            for (Integer orderId : getAcceptedOrders().keySet()) {
                if (orderId == Integer.parseInt(selectedItem)) {
                    boolean withdrawn = iManager.withdrawOrder(orderId);
                    if (withdrawn) {
                        removeOrder(orderId , getAcceptedOrders().get(orderId));
                    }
                    updateMyAdvertisementsDetailsTextField();
                    return;
                }
            }
        }
    }
    private void updateMyAdvertisementsDetailsTextField(){
        clientView.getMyAdvertisementsDetailsTextField().setText(getOrdersInformation());
        HashMap< Integer, Order> acceptedOrders = getAcceptedOrders();
        JComboBox<String> ordersIdComboBox = clientView.getOrdersIdComboBox();
        ordersIdComboBox.removeAllItems();
        for (int i = 0; i < getAcceptedOrders().values().size(); i++){
            ordersIdComboBox.addItem(Integer.toString(i));
        }
    }

    private void removeOrderFromUiData() {
        Object selectedItem1 = clientView.getOrdersIdComboBox().getSelectedItem();
        if (selectedItem1 == null) return;
        String selectedItem = selectedItem1.toString();
        if (selectedItem != "") {
            for (Integer orderId : getAcceptedOrders().keySet()) {
                if (orderId == Integer.parseInt(selectedItem)) {
                    withdrawOrder(orderId);
                    updateMyAdvertisementsDetailsTextField();
                    return;
                }
            }
        }
    }

    private void sendOrderBasedOnUIData() {
        String addText = clientView.getNewAdvertisementTextField().getText();
        String textDuration = clientView.getDisplayTimeTextField().getText();
        if(addText!=null&&textDuration!=null) {
            if (!textDuration.equals("")  && !addText.equals("")) {
                Duration duration = Duration.ofSeconds(Integer.parseInt(textDuration));
                Order order = new Order(addText, duration, this);
                sendOrder(orderId,  order);
                repaintDataOnUserInterface();
            }
        }

    }

    private void repaintDataOnUserInterface() {
        clientView.getMyAdvertisementsDetailsTextField().setText(getOrdersInformation());
        clientView.getNewAdvertisementTextField().setText("");
        clientView.getDisplayTimeTextField().setText("");
    }

    public boolean withdrawOrder(int orderId) {
        return removeOrder(orderId, getAcceptedOrders().get(orderId));
    }

    public void sendOrder( Integer orderId, Order order) {
        clientModel.addOrder( orderId, order);
        try {
            iManager.placeOrder(order);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOrderId(int orderId) throws RemoteException {
        this.orderId = orderId;
        updateMyAdvertisementsDetailsTextField();
    }

    public boolean addOrder(Order order) {
        return clientModel.addOrder( orderId, order);
    }

    public boolean removeOrder(Integer orderId, Order order) {
        return clientModel.removeOrder(orderId, order);
    }

    public String getOrdersInformation() {
        StringBuilder builder = new StringBuilder();
        for (Order order : getAcceptedOrders().values()) {
            builder.append(order);
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }

    public HashMap<Integer, Order> getAcceptedOrders() {
        return clientModel.getListOfOrders();
    }

}
