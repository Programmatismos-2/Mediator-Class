package com.freddo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class GiveFileToAI {
    public static String fileToVariable(String userquestion) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("1.txt"));   
        //Δημιουργία ενός αντικειμένου BufferedReader για να διαβάσει το περιεχόμενο από το αρχείο   
          String line = null;
          //Δημιουργία μιας μεταβλητής line που θα χρησιμοποιηθεί για να διαβάζει κάθε γραμμή από το αρχείο
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            //Διάβασμα κάθε γραμμής από το αρχείο και προσάρτησή της στο StringBuilder
            //Το while συνεχίζει να διαβάζει μέχρι να φτάσει στο τέλος του αρχείου
            String dataToAI = stringBuilder.toString() + "\n input: " + userquestion ;
            //Δημιουργία μιας συμβολοσειράς που περιλαμβάνει το περιεχόμενο του αρχείου και την ερώτηση του χρήστη
            return dataToAI;
            //Επιστροφή της συμβολοσειράς
        } finally {
            reader.close();
            //Κλείσιμο του συγκεκριμένου αντικειμένου
        }
        }
}
