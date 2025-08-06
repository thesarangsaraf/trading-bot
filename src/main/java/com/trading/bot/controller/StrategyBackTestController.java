package com.trading.bot.controller;

import com.trading.bot.service.StrategyBackTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategy")
public class StrategyBackTestController {

    @Autowired
    private StrategyBackTestService strategyBackTestService;

    @GetMapping("/back-test")
    public void generate(){
        strategyBackTestService.startTesting();
    }

}
