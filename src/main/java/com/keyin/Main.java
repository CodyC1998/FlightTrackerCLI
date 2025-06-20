package com.keyin;

import com.keyin.api.ApiClient;

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
                    System.out.println(ApiClient.getAirportsByCity(cityId));
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