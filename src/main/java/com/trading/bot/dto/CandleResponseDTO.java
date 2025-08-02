package com.trading.bot.dto;

public class CandleResponseDTO {
    private String status;
    private PayloadDTO payload;

    // Getters and Setters
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public PayloadDTO getPayload() { return payload; }
    public void setPayload(PayloadDTO payload) { this.payload = payload; }
}