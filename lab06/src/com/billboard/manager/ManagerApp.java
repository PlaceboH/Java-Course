package com.billboard.manager;
import bilboards.IManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.awt.*;
import java.rmi.RemoteException;


public class ManagerApp {
    public static void main(String[] args) throws NamingException {
        if(args.length<2){
            System.out.println("<port><service>");
        }else{


            int port = Integer.parseInt(args[0]);
            String service = args[1];

            Context context = new InitialContext();

            EventQueue.invokeLater(() -> {
                ManagerModel m = new ManagerModel();
                ManagerView v = new ManagerView("Manager");
                ManagerController mc = null;
                try {
                    mc = new ManagerController(m,v);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                mc.initController();
                try {
                    context.rebind("rmi://localhost:1099/ibillboard",mc);
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
