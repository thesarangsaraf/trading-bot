package com.trading.bot.analyser;

import com.trading.bot.dto.PriceOhlcDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsolidationDetector {

    public List<List<PriceOhlcDTO>> detectConsolidation(List<PriceOhlcDTO> candles, int minCandles, double maxRangePercent) {
        List<List<PriceOhlcDTO>> consolidations = new ArrayList<>();
        int n = candles.size();
        int i = 0;

        while (i <= n - minCandles) {
            int j = i;
            double highestHigh = candles.get(j).getHigh();
            double lowestLow = candles.get(j).getLow();

            while (j < n) {
                highestHigh = Math.max(highestHigh, candles.get(j).getHigh());
                lowestLow = Math.min(lowestLow, candles.get(j).getLow());

                double range = highestHigh - lowestLow;
                double base = (highestHigh + lowestLow) / 2.0;
                double rangePercent = (range / base) * 100;

                if (rangePercent > maxRangePercent) break;
                j++;
            }

            int duration = j - i;
            if (duration >= minCandles) {
                List<PriceOhlcDTO> block = candles.subList(i, j);
                consolidations.add(new ArrayList<>(block));
                i = j;  // skip to end of consolidation
            } else {
                i++;  // move window forward
            }
        }

        return consolidations;
    }
}