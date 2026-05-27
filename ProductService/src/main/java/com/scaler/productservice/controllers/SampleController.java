package com.scaler.productservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/hello/{name}/{times}")
    public String hello(@PathVariable("name") String n, @PathVariable("times") int x){
        String result = "";

        for(int i=0; i<x; i++){
            result += "Hello " + n + " ! <br>";
        }

        return result;
    }
}
