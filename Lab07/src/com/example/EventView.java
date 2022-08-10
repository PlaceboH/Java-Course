package com.example;

import com.example.daoImpl.EventDao;
import com.example.models.Event;

import java.util.List;
import java.util.Scanner;

public class EventView {

    private EventDao eventDao;


    EventView(){
        eventDao = new EventDao();
    }

    public EventDao getEventDao() {
        return eventDao;
    }

    public void startView(){
        Event event = new Event();
        boolean exit = false;
        do
        {
            System.out.println("1.Add Event");
            System.out.println("2.Change Event");
            System.out.println("3.Get Event by id");
            System.out.println("4.Get all events");
            System.out.println("5.Delete event by id");
            System.out.println("6.exit");

            Scanner sd=new Scanner(System.in);
            System.out.println("enter your choice");
            int num=sd.nextInt();
            switch(num)
            {
                case 1:
                    System.out.println("Podaj dane Eventu(location, name, date[dd. MMM. yyyy])");
                    sd.nextLine();
                    event.setLocation(sd.nextLine());
                    event.setName(sd.nextLine());
                    event.setLocalDate( Converter.createDate());

                    this.eventDao.add(event);
                    System.out.println("\n");
                    break;

                case 2:
                    Event updateEvent = new Event();
                    System.out.println("Podaj dane Eventu(id, location, name, date[dd. MMM. yyyy])");
                    event.setEventId(sd.nextInt());
                    event.setLocation(sd.nextLine());
                    event.setName(sd.nextLine());
                    event.setLocalDate( Converter.createDate());
                    this.eventDao.add(updateEvent);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Podaj id Eventu");
                    System.out.println(this.eventDao.get(sd.nextInt()).toString());
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("Wydarzenia: ");
                    List<Event> listEvent = this.eventDao.getAll();
                    listEvent.forEach(e -> System.out.println(e.toString()));
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Podaj id Eventu");
                    this.eventDao.delete(sd.nextInt());
                    System.out.println("\n");
                    break;

                case 6:
                    exit=true;
                    break;
            }
        }while(!exit);

    }

}
