package com.example.gisassignment1.model;

import com.vividsolutions.jts.geom.Geometry;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "last6")
public class Poi {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "Geometry", nullable = true)
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
