package com.freddo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlightChecker {

    public static boolean checkFlightExistence(String lastName, String flightNumber) {
        String csvFilePath = "output.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the CSV line into fields
                String[] fields = line.split(",");

                // Check if the current row matches the provided last name and flight number
                if (fields.length >= 4 &&
                        fields[2].equals(lastName) &&
                        fields[3].equals(flightNumber)) {
                    return true; // Flight exists
                }
            }
            return false; // Flight not found
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
            return false;
        }
    }

}
