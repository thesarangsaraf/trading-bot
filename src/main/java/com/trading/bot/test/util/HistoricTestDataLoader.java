package com.trading.bot.test.util;

import com.trading.bot.dto.PriceOhlcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Service
public class HistoricTestDataLoader {

    private String historicDataPath = "/Users/thesarangsaraf/git/trading-bot/src/main/resources/test-data";

    @Autowired
    private CsvToJsonMapper csvToJsonMapper;

    public HashMap<String, List<PriceOhlcDTO>> loadData() {
        File folder = new File(historicDataPath);
        File[] files = folder.listFiles();

        HashMap<String, List<PriceOhlcDTO>> map = new HashMap<>();

        for (File f : files) {
            try {
                List<PriceOhlcDTO> historicData = csvToJsonMapper.map(f);
                map.put(f.getName().substring(0, f.getName().indexOf(" ")), historicData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
