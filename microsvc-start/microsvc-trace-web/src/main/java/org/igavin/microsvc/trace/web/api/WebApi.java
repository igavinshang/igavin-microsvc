package org.igavin.microsvc.trace.web.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microsvc-web")
public interface WebApi {

    @GetMapping("/echo")
    String echo();

    @GetMapping("/username/{id}")
    public String getUserName(@PathVariable("id") Long id);

}
