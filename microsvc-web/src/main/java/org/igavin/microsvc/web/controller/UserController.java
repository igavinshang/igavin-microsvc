package org.igavin.microsvc.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.igavin.microsvc.api.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/username")
  public String getUserName(@RequestParam("id") Long id) {
    return userService.getUserName(id);
  }

  @RequestMapping("/echo")
  public String echo() {
     return "helloworld";
  }
}
