package com.trading.bot.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CandleResponseDTO {
    private String status;
    private PayloadDTO payload;
}