package com.trading.bot.chart;

import com.trading.bot.dto.StockTestDataDTO;
import com.trading.bot.test.CsvToJsonMapper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class ChartImageGenerator {

    @Autowired
    private CsvToJsonMapper csvToJsonMapper;

    public void generate(){
        File folder = new File("/Users/thesarangsaraf/git/trading-bot/src/main/resources/files");
        File[] files = folder.listFiles();

        for (File f: files) {
            try {
                List<StockTestDataDTO> historicData = csvToJsonMapper.map(f);

                this.generate(f.getName(), historicData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void generate(String stockName, List<StockTestDataDTO> historicData) throws Exception {
        historicData = historicData.subList(0, 10);

        List<String> dateStrs = historicData.stream().map(StockTestDataDTO::getDate).toList();

        double[] open = historicData.stream().map(StockTestDataDTO::getOpen).mapToDouble(Double::doubleValue).toArray();
        double[] high = historicData.stream().map(StockTestDataDTO::getHigh).mapToDouble(Double::doubleValue).toArray();
        double[] low = historicData.stream().map(StockTestDataDTO::getLow).mapToDouble(Double::doubleValue).toArray();
        double[] close = historicData.stream().map(StockTestDataDTO::getPrice).mapToDouble(Double::doubleValue).toArray();
        double[] volume = historicData.stream().map(StockTestDataDTO::getVolume).mapToDouble(Double::doubleValue).toArray();

        // Convert date strings to java.util.Date[]
        Date[] dates = new Date[dateStrs.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        for (int i = 0; i < dateStrs.size(); i++) {
            dates[i] = sdf.parse(dateStrs.get(i));
        }

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
        File outputFile = new File(stockName + "candlestick_chart.png");
        ChartUtils.saveChartAsPNG(outputFile, chart, 800, 600);
        System.out.println("Chart saved to " + outputFile.getAbsolutePath());

    }

}