package com.trading.bot.service;

import com.trading.bot.analyser.ConsolidationDetector;
import com.trading.bot.dto.PriceOhlcDTO;
import com.trading.bot.dto.PriceOhlcDTOTest;
import com.trading.bot.test.HistoricTestDataLoader;
import com.trading.bot.test.util.PriceOhlcTestToPriceOhlcMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StrategyBackTestService {

    @Autowired
    private HistoricTestDataLoader historicTestDataLoader;

    @Autowired
    private ConsolidationDetector consolidationDetector;

    public void startTesting() {
        HashMap<String, List<PriceOhlcDTOTest>> dataMap = historicTestDataLoader.loadData();

        for (String key: dataMap.keySet()) {
            List<PriceOhlcDTOTest> stockTestData = dataMap.get(key);
            List<List<PriceOhlcDTO>> consolidationAreas
                    = consolidationDetector.detectConsolidation(PriceOhlcTestToPriceOhlcMapper.map(stockTestData), 4, 1.0);

            System.out.println("Consolidations for " + key + " START");
            consolidationAreas.stream().forEach(c -> System.out.println(c));
            System.out.println("Consolidations for " + key + " END");
        }

    }

}