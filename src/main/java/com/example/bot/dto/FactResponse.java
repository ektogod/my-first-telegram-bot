package com.example.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FactResponse(@JsonProperty("text") String fact) {
}
