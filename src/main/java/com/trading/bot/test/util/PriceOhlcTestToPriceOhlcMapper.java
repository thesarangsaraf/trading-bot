package com.trading.bot.test.util;

import com.trading.bot.dto.PriceOhlcDTO;
import com.trading.bot.dto.PriceOhlcDTOTest;

import java.util.ArrayList;
import java.util.List;

public class PriceOhlcTestToPriceOhlcMapper {

    public static PriceOhlcDTO map(PriceOhlcDTOTest input) {
        //long timestamp, double open, double high, double low, double close, long volume
        return new PriceOhlcDTO(input.getDate().getTime(), input.getOpen(), input.getHigh(), input.getLow(), input.getPrice(), 0);
    }

    public static List<PriceOhlcDTO> map(List<PriceOhlcDTOTest> inputs) {
        List<PriceOhlcDTO> output = new ArrayList<>();
        for(PriceOhlcDTOTest dto: inputs) {
            output.add(map(dto));
        }
        return output;
    }

}