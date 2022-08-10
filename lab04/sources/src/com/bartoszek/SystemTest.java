package com.bartoszek;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;

import com.bartoszek.gui.MyFrame;

public class SystemTest {

public static void main(String[] args) {


		EventQueue.invokeLater(()->{
			try {
				ClassHandler.ClassHandlerInitialzie("d");
			} catch (IOException e) {
				e.printStackTrace();
			}

			MyFrame myFrame;

			try {
				myFrame = new MyFrame();
				myFrame.setTitle("Lab04 - Marcin Bartoszek");
				myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				myFrame.setVisible(true);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}	
		
			
			
		});

	}
}
