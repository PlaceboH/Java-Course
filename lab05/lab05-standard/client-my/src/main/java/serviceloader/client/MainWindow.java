package serviceloader.client;

import com.opencsv.exceptions.CsvException;
import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.ServiceLoader;
import java.util.Vector;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel defaultTableModel;
	private Object[][] data;
	private JList<String> servicesList;
	private DefaultListModel<String> defaultListModel;
	private HashMap<String, AnalysisService> servicesHashMap;
	private JButton processData;
	private AnalysisService analysisService;
	private DataSet dataSet;
	private JButton editButton;
	private JButton saveButton;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Analysis service loader");
		setBounds(100, 100, 971, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		defaultTableModel = new DefaultTableModel();
		table = new JTable(defaultTableModel);
		processData = new JButton("Start process");
		editButton = new JButton("Edit");
		saveButton = new JButton("Ok");
		JButton loadServicesButton = new JButton("Get services");
		defaultListModel = new DefaultListModel<>();
		servicesList = new JList<String>(defaultListModel);
		JButton generateDataButton = new JButton("Set table from scv");
		JButton showProcessedData = new JButton("Show Table");
		
		scrollPane.setBounds(25, 30, 486, 386);
		showProcessedData.setBounds(555, 211, 232, 23);
		processData.setBounds(555, 177, 154, 23);
		editButton.setBounds(328, 427, 89, 23);
		loadServicesButton.setBounds(781, 129, 139, 23);
		generateDataButton.setBounds(555, 20, 200, 23);
		saveButton.setBounds(427, 427, 89, 23);
		servicesList.setBounds(555, 60, 364, 68);
		
		processData.setEnabled(false);
		table.setEnabled(false);
		saveButton.setEnabled(false);
		
		scrollPane.setViewportView(table);
		
		processData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (servicesList.getSelectedIndex() >= 0) {
					analysisService = servicesHashMap.get(servicesList.getSelectedValue());
					try {
						String[] options = { "analysing" };
						analysisService.setOptions(options);
						analysisService.submit(dataSet);
					} catch (AnalysisException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Choose service");
				}

			}
		});

		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setEnabled(true);
				saveButton.setEnabled(true);
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				table.setRowSelectionAllowed(false);
				table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
				table.editCellAt(-1, -1);
				
				data = new String[defaultTableModel.getRowCount()][defaultTableModel.getColumnCount()];
				for (int i = 0; i < defaultTableModel.getRowCount(); i++)
					for (int j = 0; j < defaultTableModel.getColumnCount(); j++)
						data[i][j] = (String) defaultTableModel.getValueAt(i, j);

				table.setEnabled(false);

				dataSet = new DataSet();
				dataSet.setData((String[][]) data);
				
				saveButton.setEnabled(false);
			}
		});

		loadServicesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servicesHashMap = new HashMap<>();
				defaultListModel.removeAllElements();

				ServiceLoader<AnalysisService> loader = ServiceLoader.load(AnalysisService.class);
				System.out.println("Services loaded");

				for (AnalysisService service : loader) {
					servicesHashMap.put(service.getName(), service);
					defaultListModel.addElement(service.getName());
				}
				processData.setEnabled(true);
			}
		});

		showProcessedData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				defaultTableModel.getDataVector().removeAllElements();

				try {
					DataSet data = analysisService.retrieve(false);
					String[][] dataToShow = data.getData();
					for (String[] row : dataToShow) {
						Vector<String> vector = new Vector<>();
						for (String s : row)
							vector.add(s);

						defaultTableModel.addRow(vector);
					}

				} catch (AnalysisException e1) {
					JOptionPane.showMessageDialog(null, "Nie przeszkadzaj, przetwarzanie jeszcze trwa!!!", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		generateDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				defaultTableModel.getDataVector().removeAllElements();
				defaultTableModel.setColumnCount(0);

				dataSet = new DataSet();
				try {
					dataSet = CsvToDataSetConverter.readDataFromTheFile("/home/barankevycha/Desktop/Pwr_sem6/Java-techniki-zaawansowane-master/Lab05/serviceloader-modular/client/test.csv");
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (CsvException ex) {
					ex.printStackTrace();
				}


				for (String s : dataSet.getHeader()) {
					defaultTableModel.addColumn(s);
				}

				for (String[] row : dataSet.getData()) {
					Vector<String> vector = new Vector<>();
					for (String s : row)
						vector.add(s);

					defaultTableModel.addRow(vector);
				}
			}
		});
		
		contentPane.add(scrollPane);
		contentPane.add(loadServicesButton);
		contentPane.add(processData);
		contentPane.add(showProcessedData);
		contentPane.add(generateDataButton);
		contentPane.add(editButton);
		contentPane.add(servicesList);
		contentPane.add(saveButton);
	}

}
