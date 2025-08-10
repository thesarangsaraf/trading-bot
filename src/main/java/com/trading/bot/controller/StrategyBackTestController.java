package com.trading.bot.controller;

import com.trading.bot.test.StrategyTesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategy")
public class StrategyBackTestController {

    @Autowired
    private StrategyTesterService strategyTesterService;

    @GetMapping("/back-test")
    public void startTest(){
        strategyTesterService.startTesting();
    }

}
