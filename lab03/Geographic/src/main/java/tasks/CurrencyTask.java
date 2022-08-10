package tasks;

import com.example.geographic.GeographicApiClient;

import java.io.IOException;
import java.util.HashMap;

public class CurrencyTask implements TaskInterface {

    final private String nameOfTask = "Country Currency Task";
    GeographicApiClient geographicApiClient;

    public CurrencyTask() throws IOException, InterruptedException {
        geographicApiClient = new GeographicApiClient();
    }
    @Override
    public String getNameOfTask() {
        return nameOfTask;
    }

    @Override
    public HashMap<String, String> getAllItems() throws IOException, InterruptedException {
        return geographicApiClient.getAllCountries();
    }

    @Override
    public String getQuestion() {
        return "What is the population of ";
    }

    @Override
    public String checkIsCorrectAnswer(String country, String answer) throws IOException, InterruptedException {
        String currency = geographicApiClient.getCountryCurrency(country);
        if(currency == answer){
            return "Yes, you are right!";
        }
        return "No, go back to school";
    }
}
