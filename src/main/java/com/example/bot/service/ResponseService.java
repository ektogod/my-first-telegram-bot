package com.example.bot.service;

import com.example.bot.ResponseException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ResponseService {
    public <T> T getResponse(String url, Class<T> responseType) throws HttpClientErrorException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(url, responseType);
        }
        catch (HttpClientErrorException ex){
            throw new ResponseException(ex.getMessage());
        }
    }
}
