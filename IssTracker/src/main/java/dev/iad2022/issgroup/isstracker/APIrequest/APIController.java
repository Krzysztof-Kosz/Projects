package dev.iad2022.issgroup.isstracker.APIrequest;

import org.apiguardian.api.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class APIController{
    private static final String API_URL = "https://api.wheretheiss.at/v1/satellites/25544";

    public static ISSData[] getISSData() {
        // Craft a API url
        StringBuilder urlAddress = new StringBuilder(API_URL + "/positions?timestamps=");
        final long timestamp = new Date().getTime() / 1000L;
        List<String> timestamps = LongStream.range(0, 10)
                .mapToObj(multiplier -> String.valueOf((timestamp + multiplier * 400)))
                .toList();
        urlAddress.append(String.join(",", timestamps));
        try { // Make a request
            URL url = new URL(urlAddress.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() != 200) {
                throw new ConnectException("Błąd podczas wysyłania żądania: " + connection.getResponseCode());
            }
            // Parse the request
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = bufferedReader.readLine();
            String[] entries = line.split("},*");
            ISSData[] fetchedISSData = new ISSData[10];
            for (int i = 0; i < entries.length - 1; i++){
                String[] verse = entries[i].split(",");
                double lat = Double.parseDouble(verse[2].split(":")[1]);
                double lng = Double.parseDouble(verse[3].split(":")[1]);
                double alt = Double.parseDouble(verse[4].split(":")[1]);
                double speed = Double.parseDouble(verse[5].split(":")[1]);
                fetchedISSData[i] = new ISSData(lat, lng, alt, speed);
            }
            bufferedReader.close();
            // Return parsed data
            return fetchedISSData;
        } catch (Exception e) {
            System.out.println("Wystąpił błąd: " + e.getMessage());
        }
        return null;
    }
}
