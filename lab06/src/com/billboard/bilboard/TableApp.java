package com.billboard.bilboard;
import bilboards.IManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class TableApp {
    public static void main(String[] args) throws NamingException {
        if (args.length < 2) {
            System.out.println("<port><service>");
        } else {
            int port = Integer.parseInt(args[0]);
            String service = args[1];


            Context context = new InitialContext();
            Enumeration<NameClassPair> en = context.list("rmi://localhost/");
            while (en.hasMoreElements())
                System.out.println(en.nextElement().getName());

            IManager iManager = (IManager) context.lookup("rmi://localhost/ibillboard");

            EventQueue.invokeLater(() -> {
                TableModel m = new TableModel();
                m.setTotalCapacity(5);
                TableView v = new TableView("3 billboards");
                TableController c = null;
                try {
                    c = new TableController(m, v,iManager);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    c.initController();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
