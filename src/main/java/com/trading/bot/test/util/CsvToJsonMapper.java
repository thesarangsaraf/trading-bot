package com.trading.bot.test.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.trading.bot.dto.PriceOhlcDTO;
import com.trading.bot.dto.PriceOhlcDTOTest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CsvToJsonMapper {

    public List<PriceOhlcDTO> map(File csvFile) {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withColumnSeparator(',')
                .withQuoteChar('"');

        try {
            MappingIterator<PriceOhlcDTOTest> iterator = csvMapper.readerFor(PriceOhlcDTOTest.class)
                    .with(schema)
                    .readValues(csvFile);

            return iterator.readAll().stream().map(PriceOhlcTestToPriceOhlcMapper::map).toList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
