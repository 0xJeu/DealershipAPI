package com.plurasight.DealershipRESTAPI.controller;

import com.plurasight.DealershipRESTAPI.dao.interfaces.LeaseContractDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.SaleContractDAO;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleContractController {

    private final SaleContractDAO contractDAO;
    private final Dealership dealership;

    @Autowired
    public SaleContractController(SaleContractDAO contractDAO, Dealership dealership) {
        this.contractDAO = contractDAO;
        this.dealership = dealership;
    }

   @GetMapping("/contracts/sales")
    public List<Contract> getAllContracts() {
        return this.contractDAO.getContracts(this.dealership);
    }

    @PostMapping("/contracts/sales")
    public boolean saveSaleContract(@RequestBody Contract contract) {
        return false;
    }

}
