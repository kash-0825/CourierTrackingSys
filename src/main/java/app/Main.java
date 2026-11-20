package app;

import model.Agent;
import model.Parcel;
import model.StatusUpdate;

import service.ParcelService;
import service.BookingService;
import service.AgentService;
import service.TrackingService;

import exceptions.InvalidParcelException;
import exceptions.NoAgentAvailableException;
import exceptions.InvalidStatusTransitionException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main menu for the Courier and Parcel Tracking System.
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        ParcelService parcelService = new ParcelService();
        BookingService bookingService = new BookingService(parcelService);
        AgentService agentService = new AgentService(parcelService);
        TrackingService trackingService =
                new TrackingService(parcelService, agentService);

        int choice;

        do {
            System.out.println("\n===== COURIER AND PARCEL TRACKING SYSTEM =====");
            System.out.println("1. Book a parcel");
            System.out.println("2. Register a delivery agent");
            System.out.println("3. Assign an agent to a parcel");
            System.out.println("4. Update parcel status");
            System.out.println("5. Track a parcel");
            System.out.println("6. View all parcels");
            System.out.println("7. Cancel a parcel");
            System.out.println("8. View all agents");
            System.out.println("0. Exit");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    bookParcel(bookingService);
                    break;

                case 2:
                    registerAgent(agentService);
                    break;

                case 3:
                    assignAgent(agentService);
                    break;

                case 4:
                    updateStatus(trackingService);
                    break;

                case 5:
                    trackParcel(trackingService);
                    break;

                case 6:
                    viewAllParcels(parcelService);
                    break;

                case 7:
                    cancelParcel(bookingService);
                    break;

                case 8:
                    viewAllAgents(agentService);
                    break;

                case 0:
                    System.out.println("Thank you for using the system.");
                    break;

                default:
                    System.out.println("Invalid menu choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void bookParcel(BookingService bookingService) {
        System.out.print("Enter sender name: ");
        String sender = sc.nextLine();

        System.out.print("Enter receiver name: ");
        String receiver = sc.nextLine();

        double weight = readDouble("Enter parcel weight: ");

        try {
            Parcel parcel = bookingService.bookParcel(sender, receiver, weight);
            System.out.println("Parcel booked successfully!");
            System.out.println("Your parcel ID is: " + parcel.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static void registerAgent(AgentService agentService) {
        System.out.print("Enter agent name: ");
        String name = sc.nextLine();

        System.out.print("Enter agent contact: ");
        String contact = sc.nextLine();

        Agent agent = agentService.registerAgent(name, contact);

        System.out.println("Agent registered successfully!");
        System.out.println("Agent ID: " + agent.getId());
    }

    private static void assignAgent(AgentService agentService) {
        System.out.print("Enter parcel ID: ");
        String parcelId = sc.nextLine();

        try {
            Agent agent = agentService.assignParcel(parcelId);
            System.out.println("Agent assigned successfully!");
            System.out.println("Assigned agent: " + agent.getName());
            System.out.println("Agent ID: " + agent.getId());
        } catch (InvalidParcelException | NoAgentAvailableException e) {
            System.out.println("Assignment failed: " + e.getMessage());
        }
    }

    private static void updateStatus(TrackingService trackingService) {
        System.out.print("Enter parcel ID: ");
        String parcelId = sc.nextLine();

        System.out.println("Available next statuses:");
        System.out.println("1. Picked Up");
        System.out.println("2. In Transit");
        System.out.println("3. Out for Delivery");
        System.out.println("4. Delivered");

        int statusChoice = readInt("Choose the new status: ");

        String newStatus;

        switch (statusChoice) {
            case 1:
                newStatus = "Picked Up";
                break;
            case 2:
                newStatus = "In Transit";
                break;
            case 3:
                newStatus = "Out for Delivery";
                break;
            case 4:
                newStatus = "Delivered";
                break;
            default:
                System.out.println("Invalid status choice.");
                return;
        }

        try {
            trackingService.updateStatus(parcelId, newStatus);
            System.out.println("Parcel status updated successfully!");
        } catch (InvalidParcelException | InvalidStatusTransitionException e) {
            System.out.println("Status update failed: " + e.getMessage());
        }
    }

    private static void trackParcel(TrackingService trackingService) {
        System.out.print("Enter parcel ID: ");
        String parcelId = sc.nextLine();

        try {
            ArrayList<StatusUpdate> history =
                    trackingService.getHistory(parcelId);

            System.out.println("\nTracking History:");
            for (StatusUpdate update : history) {
                System.out.println(update);
            }
        } catch (InvalidParcelException e) {
            System.out.println("Tracking failed: " + e.getMessage());
        }
    }

    private static void viewAllParcels(ParcelService parcelService) {
        ArrayList<Parcel> parcels = parcelService.getAllParcels();

        if (parcels.isEmpty()) {
            System.out.println("No parcels have been booked.");
            return;
        }

        System.out.println("\nAll Parcels:");
        for (Parcel parcel : parcels) {
            System.out.println(parcel);
        }
    }

    private static void cancelParcel(BookingService bookingService) {
        System.out.print("Enter parcel ID to cancel: ");
        String parcelId = sc.nextLine();

        try {
            bookingService.cancelParcel(parcelId);
            System.out.println("Parcel cancelled successfully!");
        } catch (InvalidParcelException e) {
            System.out.println("Cancellation failed: " + e.getMessage());
        }
    }

    private static void viewAllAgents(AgentService agentService) {
        ArrayList<Agent> agents = agentService.getAllAgents();

        if (agents.isEmpty()) {
            System.out.println("No agents have been registered.");
            return;
        }

        System.out.println("\nAll Agents:");
        for (Agent agent : agents) {
            agent.displayInfo();
            System.out.println("--------------------");
        }
    }
}