package com.plurasight.DealershipRESTAPI.controller;

import com.plurasight.DealershipRESTAPI.dao.AllContractsDaoImpl;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllContractsController {

    private final AllContractsDaoImpl contractsDao;
    private final Dealership dealership;

    @Autowired
    public AllContractsController(AllContractsDaoImpl contractsDao, Dealership dealership) {
        this.contractsDao = contractsDao;
        this.dealership = dealership;
    }


    @GetMapping("/contracts")
    public List<Contract> getAllContracts() {
        return this.contractsDao.getContracts(this.dealership);
    }
}
