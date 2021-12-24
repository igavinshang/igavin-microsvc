package org.igavin.microsvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.igavin.microsvc.api.UserService;

//此处不需要添加任何资源埋点，在默认情况下Sentinel Starter会对所有HTTP请求进行限流
@RestController
public class UserController {

  final static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @GetMapping("/db/{id}")
  public String getUserNameById(@PathVariable("id") Long id) {

    logger.info("get-microsvc-web/db/"+id);
    return userService.getTopOne();
  }


  @RequestMapping("/echo")
  public String echo() {
     return "helloworld";
  }
}
