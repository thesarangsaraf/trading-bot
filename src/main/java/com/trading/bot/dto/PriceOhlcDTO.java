package com.trading.bot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceOhlcDTO {
    private long timestamp;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;

    public Date getDate() {
        return new Date(timestamp);
    }

    @Override
    public String toString() {
        return "PriceOhlcDTO{" +
                "timestamp=" + new Date(timestamp) +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                '}';
    }
}