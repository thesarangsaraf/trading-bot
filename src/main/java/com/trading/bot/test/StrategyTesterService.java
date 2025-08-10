package com.trading.bot.test;

import com.trading.bot.dto.PriceOhlcDTO;
import com.trading.bot.test.util.HistoricTestDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StrategyTesterService {

    @Autowired
    private HistoricTestDataLoader historicTestDataLoader;

    @Autowired
    private SupplyDemandZoneStrategyTester supplyDemandZoneStrategyTester;

    public void startTesting() {
        // Load historic test data
        HashMap<String, List<PriceOhlcDTO>> dataMap = historicTestDataLoader.loadData();

        for (String instrumentName : dataMap.keySet()) {
            // Test Supply and Demand
            System.out.println("---- " + instrumentName + " -----");
            supplyDemandZoneStrategyTester.startTesting(dataMap.get(instrumentName));
            System.out.println("---- END -----");

        }

    }

}
