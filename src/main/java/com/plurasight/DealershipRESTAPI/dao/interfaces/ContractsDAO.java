package com.plurasight.DealershipRESTAPI.dao.interfaces;

import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;

import java.util.List;

public interface ContractsDAO {

    public List<Contract> getContracts(Dealership dealership);


}
