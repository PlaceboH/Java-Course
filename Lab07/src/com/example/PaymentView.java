package com.example;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.PaymentDao;
import com.example.daoImpl.PersonDao;
import com.example.models.Event;
import com.example.models.Payment;
import com.example.models.Person;

import java.util.List;
import java.util.Scanner;

public class PaymentView {

    private final PaymentDao paymentDao;
    private final EventDao eventDao;
    private final PersonDao personDao;

    PaymentView(){
        paymentDao = new PaymentDao();
        eventDao = new EventDao();
        personDao = new PersonDao();
    }

    public PaymentDao PaymentDao() {
        return paymentDao;
    }

    public void startView(){
        Payment payment = new Payment();
        Event event;
        Person person;
        boolean exit = false;
        do
        {
            System.out.println("1.Add Payment");
            System.out.println("2.Change Payment");
            System.out.println("3.Get Payment by id");
            System.out.println("4.Get all payments");
            System.out.println("5.Delete payment by id");
            System.out.println("6.exit");

            Scanner sd=new Scanner(System.in);
            System.out.println("enter your choice");
            int num=sd.nextInt();
            switch(num)
            {
                case 1:
                    System.out.println("Podaj dane Paymentu(paymentDate[dd/mm/yyyy], paymentAmount, instalmentNumber, event, person");
                    sd.nextLine();
                    payment.setPaymentDate( Converter.createDate());
                    payment.setPaymentAmount(sd.nextFloat());
                    payment.setInstalmentNumber(sd.nextLine());
                    event = eventDao.get(sd.nextInt());
                    payment.setEvent(event);
                    person = personDao.get(sd.nextInt());
                    payment.setPerson(person);
                    this.paymentDao.add(payment);
                    System.out.println("\n");
                    break;
                case 2:
                    Payment updatePayment = new Payment();
                    System.out.println("Podaj dane Paymentu(paymentDate[dd/mm/yyyy], paymentAmount, instalmentNumber, event, person");
                    updatePayment.setPaymentId(sd.nextInt());
                    updatePayment.setPaymentDate( Converter.createDate());
                    updatePayment.setPaymentAmount(sd.nextFloat());
                    updatePayment.setInstalmentNumber(sd.nextLine());
                    event = eventDao.get(sd.nextInt());
                    payment.setEvent(event);
                    person = personDao.get(sd.nextInt());
                    payment.setPerson(person);
                    this.paymentDao.add(updatePayment);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Podaj id Paymentu");
                    System.out.println(this.paymentDao.get(sd.nextInt()).toString());
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("Wydarzenia: ");
                    List<Payment> listPayment = this.paymentDao.getAll();
                    listPayment.forEach(e -> System.out.println(e.toString()));
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Podaj id Paymentu");
                    this.paymentDao.delete(sd.nextInt());
                    System.out.println("\n");
                    break;

                case 6:
                    exit=true;
                    break;
            }
        }while(!exit);

    }
}
