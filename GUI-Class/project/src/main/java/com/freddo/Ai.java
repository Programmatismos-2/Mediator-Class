package com.freddo;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
 
public class Ai {
static HttpClient client = HttpClient.newHttpClient();
    public static String hey(String question) throws IOException, InterruptedException {
        String a = GiveFileToAI.fileToVariable(question);
        String requestBody = "{\n" +
        " \"instances\": [\n" +
        " {\n" +
        "\"content\": \"" + a + " \",\n" +
        " \"output\": \"\"\n" +
        " }\n" +
        " ],\n" +
        " \"parameters\": {\n" +
        " \"candideateCount\": 1,\n" +
        " \"maxOutputTokens\": 1024,\n" +
        " \"temperature\": 0.8,\n" +
        " \"topP\": 0.8,\n" +
        " \"topK\": 40\n" +
        " }\n" +
        "}";
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://server_location-aiplatform.googleapis.com/v1/projects/project_name/locations/server_location/publishers/google/models/text-bison:predict"))
        .POST(BodyPublishers.ofString(requestBody))
        .header("Content-Type", "application/json")
        .header("Authorization", "Bearer " + RunBashCommand.aoth2())
        .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());;
        return ("Response Body: " + response.body());
    }    
}
