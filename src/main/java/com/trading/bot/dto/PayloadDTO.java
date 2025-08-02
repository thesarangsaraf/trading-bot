package com.trading.bot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PayloadDTO {
    private List<CandleDTO> candles;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private int interval_in_minutes;

    // Getters and Setters
    public List<CandleDTO> getCandles() { return candles; }
    public void setCandles(List<CandleDTO> candles) { this.candles = candles; }

    public LocalDateTime getStart_time() { return start_time; }
    public void setStart_time(LocalDateTime start_time) { this.start_time = start_time; }

    public LocalDateTime getEnd_time() { return end_time; }
    public void setEnd_time(LocalDateTime end_time) { this.end_time = end_time; }

    public int getInterval_in_minutes() { return interval_in_minutes; }
    public void setInterval_in_minutes(int interval_in_minutes) { this.interval_in_minutes = interval_in_minutes; }
}
