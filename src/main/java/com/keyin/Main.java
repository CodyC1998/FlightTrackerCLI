package com.keyin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.api.ApiClient;
import com.keyin.model.Airport;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===== Flight Tracker CLI =====");
        System.out.println("1. View airports in a city");
        System.out.println("2. View aircraft flown by a passenger");
        System.out.println("3. View airports used by an aircraft");
        System.out.println("4. View airports used by a passenger");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1:
                    System.out.print("Enter City ID: ");
                    int cityId = scanner.nextInt();
                    String response = ApiClient.getAirportsByCity(cityId);

                    ObjectMapper mapper = new ObjectMapper();
                    List<Airport> airports = Arrays.asList(mapper.readValue(response, Airport[].class));

                    if (airports.isEmpty()) {
                        System.out.println("No airports found in this city.");
                    } else {
                        System.out.println("Airports in the city:");
                        for (Airport a : airports) {
                            System.out.println("✈ " + a.getName() + " (" + a.getCode() + ")");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Passenger ID: ");
                    int passengerId = scanner.nextInt();
                    System.out.println(ApiClient.getAircraftByPassenger(passengerId));
                    break;
                case 3:
                    System.out.print("Enter Aircraft ID: ");
                    int aircraftId = scanner.nextInt();
                    System.out.println(ApiClient.getAirportsForAircraft(aircraftId));
                    break;
                case 4:
                    System.out.print("Enter Passenger ID: ");
                    int pid = scanner.nextInt();
                    System.out.println(ApiClient.getAirportsUsedByPassenger(pid));
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}