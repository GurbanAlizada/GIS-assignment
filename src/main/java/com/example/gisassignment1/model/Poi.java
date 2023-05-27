package com.example.gisassignment1.model;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;

@Entity
@Table(name = "poi_table")
public class Poi {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry")
    private Geometry geometry;

    public Poi() {
    }

    public Poi(Long id, Geometry geometry) {
        this.id = id;
        this.geometry = geometry;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
