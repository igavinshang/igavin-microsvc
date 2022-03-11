package org.igavin.microsvc.web.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microsvc-web")
public interface WebApi {

    @GetMapping("/echo")
    String echo();

    @GetMapping("/sentinel/hello/{id}")
    public String hello(@PathVariable("id") Integer id);

}
