package com.trading.bot.controller;

import com.trading.bot.chart.ChartImageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChartImageGeneratorController {

    @Autowired
    private ChartImageGenerator chartImageGenerator;

    @GetMapping("/generate")
    public void generate(){
        try {
            chartImageGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
