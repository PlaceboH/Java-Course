package com.example;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.InstalmentDao;
import com.example.models.Event;
import com.example.models.Instalment;

import java.util.List;
import java.util.Scanner;

public class InstalmentView {

    private final InstalmentDao instalmentDao;
    private final EventDao eventDao;

    InstalmentView(){
        instalmentDao = new InstalmentDao();
        eventDao = new EventDao();
    }

    public InstalmentDao InstalmentDao() {
        return instalmentDao;
    }

    public void startView(){
        Instalment instalment = new Instalment();
        Event event;
        boolean exit = false;
        do
        {
            System.out.println("1.Add Instalment");
            System.out.println("2.Change Instalment");
            System.out.println("3.Get Instalment by id");
            System.out.println("4.Get all instalments");
            System.out.println("5.Delete instalment by id");
            System.out.println("6.exit");

            Scanner sd=new Scanner(System.in);
            System.out.println("enter your choice");
            int num=sd.nextInt();
            switch(num)
            {
                case 1:
                    System.out.println("Podaj dane Instalmentu(instalmentDate[dd/mm/yyyy], String instalmentNumber, payment, event");
                    sd.nextLine();
                    instalment.setPaymentDate(Converter.createDate());
                    instalment.setInstalmentNumber(sd.nextLine());
                    instalment.setPayment(sd.nextFloat());
                    event = eventDao.get(sd.nextInt());
                    instalment.setEvent(event);
                    this.instalmentDao.add(instalment);
                    System.out.println("\n");
                    break;
                case 2:
                    Instalment updateInstalment = new Instalment();
                    System.out.println("Podaj dane Instalmentu(instalmentDate[dd/mm/yyyy], String instalmentNumber, payment, event");
                    updateInstalment.setInstalmentId(sd.nextInt());
                    updateInstalment.setPaymentDate(Converter.createDate());
                    updateInstalment.setPayment(sd.nextFloat());
                    updateInstalment.setInstalmentNumber(sd.nextLine());
                    event = eventDao.get(sd.nextInt());
                    instalment.setEvent(event);
                    this.instalmentDao.add(updateInstalment);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Podaj id Instalmentu");
                    System.out.println(this.instalmentDao.get(sd.nextInt()).toString());
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("Wydarzenia: ");
                    List<Instalment> listInstalment = this.instalmentDao.getAll();
                    listInstalment.forEach(e -> System.out.println(e.toString()));
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Podaj id Instalmentu");
                    this.instalmentDao.delete(sd.nextInt());
                    System.out.println("\n");
                    break;

                case 6:
                    exit=true;
                    break;
            }
        }while(!exit);

    }
}
