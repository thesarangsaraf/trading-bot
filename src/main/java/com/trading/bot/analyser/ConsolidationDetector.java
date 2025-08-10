package com.trading.bot.analyser;

import com.trading.bot.dto.PriceOhlcDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsolidationDetector {

    private int minConsolidationCondles = 4;
    private double maxConsolidationRangePercentage = 2.0;

    public List<List<Integer>> detect(List<PriceOhlcDTO> candles){
        return detect(candles, minConsolidationCondles, maxConsolidationRangePercentage);
    }

    public List<List<Integer>> detect(List<PriceOhlcDTO> candles, int minCandles, double maxRangePercent) {
        List<List<Integer>> consolidations = new ArrayList<>();
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
                consolidations.add(List.of(i, j));
                i = j;  // skip to end of consolidation
            } else {
                i++;  // move window forward
            }
        }

        return consolidations;
    }
}