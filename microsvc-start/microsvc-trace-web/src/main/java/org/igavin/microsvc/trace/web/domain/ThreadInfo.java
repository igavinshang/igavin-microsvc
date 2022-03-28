package org.igavin.microsvc.trace.web.domain;


import java.io.Serializable;

public class ThreadInfo implements Serializable {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "ThreadInfo{" +
        "name='" + name + '\'' +
        '}';
  }
}
