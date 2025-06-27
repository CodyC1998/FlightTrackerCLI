package com.keyin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.api.ApiClient;
import com.keyin.model.Airport;
import com.keyin.model.Aircraft;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();

        while (true) {
            System.out.println("\n===== Flight Tracker CLI =====");
            System.out.println("1. View airports in a city");
            System.out.println("2. View aircraft flown by a passenger");
            System.out.println("3. View airports used by an aircraft");
            System.out.println("4. View airports used by a passenger");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter City ID: ");
                        int cityId = scanner.nextInt();
                        String response = ApiClient.getAirportsByCity(cityId);
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
                        String aircraftResponse = ApiClient.getAircraftByPassenger(passengerId);
                        List<Aircraft> aircraftList = Arrays.asList(mapper.readValue(aircraftResponse, Aircraft[].class));
                        if (aircraftList.isEmpty()) {
                            System.out.println("This passenger has not flown on any aircraft.");
                        } else {
                            System.out.println("Aircraft flown by this passenger:");
                            for (Aircraft a : aircraftList) {
                                System.out.println("✈ " + a.getType() + " - " + a.getAirlineName());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Aircraft ID: ");
                        int aircraftId = scanner.nextInt();
                        String airportResponse = ApiClient.getAirportsForAircraft(aircraftId);
                        List<Airport> usedAirports = Arrays.asList(mapper.readValue(airportResponse, Airport[].class));
                        if (usedAirports.isEmpty()) {
                            System.out.println("This aircraft has not used any airports.");
                        } else {
                            System.out.println("Airports used by this aircraft:");
                            for (Airport a : usedAirports) {
                                System.out.println("🛫 " + a.getName() + " (" + a.getCode() + ")");
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Passenger ID: ");
                        int pid = scanner.nextInt();
                        String usedResponse = ApiClient.getAirportsUsedByPassenger(pid);
                        List<Airport> used = Arrays.asList(mapper.readValue(usedResponse, Airport[].class));
                        if (used.isEmpty()) {
                            System.out.println("This passenger has not used any airports.");
                        } else {
                            System.out.println("Airports this passenger has used:");
                            for (Airport a : used) {
                                System.out.println("🛬 " + a.getName() + " (" + a.getCode() + ")");
                            }
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}