package com.anthonydelacruz.listadodelibros.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class Traductor {

    private final RestTemplate restTemplate = new RestTemplate();

    public String translateBook(String text, String targetLanguage) {

        String apiUrl = "https://api.openai.com/v1/translations";
        String apiKey = "your-api-key-here"; // Coloca tu clave de API aquí

        return "Texto traducido (ficticio): " + text + " a " + targetLanguage;
    }
}
