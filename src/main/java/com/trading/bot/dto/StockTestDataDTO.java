package com.trading.bot.dto;

import lombok.Data;

@Data
public class StockTestDataDTO {
    private String date;
    private double open;
    private double high;
    private double low;
    private double price;
    private double volume;
    private String change;
}