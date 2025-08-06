package com.trading.bot.test.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.trading.bot.dto.PriceOhlcDTOTest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CsvToJsonMapper {

    public List<PriceOhlcDTOTest> map(File csvFile) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withQuoteChar('"');

        List<PriceOhlcDTOTest> stockDataList = null;
        try {
            MappingIterator<PriceOhlcDTOTest> iterator = csvMapper.readerFor(PriceOhlcDTOTest.class)
                    .with(schema)
                    .readValues(csvFile);

            stockDataList = iterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stockDataList;
    }
}
