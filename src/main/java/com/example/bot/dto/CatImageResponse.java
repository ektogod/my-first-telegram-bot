package com.example.bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@Getter
@NoArgsConstructor

public class CatImageResponse {
    @JsonProperty("url")
    String catImageURL;
}
