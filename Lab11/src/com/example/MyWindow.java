package com.example;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class MyWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField vectorATextField;
	private JTextField vectorBTextField;
	private JTextField resultTextField;

	public static void main(String[] args) {

		System.load("/home/barankevycha/Desktop/Pwr_sem6/Java/java_hujava/Java-techniki-zaawansowane-master2/Lab11/src/c/libnative.so");

		MyWindow frame = new MyWindow();
		frame.setVisible(true);

	}

	public MyWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tablica A: ");
		lblNewLabel.setBounds(10, 11, 67, 14);
		contentPane.add(lblNewLabel);

		vectorATextField = new JTextField();
		vectorATextField.setBounds(79, 8, 200, 20);
		contentPane.add(vectorATextField);
		vectorATextField.setColumns(10);


		JSeparator separator = new JSeparator();
		separator.setBounds(0, 67, 302, 2);
		contentPane.add(separator);

		JButton sort01Btn = new JButton("sort01(Double[] a, boolean order)");
		sort01Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					JniSort jniSort = new JniSort();

					boolean order = true;
					Double[] result = jniSort.sort01(getVectorA(), order);
					String stringArray = Arrays.toString(result).replaceAll("\\s+", "");
					if (result != null)
						resultTextField.setText(stringArray);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wpisz dane jeszcze raz", "Błąd", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		sort01Btn.setBounds(35, 80, 233, 23);
		contentPane.add(sort01Btn);

		JButton sort02Btn = new JButton("sort02(Double[] a)");
		sort02Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					JniSort jniSort = new JniSort();
					jniSort.order = false;
					Double[] result = jniSort.sort02(getVectorA());
					String stringArray = Arrays.toString(result).replaceAll("\\s+", "");
					if (result != null)
						resultTextField.setText(stringArray);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Wpisz dane jeszcze raz", "Błąd", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		sort02Btn.setBounds(35, 119, 233, 23);
		contentPane.add(sort02Btn);


		JLabel lblNewLabel_1 = new JLabel("Wynik: ");
		lblNewLabel_1.setBounds(10, 214, 100, 14);
		contentPane.add(lblNewLabel_1);

		resultTextField = new JTextField();
		resultTextField.setEditable(false);
		resultTextField.setBounds(64, 211, 200, 20);
		contentPane.add(resultTextField);
		resultTextField.setColumns(10);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(0, 154, 302, 2);
		contentPane.add(separator_1_1);
	}

	public Double[] getVectorA() throws NumberFormatException {

		String[] v = vectorATextField.getText().replaceAll(" ", "").split(",");

		ArrayList<Double> vectorArrayList = new ArrayList<>();

		for (String s : v) {
			vectorArrayList.add(Double.parseDouble(s));
			System.out.println(vectorArrayList);
		}
		return (Double[]) vectorArrayList.toArray(new Double[vectorArrayList.size()]);
	}

}
