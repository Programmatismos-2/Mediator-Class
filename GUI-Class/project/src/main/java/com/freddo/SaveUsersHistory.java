package com.freddo;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveUsersHistory {

    public static String SaveEmailAndQuestion(String flightnumber,String Question) throws IOException, InterruptedException{


        String csvFilePath = "your_file.csv";

      //  Ai aiobj = new Ai();
        String inputText = Ai.hey("question");//θα καλώ την AI
        String textWithoutCommas = removeCommas(inputText);
        System.out.println("Original Text: " + inputText);
        System.out.println("Text without Commas: " + textWithoutCommas);

        saveDataToCSV(flightnumber, Question, textWithoutCommas, csvFilePath);
        return inputText;

    }

    private static void saveDataToCSV(String email, String question, String answer, String csvFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            // Append a new line with email, question, and answer
            writer.write(email + "," + question + "," + answer);
            writer.newLine();

            System.out.println("Data has been written to the CSV file.");

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
    private static String removeCommas(String inputText) {
        // Use the replace method to remove all commas
        return inputText.replaceAll(",", "");
    }
}
