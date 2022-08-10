package com.example;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.InstalmentDao;
import com.example.daoImpl.PaymentDao;
import com.example.daoImpl.PersonDao;
import com.example.models.Event;
import com.example.models.Instalment;
import com.example.models.Payment;
import com.example.models.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Converter {

    private PersonDao personDao;
    private EventDao eventDao;
    private InstalmentDao instalmentDao;
    private PaymentDao paymentDao;

    Converter(){
        personDao = new PersonDao();
        eventDao = new EventDao();
        paymentDao = new PaymentDao();
        instalmentDao = new InstalmentDao();
    }

    public static LocalDate createDate() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a date [d/MM/yyyy]: ");
        String str = scan.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(str, formatter);
    }


    public void scvToSQLPerson(String csvFilePath) throws IOException {
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
        String lineText;
        lineReader.readLine(); // skip header line
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");

            if(data.length == 2) {
                String first_name = data[0];
                String second_name = data[1];
                Person person = new Person(first_name, second_name);
                personDao.add(person);
            }
            else {
                System.out.println("nie poprawne dane w pliku");
            }
        }

        lineReader.close();
    }
    //String location, String name, LocalDate date
    public void scvToSQLEvent(String csvFilePath) throws IOException {
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
        String lineText;
        lineReader.readLine(); // skip header line
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            if(data.length == 3) {
                String location = data[0];
                String name = data[1];
                LocalDate date = LocalDate.parse(data[2]);
                Event event = new Event(location, name, date);
                eventDao.add(event);
            }
            else {
                System.out.println("nie poprawne dane w pliku");
            }
        }
        lineReader.close();
    }
    //String instalmentNumber, float payment, LocalDate date, Event event
    public void scvToSQLInstalment(String csvFilePath) throws IOException {
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
        String lineText;
        lineReader.readLine(); // skip header line
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            if(data.length == 4) {
                String instalmentNumber = data[0];
                float payment = Float.parseFloat(data[1]);
                LocalDate date = LocalDate.parse(data[2]);
                Event event = eventDao.get(Integer.parseInt(data[3]));
                Instalment inst = new Instalment(instalmentNumber, payment, date, event);
                instalmentDao.add(inst);
            }
            else {
                System.out.println("nie poprawne dane w pliku");
            }
        }
        lineReader.close();
    }

    //LocalDate paymentDate, float paymentAmount, String instalmentNumber, Event event, Person person)
    public void scvToSQLPayment(String csvFilePath) throws IOException {
        BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
        String lineText;
        lineReader.readLine(); // skip header line
        while ((lineText = lineReader.readLine()) != null) {
            String[] data = lineText.split(",");
            if(data.length == 5) {
                LocalDate date = LocalDate.parse(data[0]);
                float paymentAmount = Float.parseFloat(data[1]);
                String instalmentNumber = data[3];
                Event event = eventDao.get(Integer.parseInt(data[4]));
                Person person = personDao.get(Integer.parseInt(data[5]));

                Payment payment = new Payment(date,paymentAmount, instalmentNumber, event, person);
                paymentDao.add(payment);
            }
            else {
                System.out.println("nie poprawne dane w pliku");
            }
        }
        lineReader.close();
    }


}
