package com.trading.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandleDTO {
    private long timestamp;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
}