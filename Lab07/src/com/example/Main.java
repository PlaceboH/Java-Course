package com.example;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.InstalmentDao;
import com.example.daoImpl.PaymentDao;
import com.example.daoImpl.PersonDao;
import com.example.models.Event;

import java.util.Scanner;


public class Main {


	private EventView eventView;
	private PersonView personView;
	private InstalmentView instalmentView;
	private PaymentView paymentView;


	public static void main(String[] args) {
		Main main = new Main();
		main.consoleView();
	}

	Main(){
		eventView = new EventView();
		personView = new PersonView();
		instalmentView = new InstalmentView();
		paymentView = new PaymentView();
	}

	private void consoleView(){
		boolean exit = false;
		do
		{
			System.out.println("1.Event Manager");
			System.out.println("2.Instalment Manager");
			System.out.println("3.Payment Manager ");
			System.out.println("4.Person Manager");
			System.out.println("5.exit");

			Scanner sd=new Scanner(System.in);
			System.out.println("enter your choice");
			int num=sd.nextInt();
			switch(num)
			{
				case 1:
					eventView.startView();
					System.out.println("\n");
					break;

				case 2:
					instalmentView.startView();
					System.out.println("\n");
					break;

				case 3:
					paymentView.startView();
					System.out.println("\n");
					break;

				case 4:
					personView.startView();
					personView.startView();
					System.out.println("\n");
					break;

				case 5:
					exit=true;
					break;
			}
		}while(!exit);

	}

}
