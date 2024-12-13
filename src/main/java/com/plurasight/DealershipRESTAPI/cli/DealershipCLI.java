package com.plurasight.DealershipRESTAPI.cli;

import com.plurasight.DealershipRESTAPI.cli.menu.UserInterface;
import com.plurasight.DealershipRESTAPI.models.Dealership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DealershipCLI implements CommandLineRunner {
    static final Scanner keyboard = new Scanner(System.in);

    private final Dealership dealership;
    private final UserInterface userInterface;

    @Autowired
    public DealershipCLI(Dealership dealership, UserInterface userInterface) {
        this.dealership = dealership;
        this.userInterface = userInterface;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.printf("Welcome to %s's Auto Shop! \n", dealership.getName());
            UserInterface.display();
            int userInput = Integer.parseInt(keyboard.nextLine());
            System.out.println("-----------");

            switch (userInput) {
                case 1:
                    userInterface.processGetByPriceRequest();
                    System.out.println("-----------");
                    break;
                case 2:
                    userInterface.processGetByMakeModelRequest();
                    System.out.println("-----------");
                    break;
                case 3:
                    userInterface.processGetByYearRequest();
                    System.out.println("-----------");
                    break;
                case 4:
                    userInterface.processGetByColorRequest();
                    System.out.println("-----------");
                    break;
                case 5:
                    userInterface.processGetByMileageRequest();
                    System.out.println("-----------");
                    break;
                case 6:
                    userInterface.processGetByVehicleTypeRequest();
                    System.out.println("-----------");
                    break;
                case 7:
                    userInterface.processGetAllVehiclesRequest();
                    System.out.println("-----------");
                    break;
                case 8:
                    userInterface.processAddVehicleRequest();
                    System.out.println("-----------");
                    break;
                case 9:
                    userInterface.processRemoveVehicleRequest();
                    System.out.println("-----------");
                    break;
                case 10:
                    userInterface.contractScreen();
                    System.out.println("-----------");
                    break;
                case 99:
                    System.out.println("Thank you for visiting!");
                    System.exit(0);
            }
        }

    }
}

