package com.example.bot.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NasaResponse {
    private String url;
    private String title;
    private String explanation;
}
