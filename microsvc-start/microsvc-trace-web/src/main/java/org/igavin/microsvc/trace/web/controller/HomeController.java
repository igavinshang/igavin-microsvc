package org.igavin.microsvc.trace.web.controller;

import org.igavin.microsvc.trace.web.api.WebApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  final static Logger logger = LoggerFactory.getLogger(HomeController.class);

  @Autowired private WebApi webApi;

  @RequestMapping("/username")
  public String getUserName(@RequestParam("id") Long id) {

    logger.info("get-microsvc-trace-web/username/"+id);
    return webApi.getUserName(id);
  }

  @RequestMapping("/echo")
  public String echo() {
    return webApi.echo();
  }
}