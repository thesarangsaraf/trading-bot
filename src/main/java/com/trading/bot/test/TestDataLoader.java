package com.trading.bot.test;

import com.trading.bot.test.util.CsvToJsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class TestDataLoader {

    @Autowired
    private CsvToJsonMapper csvToJsonMapper;

    public void getTestData() {
        File csvFile = new File("stock_data.csv");

    }

}
