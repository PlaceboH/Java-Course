package tasks;

import com.example.geographic.GeographicApiClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CountryTask implements TaskInterface {

    private String NameOfTask = "Continent Country Task";

    GeographicApiClient geographicApiClient;

    @Override
    public String getNameOfTask() {
        return NameOfTask;
    }


    public CountryTask() throws IOException, InterruptedException {
        geographicApiClient = new GeographicApiClient();
    }

    @Override
    public HashMap<String, String> getAllItems() throws IOException, InterruptedException {
        return geographicApiClient.getAllContinents();
    }

    @Override
    public String getQuestion(){
        return "How many countries in continent";
    }


    @Override
    public String checkIsCorrectAnswer(String continent, String answer ) throws IOException, InterruptedException {
        int count = geographicApiClient.getCountCountriesInContinent(continent);
        if(count == Integer.parseInt(answer)){
            return "Yes, you are right!";
        }
        return "No, go back to school";
    }


}
