package com.freddo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class GiveFileToAI {
    public static String fileToVariable(String userquestion) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("1.txt"));      
          String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String dataToAI = stringBuilder.toString() + "\n input: " + userquestion ;
            return dataToAI;
        } finally {
            reader.close();
        }
        }
}
