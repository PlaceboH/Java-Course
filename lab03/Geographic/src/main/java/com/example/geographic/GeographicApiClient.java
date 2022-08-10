package com.example.geographic;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;


public class GeographicApiClient {
    private final String x_rapidapi_key = "";
    private final String x_rapidapi_host = "api.teleport.org";
    private final String continentsEndpoint = "https://api.teleport.org/api/continents/";
    private final String countriesEndpoint = "https://api.teleport.org/api/countries/";
    private final String helpCountriesEndpoint = "countries/";



    private HttpRequest request;
    private HttpResponse<String> response;
    private JSONObject jsonData;
    private HttpClient client;

    HashMap<String, String> allContinents;
    HashMap<String, String> allCountries;

    public GeographicApiClient() throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();
        this.allContinents = getAllContinents();
        this.allCountries = getAllCountries();
    }


     public HashMap<String, String> getAllContinents() throws IOException, InterruptedException {
        HashMap<String, String> listOfContinents = new HashMap<>();
        getAllModel(continentsEndpoint);
        int countContinents =  Integer.parseInt(jsonData.get("count").toString());
        JSONObject links = (JSONObject) jsonData.get("_links");
        JSONArray continentsItems = (JSONArray) links.get("continent:items");

        while (countContinents != 0){
            JSONObject continent = (JSONObject)continentsItems.get(countContinents-1);;
            listOfContinents.put(continent.get("name").toString(), continent.get("href").toString() );
            countContinents--;
        }
        return listOfContinents;
    }

    public HashMap<String, String> getAllCountries() throws IOException, InterruptedException {
        HashMap<String, String> listOfCountries = new HashMap<>();
        getAllModel(countriesEndpoint);
        int countCountries =  Integer.parseInt(jsonData.get("count").toString());
        JSONObject links = (JSONObject) jsonData.get("_links");
        JSONArray countiesItems = (JSONArray) links.get("country:items");

        while (countCountries != 0){
            JSONObject country = (JSONObject)countiesItems.get(countCountries-1);
            listOfCountries.put(country.get("name").toString(), country.get("href").toString() );
            countCountries--;
        }
        return listOfCountries;
    }


    public int getCountCountriesInContinent(String continent) throws IOException, InterruptedException {
        int countContinents = -1;
        if(this.allContinents.containsKey(continent)){
            getAllModel(this.allContinents.get(continent) + helpCountriesEndpoint);
            countContinents =  Integer.parseInt(jsonData.get("count").toString());
        }
        return countContinents;
    }

    public String getCountryCurrency(String country) throws IOException, InterruptedException {
        String currency = "NXN";
        if(this.allCountries.containsKey(country)){
            getAllModel(this.allCountries.get(country));
            currency = jsonData.get("currency_code").toString();
        }
        return currency;
    }

    public String getCountryPopulation(String country) throws IOException, InterruptedException {
        String population = "-1";
        if(this.allCountries.containsKey(country)){
            getAllModel(this.allCountries.get(country));
            population = jsonData.get("population").toString();
        }
        return population;
    }





    private void getAllModel(String endpoint) throws IOException, InterruptedException {
        URI uri = URI.create(endpoint);
        setRequeset(uri);
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        jsonData = ReadJSON.readJsonFromUrl(uri.toString());
    }


    private void setRequeset(URI uri) {
        request = HttpRequest.newBuilder().uri(uri).header("x-rapidapi-key", x_rapidapi_key)
                .header("x-rapidapi-host", x_rapidapi_host).method("GET", HttpRequest.BodyPublishers.noBody()).build();
    }


}
