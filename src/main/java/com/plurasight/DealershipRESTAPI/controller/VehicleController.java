package com.plurasight.DealershipRESTAPI.controller;

import com.plurasight.DealershipRESTAPI.dao.DealerShipDataManager;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import com.plurasight.DealershipRESTAPI.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    private final Dealership dealership;
    private final DealerShipDataManager dealerShipDataManager;

    @Autowired
    public VehicleController(Dealership dealership, DealerShipDataManager dealerShipDataManager) {
        this.dealership = dealership;
        this.dealerShipDataManager = dealerShipDataManager;
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return this.dealership.getAllVehicles();
    }

    @GetMapping("/vehicles/search")
    public List<Vehicle> searchVehicles(@RequestParam(required = false) Double minPrice,
                                        @RequestParam(required = false) Double maxPrice,
                                        @RequestParam(required = false) String make,
                                        @RequestParam(required = false) String model,
                                        @RequestParam(required = false) Integer minYear,
                                        @RequestParam(required = false) Integer maxYear,
                                        @RequestParam(required = false) String color,
                                        @RequestParam(required = false) Integer minMiles,
                                        @RequestParam(required = false) Integer maxMiles,
                                        @RequestParam(required = false) String vehicleType ) {


        if (minPrice != null && maxPrice != null) {
            return this.dealership.getVehicleByPrice(minPrice, maxPrice);
        }

        if (make != null && model != null) {
            return this.dealership.getVehicleByMakeMode(make,model);
        }

        if (minYear != null && maxYear != null) {
            return this.dealership.getVehiclesByYear(minYear, maxYear);
        }

        if (color != null) {
            return this.dealership.getVehicleByColor(color);
        }

        if (minMiles != null && maxMiles != null) {
            return this.dealership.getvehicleByMileage(minMiles, maxMiles);
        }

        if (vehicleType != null) {
            return this.dealership.getVehiclesByVehicleType(vehicleType);
        }

        return List.of();
    }

    @PostMapping("/vehicles/add")
    public boolean addVehicle(@RequestBody Vehicle vehicle) {
        this.dealerShipDataManager.addToInventory(vehicle);
        this.dealership.addVehicle(vehicle);
        return true;
    }

}
