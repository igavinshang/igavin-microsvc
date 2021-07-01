package org.igavin.microsvc.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.igavin.microsvc.api.UserService;

@RestController
public class UserController {

  final static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

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
