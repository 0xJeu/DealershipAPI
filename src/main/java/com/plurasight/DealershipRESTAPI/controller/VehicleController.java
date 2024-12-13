package com.plurasight.DealershipRESTAPI.controller;

import com.plurasight.DealershipRESTAPI.models.Dealership;
import com.plurasight.DealershipRESTAPI.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleController {

    private final Dealership dealership;

    @Autowired
    public VehicleController(Dealership dealership) {
        this.dealership = dealership;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return this.dealership.getAllVehicles();
    }

    @GetMapping("/vehicles/search")
    public List<Vehicle> searchVehicles(@RequestParam(required = false) double minPrice,
                                        @RequestParam(required = false) double maxPrice,
                                        @RequestParam(required = false) String make,
                                        @RequestParam(required = false) String model,
                                        @RequestParam(required = false) int minYear,
                                        @RequestParam(required = false) int maxYear,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) int minMiles,
                                        @RequestParam(required = false) int maxMiles,
                                        @RequestParam(required = false) String vehicleType ) {


        if (minPrice != 0 && maxPrice != 0) {
            return this.dealership.getVehicleByPrice(minPrice, maxPrice);
        }

        if (make != null && model != null) {
            return this.dealership.getVehicleByMakeMode(make,model);
        }

        if (minYear != 0 && maxYear != 0) {
            return this.dealership.getVehiclesByYear(minYear, maxYear);
        }

        if (color != null) {
            return this.dealership.getVehicleByColor(color);
        }

        if (minMiles != 0 && maxMiles != 0) {
            return this.dealership.getvehicleByMileage(minMiles, maxMiles);
        }

        if (vehicleType != null) {
            return this.dealership.getVehiclesByVehicleType(vehicleType);
        }

        return List.of();
    }

}
