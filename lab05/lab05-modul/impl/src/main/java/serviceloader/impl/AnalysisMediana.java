package serviceloader.impl;

import java.util.Arrays;
import java.util.Comparator;

import ex.api.AnalysisService;
import ex.api.AnalysisException;
import ex.api.DataSet;

public class AnalysisMediana implements AnalysisService, Runnable {

	private boolean isAnalysing;
	private DataSet dataSet, outputDataSet = null;
	private Thread thread = new Thread(this);
	
	@Override
	public void setOptions(String[] options) throws AnalysisException {
		if(options[0].equals("") || options[0].equals(null)) {
			return;
		}else if(options[0].equals("analysing")){
			isAnalysing = true;
		}else {
			throw new AnalysisException("Nie podano poprawnej opcji");
		}
	}

	@Override
	public String getName() {
		return "Znajdowanie mediany";
	}

	@Override
	public void submit(DataSet ds) throws AnalysisException {
		if(thread.isAlive()) {
			throw new AnalysisException("Trwa przetwarzanie innych danych");
		}
		thread= new Thread(this);	
		dataSet = ds;
		outputDataSet = null;
		thread.start();
	}

	@Override
	public DataSet retrieve(boolean clear) throws AnalysisException {
		if(outputDataSet == null)
			throw new AnalysisException("Nie przeszkadzaj, przetwarzanie jeszcze trwa!!!");
		return outputDataSet;
	}

	@Override
	public void run() {
		String[][] data = dataSet.getData();

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if(isAnalysing) {
			String output[][] = new String[data.length + 1][data[0].length];
//	---------------------------------------------- WARNING BAD CODE SECTION -------------------------------------------------------------

			for (int i = 0; i < data[0].length; i++) {
				for (int j = 0; j < data.length; j++) {
					for (int k = 0; k < data.length - 1; k++) {
						if (Integer.parseInt(data[k][i]) > Integer.parseInt(data[k + 1][i])) {
							String temp = data[k][i];
							data[k][i] = data[k+1][i];
							data[k+1][i] = temp;
						}
					}
				}
			}

			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data[0].length; j++){
					output[i][j] = data[i][j];
				}
			}
			if(data.length % 2 != 0){
				for (int i = 0; i < data[0].length; i++){
					output[data.length][i] = data[(data.length/2) + 1][i];
				}
			}
			else {
				for (int i = 0; i < data[0].length; i++){
					Double mediana  = (Double.parseDouble(data[data.length/2][i] )+ Double.parseDouble(data[(data.length/2) + 1][i])) / 2;
					output[data.length][i] = mediana.toString();
				}
			}

			outputDataSet = new DataSet();
			outputDataSet.setData(output);

			isAnalysing= false;
		}

	}
	


}
