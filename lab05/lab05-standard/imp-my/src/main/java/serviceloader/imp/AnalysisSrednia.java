package serviceloader.imp;

import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;

public class AnalysisSrednia implements AnalysisService, Runnable {

	private boolean isAnalysing;
	private DataSet dataSet, outputDataSet = null;
	private Thread thread= new Thread(this);
	
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
		return "Średnia arytmetyczna";
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

			for (int i = 0; i < data[0].length; i++) {
				Double arytm = 0.0;
				for (int j = 0; j < data.length; j++){
					arytm +=  Double.parseDouble(data[j][i]);
					output[j][i] = data[j][i];
				}
				arytm = arytm/data.length;
				output[data.length][i] = arytm.toString();
			}


			outputDataSet = new DataSet();
			outputDataSet.setData(output);

			isAnalysing= false;
		}
	}



}
