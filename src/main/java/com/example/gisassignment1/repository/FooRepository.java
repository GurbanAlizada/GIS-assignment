package com.example.gisassignment1.repository;

import com.example.gisassignment1.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FooRepository extends JpaRepository<Foo ,Integer > {


    @Query("select f from Foo f where function('DATE' , f.birth) = :date  ")
    List<Foo> getByBirth(LocalDate date);



    @Query( "SELECT f FROM Foo f WHERE function('DATE' , f.birth)>= :localDate1 and function('DATE' , f.birth)<= :localDate2")
    List<Foo> getByBirthWithTwoDates(LocalDate localDate1 , LocalDate localDate2);






}
