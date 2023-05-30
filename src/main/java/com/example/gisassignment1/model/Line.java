package com.example.gisassignment1.model;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;


@Entity
@Table(name = "lines2")
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry")
    private Geometry geometry;


    public Line() {
    }

    public Line(Long id, Geometry geometry) {
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
