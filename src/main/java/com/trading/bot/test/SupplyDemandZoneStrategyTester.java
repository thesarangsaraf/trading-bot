package com.trading.bot.test;

import com.trading.bot.dto.PriceOhlcDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyDemandZoneStrategyTester {

    private int consolidationPeriod = 4;
    private double impulseMultiplier = 2.0;

    public void startTesting2(List<PriceOhlcDTO> candles) {
        int n = candles.size();
        int i = 0;

        while (i <= n - consolidationPeriod) {
            int j = i;
            double highestHigh = candles.get(j).getHigh();
            double lowestLow = candles.get(j).getLow();

            while (j < n) {
                highestHigh = Math.max(highestHigh, candles.get(j).getHigh());
                lowestLow = Math.min(lowestLow, candles.get(j).getLow());

                double range = highestHigh - lowestLow;
                double base = (highestHigh + lowestLow) / 2.0;
                double rangePercent = (range / base) * 100;

                if (rangePercent > impulseMultiplier) break;
                j++;
            }

            int duration = j - i;
            if (duration >= consolidationPeriod) {
                consolidations.add(List.of(i, j));
                i = j;  // skip to end of consolidation
            } else {
                i++;  // move window forward
            }
        }
    }

    public void startTesting(List<PriceOhlcDTO> candles) {
        for (int i = consolidationPeriod; i < candles.size(); i++) {
            
            // 1. Check consolidation
            double avgRange = 0;
            for (int j = i - consolidationPeriod; j < i; j++) {
                avgRange += (candles.get(j).getHigh() - candles.get(j).getLow());
            }
            avgRange /= consolidationPeriod;

            // 2. Check for impulsive move
            PriceOhlcDTO current = candles.get(i);
            double currentRange = current.getHigh() - current.getLow();

            if (currentRange >= avgRange * impulseMultiplier) {
                PriceOhlcDTO baseCandle = candles.get(i - 1); // last before impulse

                if (current.getClose() > current.getOpen()) { // Bullish impulse
                    double entry = baseCandle.getHigh();
                    double stopLoss = baseCandle.getLow();
                    System.out.println("BUY setup at " + current.getDate() +
                            " | Entry: " + entry +
                            " | SL: " + stopLoss);
                } else { // Bearish impulse
                    double entry = baseCandle.getLow();
                    double stopLoss = baseCandle.getHigh();
                    System.out.println("SELL setup at " + current.getDate() +
                            " | Entry: " + entry +
                            " | SL: " + stopLoss);
                }
            }
        }
    }

}
