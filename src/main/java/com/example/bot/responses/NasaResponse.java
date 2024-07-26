package com.example.bot.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NasaResponse {
    private String url;
    private String title;
    private String explanation;
}
