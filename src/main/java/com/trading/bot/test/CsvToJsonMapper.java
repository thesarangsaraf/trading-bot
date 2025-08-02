package com.trading.bot.test;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.trading.bot.dto.StockTestDataDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CsvToJsonMapper {

    public List<StockTestDataDTO> map(File csvFile) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withQuoteChar('"');

        List<StockTestDataDTO> stockDataList = null;
        try {
            MappingIterator<StockTestDataDTO> iterator = csvMapper.readerFor(StockTestDataDTO.class)
                    .with(schema)
                    .readValues(csvFile);

            stockDataList = iterator.readAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stockDataList;
    }
}
