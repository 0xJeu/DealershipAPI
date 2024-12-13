package com.plurasight.DealershipRESTAPI.dao;

import com.plurasight.DealershipRESTAPI.dao.interfaces.ContractsDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.LeaseContractDAO;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import com.plurasight.DealershipRESTAPI.models.Lease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaseContractDaoImpl implements LeaseContractDAO, ContractsDAO {

    private final DataSource dataSource;
    private final List<Contract> contractList;

    @Autowired
    public LeaseContractDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.contractList = new ArrayList<>();

    }

    @Override
    public List<Contract> getContracts(Dealership dealership) {
        this.contractList.clear();
        try (Connection connection = dataSource.getConnection()) {

            //Try block handles lease_contract table
            try (PreparedStatement preparedStatement = connection.prepareStatement("""
                    SELECT * FROM lease_contracts""");
                 ResultSet results = preparedStatement.executeQuery()) {

                while (results.next()) {
                    String date = results.getString("date");
                    int vin = results.getInt("VIN");
                    String customerName = results.getString("CustomerName");
                    String email = results.getString("Email");
                    double totalPrice = results.getDouble("TotalPrice");
                    double monthlyPayment = results.getDouble("MonthlyPayment");
                    double expectedEndingValue = results.getDouble("ExpectedEndingValue");
                    double leaseFee = results.getDouble("LeaseFee");

                    Lease lease = new Lease(
                            date,
                            customerName,
                            vin,
                            email,
                            false,
                            totalPrice,
                            monthlyPayment,
                            expectedEndingValue,
                            leaseFee);
                    dealership.addContract(lease);
                    this.contractList.add(lease);
                }

            } catch (SQLException e) {
                throw new SQLException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.contractList;
    }

    @Override
    public void saveContract(Contract contract) {

        try (Connection connection = dataSource.getConnection()) {

                try (PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO lease_contracts (date, VIN, CustomerName, Email, VehicleSold, TotalPrice, MonthlyPayment,
                        ExpectedEndingValue, LeaseFee)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """, PreparedStatement.RETURN_GENERATED_KEYS)) {

                    preparedStatement.setString(1, contract.getDate());
                    preparedStatement.setInt(2,((Lease)contract).getVIN());
                    preparedStatement.setString(3, contract.getCustomerName());
                    preparedStatement.setString(4, contract.getEmail());
                    preparedStatement.setBoolean(5, contract.isVehicleSold());
                    preparedStatement.setDouble(6, contract.getTotalPrice());
                    preparedStatement.setDouble(7, contract.getMonthlyPayment());
                    preparedStatement.setDouble(8, ((Lease) contract).getExpectedEndingValue());
                    preparedStatement.setDouble(9, ((Lease) contract).getLeaseFee());

                    int rows = preparedStatement.executeUpdate();

                    System.out.printf("Rows updated: %d\n", rows);

                    try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                        while (keys.next()) {
                            System.out.printf("%d key was added\n", keys.getInt(1));
                        }

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}

