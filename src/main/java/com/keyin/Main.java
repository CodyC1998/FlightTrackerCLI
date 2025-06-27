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
        ApiClient client = new ApiClient();

        while (true) {
            System.out.println("\n===== Flight Tracker CLI =====");
            System.out.println("1. View airports in a city");
            System.out.println("2. View aircraft flown by a passenger");
            System.out.println("3. View airports used by an aircraft");
            System.out.println("4. View airports used by a passenger");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter City ID: ");
                        int cityId = Integer.parseInt(scanner.nextLine());
                        String cityJson = client.getAirportsByCity(cityId);
                        List<Airport> airports = Arrays.asList(
                                mapper.readValue(cityJson, Airport[].class)
                        );
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
                        int passengerId = Integer.parseInt(scanner.nextLine());
                        String aircraftJson = client.getAircraftByPassenger(passengerId);
                        List<Aircraft> aircraft = Arrays.asList(
                                mapper.readValue(aircraftJson, Aircraft[].class)
                        );
                        if (aircraft.isEmpty()) {
                            System.out.println("This passenger has not flown on any aircraft.");
                        } else {
                            System.out.println("Aircraft flown by this passenger:");
                            for (Aircraft a : aircraft) {
                                System.out.println("✈ " + a.getType() + " - " + a.getAirlineName());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Aircraft ID: ");
                        int aircraftId = Integer.parseInt(scanner.nextLine());
                        String airportsForJson = client.getAirportsForAircraft(aircraftId);
                        List<Airport> usedByAircraft = Arrays.asList(
                                mapper.readValue(airportsForJson, Airport[].class)
                        );
                        if (usedByAircraft.isEmpty()) {
                            System.out.println("This aircraft has not used any airports.");
                        } else {
                            System.out.println("Airports used by this aircraft:");
                            for (Airport a : usedByAircraft) {
                                System.out.println("🛫 " + a.getName() + " (" + a.getCode() + ")");
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter Passenger ID: ");
                        int pid = Integer.parseInt(scanner.nextLine());
                        String airportsUsedJson = client.getAirportsUsedByPassenger(pid);
                        List<Airport> airportsUsed = Arrays.asList(
                                mapper.readValue(airportsUsedJson, Airport[].class)
                        );
                        if (airportsUsed.isEmpty()) {
                            System.out.println("This passenger has not used any airports.");
                        } else {
                            System.out.println("Airports this passenger has used:");
                            for (Airport a : airportsUsed) {
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