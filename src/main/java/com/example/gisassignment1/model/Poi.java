package com.example.gisassignment1.model;

import com.vividsolutions.jts.geom.Geometry;

import javax.persistence.*;

@Entity
@Table(name = "pois")
public class Poi {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry" , name = "geometry")
    private Geometry point;

    public Poi() {
    }

    public Poi(Long id, Geometry point) {
        this.id = id;
        this.point = point;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return point;
    }

    public void setGeometry(Geometry geometry) {
        this.point = geometry;
    }
}
