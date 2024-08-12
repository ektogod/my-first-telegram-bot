package com.example.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DogResponse(@JsonProperty("message") String dogImageURL) {
}
