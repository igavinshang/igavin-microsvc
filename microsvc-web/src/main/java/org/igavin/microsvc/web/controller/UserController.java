package org.igavin.microsvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.igavin.microsvc.api.UserService;

@RestController
public class UserController {

  final static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  @GetMapping("/name/{id}")
  public String getUserNameById(@PathVariable("id") Long id) {

    logger.info("get-microsvc-web/name/"+id);
    return userService.getUserName(id);
  }

  @RequestMapping("/username")
  public String getUserName(@RequestParam("id") Long id) {

    logger.info("get-microsvc-web/username/"+id);
    return userService.getUserName(id);
  }

  @RequestMapping("/echo")
  public String echo() {
     return "helloworld";
  }
}
