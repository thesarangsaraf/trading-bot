package com.trading.bot.chart;

import com.trading.bot.dto.PriceOhlcDTOTest;
import com.trading.bot.test.util.CsvToJsonMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class ChartImageGenerator {

    @Autowired
    private CsvToJsonMapper csvToJsonMapper;

    public void generate(){
        File folder = new File("/Users/thesarangsaraf/git/trading-bot/src/main/resources/test-data");
        File[] files = folder.listFiles();

        for (File f: files) {
            try {
                List<PriceOhlcDTOTest> historicData = csvToJsonMapper.map(f);

                this.generate(f.getName(), historicData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void generate(String stockName, List<PriceOhlcDTOTest> historicData) throws Exception {
        historicData = historicData.subList(100, 300);

        Date[] dates = historicData.stream().map(PriceOhlcDTOTest::getDate).toArray(Date[]::new);

        double[] open = historicData.stream().map(PriceOhlcDTOTest::getOpen).mapToDouble(Double::doubleValue).toArray();
        double[] high = historicData.stream().map(PriceOhlcDTOTest::getHigh).mapToDouble(Double::doubleValue).toArray();
        double[] low = historicData.stream().map(PriceOhlcDTOTest::getLow).mapToDouble(Double::doubleValue).toArray();
        double[] close = historicData.stream().map(PriceOhlcDTOTest::getPrice).mapToDouble(Double::doubleValue).toArray();
        double[] volume = historicData.stream().map(PriceOhlcDTOTest::getVolume).mapToDouble(Double::doubleValue).toArray();

        // Create dataset
        DefaultHighLowDataset dataset = new DefaultHighLowDataset(
                "Stock Data", dates, high, low, open, close, volume
        );

        // Generate chart
        JFreeChart chart = ChartFactory.createCandlestickChart(
                "OHLC Candlestick Chart",
                "Date", "Price", dataset,
                false // no legend
        );

        // Save chart to PNG
        File outputFile = new File(stockName + ".png");
        ChartUtils.saveChartAsPNG(outputFile, chart, 1920, 1080);
        System.out.println("Chart saved to " + outputFile.getAbsolutePath());

    }

}