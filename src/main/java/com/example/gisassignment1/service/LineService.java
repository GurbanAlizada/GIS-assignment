package com.example.gisassignment1.service;


import com.example.gisassignment1.util.GeometryHelper;
import com.example.gisassignment1.model.Line;
import com.example.gisassignment1.repository.LineRepository;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;

@Service
public class LineService {


    private final LineRepository lineRepository;


    public LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    @Transactional
    public void save(FeatureCollection featureCollection) {
        Feature[] features = featureCollection.getFeatures();

        for (Feature element : features){
            Line line = convertFeatureToEntity(element);
            if (lineRepository.checkIntersects(line.getGeometry()) == 0){
                lineRepository.save(line);
            }
        }
    }


    private Line convertFeatureToEntity(Feature feature) {
        Line entity = new Line();
        entity.setGeometry(GeometryHelper.convertGeoJsonToJtsGeometry(feature.getGeometry()));
        return entity;
    }



//    private boolean checkIntersects(Line line){
//        List<Line> linesFromDb = lineRepository.findAll();
//        Geometry geometry = line.getGeometry();
//        for (Line element : linesFromDb){
//            if (geometry.intersects(element.getGeometry())){
//                return true;
//            }
//        }
//        return false;
//    }





}
