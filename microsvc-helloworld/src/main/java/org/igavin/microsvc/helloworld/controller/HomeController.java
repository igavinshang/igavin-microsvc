package org.igavin.microsvc.helloworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HomeController {
    final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/echo")
    public String echo() {
        logger.info("/echo by call:"+new Date());
        return "helloworld";
    }
}
