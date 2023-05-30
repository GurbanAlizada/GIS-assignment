package com.example.gisassignment1.dto.request;

import org.wololo.geojson.Feature;

import javax.validation.constraints.NotNull;

public record UpdateBuildingRequest(
        @NotNull
        Feature feature,
        @NotNull
        Long id

) {
}
