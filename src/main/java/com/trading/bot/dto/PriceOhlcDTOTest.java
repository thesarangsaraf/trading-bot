package com.trading.bot.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.trading.bot.test.util.CommaDoubleDeserializer;
import lombok.Data;

import java.util.Date;

@Data
public class PriceOhlcDTOTest {

    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date date;

    @JsonProperty("Price")
    @JsonDeserialize(using = CommaDoubleDeserializer.class)
    private double price;

    @JsonProperty("Open")
    @JsonDeserialize(using = CommaDoubleDeserializer.class)
    private double open;

    @JsonProperty("High")
    @JsonDeserialize(using = CommaDoubleDeserializer.class)
    private double high;

    @JsonProperty("Low")
    @JsonDeserialize(using = CommaDoubleDeserializer.class)
    private double low;

    @JsonProperty("Vol.")
    private String volumeRaw; // raw string like "16.61M"

    private double volume; // parsed numeric value

    @JsonProperty("Change %")
    private String changePercent;

}