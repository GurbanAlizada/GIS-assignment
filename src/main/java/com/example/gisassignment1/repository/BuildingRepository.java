package com.example.gisassignment1.repository;

import com.example.gisassignment1.model.Building;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    Building findByIndex(Long index);

    @Query(nativeQuery = true , value = "SELECT COUNT(*) AS count " +
        "FROM buildings2 " +
        "WHERE ST_Equals(geometry, :geometry) = TRUE; ")
    int checkIsExistsTheSameBuildings(Geometry geometry);



}
