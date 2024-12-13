package com.plurasight.DealershipRESTAPI.dao;

import com.plurasight.DealershipRESTAPI.dao.interfaces.ContractsDAO;
import com.plurasight.DealershipRESTAPI.dao.interfaces.SaleContractDAO;
import com.plurasight.DealershipRESTAPI.models.Contract;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import com.plurasight.DealershipRESTAPI.models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SaleContractDaoImpl implements SaleContractDAO, ContractsDAO {

    private final DataSource dataSource;
    private final List<Contract> contractList;

    @Autowired
    public SaleContractDaoImpl(DataSource dataSource, List<Contract> contractList) {
        this.dataSource = dataSource;
        this.contractList = contractList;
    }

    @Override
    public List<Contract> getContracts(Dealership dealership) {
        //Try block handles sales_contract table
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
                     SELECT * FROM sales_contracts""");
        ) {

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    String date = results.getString("date");
                    String customerName = results.getString("CustomerName");
                    int vin = results.getInt("VIN");
                    String email = results.getString("Email");
                    double totalPrice = results.getDouble("TotalPrice");
                    double monthlyPayment = results.getDouble("MonthlyPayment");
                    double salesTax = results.getDouble("SalesTaxAmount");
                    int recordingFee = results.getInt("RecordingFee");
                    int processingFee = results.getInt("ProcessingFee");
                    boolean financing = results.getBoolean("WantToFinance");

                    Sale sale = new Sale(date,
                            customerName,
                            vin,
                            email,
                            true,
                            totalPrice,
                            monthlyPayment,
                            salesTax,
                            recordingFee,
                            processingFee,
                            financing);
                    dealership.addContract(sale);
                    this.contractList.add(sale);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.contractList;
    }

    @Override
    public void saveContract(Contract contract) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("""
                      INSERT INTO sales_contracts (date, VIN, CustomerName, Email, VehicleSold, TotalPrice,\s
                      MonthlyPayment, SalesTaxAmount, RecordingFee, ProcessingFee, WantToFinance )
                      VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                     \s""", PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, contract.getDate());
            preparedStatement.setInt(2, ((Sale)contract).getVIN());
            preparedStatement.setString(3, contract.getCustomerName());
            preparedStatement.setString(4, contract.getEmail());
            preparedStatement.setBoolean(5, contract.isVehicleSold());
            preparedStatement.setDouble(6, contract.getTotalPrice());
            preparedStatement.setDouble(7, contract.getMonthlyPayment());
            preparedStatement.setDouble(8, ((Sale) contract).getSalesTaxAmount());
            preparedStatement.setInt(9, ((Sale) contract).getRecordingFee());
            preparedStatement.setInt(10, ((Sale) contract).getProcessingFee());
            preparedStatement.setBoolean(11, ((Sale) contract).isWantToFinance());

            int rows = preparedStatement.executeUpdate();

            System.out.printf("Rows updated: %d\n", rows);

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                while (keys.next()) {
                    System.out.printf("%d key was added\n", keys.getInt(1));
                }

            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

