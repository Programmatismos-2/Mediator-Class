package com.freddo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveUsersCredentials {
    public static void SaveStringToCSV(String email, String firstname, String lastname, String flightnumber ) {
        String filePath = "output.csv"; // Path to the CSV file


        try {
            FileWriter fileWriter = new FileWriter(filePath, true); // Use 'true' to append data to an existing file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String dataToSave = email + "," + firstname + "," + lastname + "," + flightnumber;
            bufferedWriter.write(dataToSave);
            bufferedWriter.newLine(); // Move to the next line (row)

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Data saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
