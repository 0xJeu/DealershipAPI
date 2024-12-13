package com.plurasight.DealershipRESTAPI.controller;

import com.plurasight.DealershipRESTAPI.dao.interfaces.LeaseContractDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.SaleContractDAO;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaseContractController {

    private final LeaseContractDAO contractDAO;
    private final Dealership dealership;

    @Autowired
    public LeaseContractController(LeaseContractDAO contractDAO, Dealership dealership) {
        this.contractDAO = contractDAO;
        this.dealership = dealership;
    }


    @GetMapping("/contracts/lease")
    public List<Contract> getAllLeaseContracts() {
        return this.contractDAO.getContracts(dealership);
    }

}
