package com.quiz.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class GenAIService {
    // --- ACTION REQUIRED ---
    private static final String API_KEY = "AIzaSyBRN7YuyifG8xZqwxO_pKCf5khm6AzuJsY";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=";

    public String generateCourseContent(String topic, String level) {
        try {
            // This prompt asks the AI for both learning material and a quiz
            String prompt = String.format(
                "Create a short, personalized learning course on the topic of '%s' for a '%s' level learner. " +
                "The course should have a title, a brief introduction, and 3 distinct lesson sections. Each lesson must have a title and a paragraph of learning material. " +
                "After the lessons, create a 3-question multiple-choice quiz based on the material. Each question must have 3 options, with only one being correct. " +
                "Return the output ONLY as a single, valid JSON object. Do not include any text, markdown formatting like ```json, or any other characters before or after the JSON. " +
                "The JSON must strictly follow this structure: " +
                "{\"courseTitle\": \"...\", \"introduction\": \"...\", \"lessons\": [{\"lessonTitle\": \"...\", \"material\": \"...\"}], \"quiz\": [{\"questionText\": \"...\", \"options\": [{\"optionText\": \"...\", \"isCorrect\": boolean}]}]}",
                topic, level
            );

            // 1. Construct the JSON body for the API request
            String jsonBody = new JSONObject()
                .put("contents", new JSONObject[]{
                    new JSONObject().put("parts", new JSONObject[]{
                        new JSONObject().put("text", prompt)
                    })
                })
                .toString();

            // 2. Create the HTTP Client and Request
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + API_KEY)) // Append the key to the URL
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

            // 3. Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Parse the response to get the generated content
            JSONObject responseJson = new JSONObject(response.body());
            String generatedContent = responseJson.getJSONArray("candidates")
                                                  .getJSONObject(0)
                                                  .getJSONObject("content")
                                                  .getJSONArray("parts")
                                                  .getJSONObject(0)
                                                  .getString("text");

            return generatedContent;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}