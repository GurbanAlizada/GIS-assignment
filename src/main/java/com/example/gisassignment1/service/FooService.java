package com.example.gisassignment1.service;


import com.example.gisassignment1.model.Foo;
import com.example.gisassignment1.repository.FooRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.util.List;

@Service
public class FooService {

    private final FooRepository fooRepository;


    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }




    public List<Foo> fooDate(LocalDate localDate){
        return fooRepository.getByBirth(localDate);
    }


    public List<Foo> fooBetween(LocalDate localDate1 , LocalDate localDate2){
       return fooRepository.getByBirthWithTwoDates(localDate1 , localDate2);

    }



}
