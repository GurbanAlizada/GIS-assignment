package com.example.gisassignment1.model;

import com.vividsolutions.jts.geom.Geometry;
import javax.persistence.*;

@Entity
@Table(name = "buildings2")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long index;

    @Column(columnDefinition = "Geometry")
    private Geometry geometry;

    public Building() {
    }

    public Building(Long id, Long index, Geometry geometry) {
        this.id = id;
        this.index = index;
        this.geometry = geometry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }




}
