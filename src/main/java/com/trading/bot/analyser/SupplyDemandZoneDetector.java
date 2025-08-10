package com.trading.bot.analyser;

import com.trading.bot.dto.PriceOhlcDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyDemandZoneDetector {

    // Identify consolidation
    // Identify candle when it breaks consolidation
    // Mark last high / low of that candle as entry point

    public void detect(List<PriceOhlcDTO> candles) {
        // Phase 1 - detect consolidation


    }

//    public void findConsolidationOccurance();

//    public void findConsolidationBreak();

//    public void findEntryPoint();

}
