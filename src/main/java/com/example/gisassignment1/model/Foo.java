package com.example.gisassignment1.model;

import io.swagger.v3.oas.annotations.tags.Tags;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table
public class Foo {


    @Id
    @GeneratedValue
    int id;

    private String name;


    private LocalDateTime birth;


    public Foo(int id, String name, LocalDateTime birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public Foo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }
}
