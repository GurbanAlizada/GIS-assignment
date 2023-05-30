package com.example.gisassignment1.repository;

import com.example.gisassignment1.model.Line;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LineRepository extends JpaRepository<Line , Long> {


    @Query(nativeQuery = true , value = "SELECT COUNT(*) AS count " +
            "FROM lines2 " +
            "WHERE ST_Intersects(geometry, :geometry) = TRUE; ")
    int checkIntersects(Geometry geometry);


}
