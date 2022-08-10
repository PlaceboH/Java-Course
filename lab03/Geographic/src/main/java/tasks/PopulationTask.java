package tasks;

import com.example.geographic.GeographicApiClient;

import java.io.IOException;
import java.util.HashMap;

public class PopulationTask implements TaskInterface {

    private String NameOfTask = "Population of Country Task";

    GeographicApiClient geographicApiClient;

    @Override
    public String getNameOfTask() {
        return NameOfTask;
    }


    public PopulationTask() throws IOException, InterruptedException {
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
    public String checkIsCorrectAnswer(String country, String answer ) throws IOException, InterruptedException {
        String population = geographicApiClient.getCountryPopulation(country);
        if(population == answer){
            return "Yes, you are right!";
        }
        return "No, go back to school";
    }


}
