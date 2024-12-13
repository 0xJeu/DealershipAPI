package com.plurasight.DealershipRESTAPI.dao;

import com.plurasight.DealershipRESTAPI.dao.interfaces.ContractsDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.LeaseContractDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.SaleContractDAO;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllContractsDaoImpl implements ContractsDAO {


    private final LeaseContractDAO leaseContractDAO;
    private final SaleContractDAO saleContractDAO;

    @Autowired
    public AllContractsDaoImpl(LeaseContractDAO leaseContractDAO, SaleContractDAO saleContractDAO) {
        this.leaseContractDAO = leaseContractDAO;
        this.saleContractDAO = saleContractDAO;

    }

    @Override
    public List<Contract> getContracts(Dealership dealership) {
        List<Contract> contracts = new ArrayList<>();

        contracts.addAll(this.leaseContractDAO.getContracts(dealership));
        contracts.addAll(this.saleContractDAO.getContracts(dealership));

        return contracts;
    }
}
