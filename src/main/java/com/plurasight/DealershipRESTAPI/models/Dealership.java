package com.plurasight.DealershipRESTAPI.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Dealership {
    private String name, address, phone;

    static ArrayList<Vehicle> inventory = new ArrayList<>();
    static ArrayList<Contract> contracts = new ArrayList<>();

    public Dealership() {
        this.name = "D & B Used Cars";
        this.address = "111 Old Benbrook Rd";
        this.phone = "817-555-5555";
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getVehicleByPrice(double min, double max) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            double price = vehicle.getPrice();

            if (price >= min && price <= max) {
                filteredInventory.add(vehicle);
            }
        }
        System.out.println("Found  " + filteredInventory.size() + " vehicle(s) in the price range\n");
        return filteredInventory;
    }

    public List<Vehicle> getVehicleByMakeMode(String make, String model) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (make.equalsIgnoreCase(vehicle.getMake()) && model.equalsIgnoreCase(vehicle.getModel())) {
                filteredInventory.add(vehicle);
            }
        }
        System.out.println("Found  " + filteredInventory.size() + " vehicle(s) with the make and model requested\n");
        return filteredInventory;
    }

    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int year = vehicle.getYear();

            if (year >= minYear && year <= maxYear) {
                filteredInventory.add(vehicle);
            }
        }
        System.out.println("Found  " + filteredInventory.size() + " vehicle(s) in the year range\n");
        return filteredInventory;
    }

    public List<Vehicle> getVehicleByColor(String color) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (color.equalsIgnoreCase(vehicle.getColor())) {
                filteredInventory.add(vehicle);
            }
        }
        System.out.println("Found " + filteredInventory.size() + " vehicle(s) with the color requested\n");

        return filteredInventory;
    }

    public List<Vehicle> getvehicleByMileage(int minMileage, int maxMileage) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            int mileage = vehicle.getOdometer();
            if (mileage >= minMileage && mileage <= maxMileage) {
                filteredInventory.add(vehicle);
            }
        }
        System.out.println("Found " + filteredInventory.size() + " vehicle(s) with the mileage range requested\n");
        return filteredInventory;
    }

    public List<Vehicle> getVehiclesByVehicleType(String vehicleType) {
        List<Vehicle> filteredInventory = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicleType.equalsIgnoreCase(vehicle.getVehicleType())) {
                filteredInventory.add(vehicle);
            }
        }

        System.out.println("Found " + filteredInventory.size() + " vehicle(s) in the vehicle type request\n");

        return filteredInventory;
    }


    public List<Vehicle> getAllVehicles() {
        return inventory;
    }


    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }


    public List<Contract> getContracts() {
        return contracts;
    }

    @Override
    public String toString() {
        return name + "|" +
               address + '|' +
               phone;
    }
}

