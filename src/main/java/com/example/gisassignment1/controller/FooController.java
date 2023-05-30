package com.example.gisassignment1.controller;


import com.example.gisassignment1.model.Foo;
import com.example.gisassignment1.service.FooService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class FooController {

    private final FooService fooService;

    public FooController(FooService fooService) {
        this.fooService = fooService;
    }


    @GetMapping("/date")
    public List<Foo> foo(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
      return   fooService.fooDate(date);
    }


    @GetMapping("/betweenTwoDates")
    public List<Foo> foo2(@RequestParam("date1") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("date2") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date2){
       return fooService.fooBetween(date , date2);
    }


}
