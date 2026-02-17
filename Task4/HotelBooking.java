import java.io.*;
import java.util.*;

// ===== Room Class =====
class Room {
    int roomNumber;
    String type;
    double price;
    boolean available;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = true;
    }
}

// ===== Booking Class =====
class Booking {
    String customerName;
    int roomNumber;
    String roomType;
    double amount;

    Booking(String customerName, int roomNumber, String roomType, double amount) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.amount = amount;
    }
}

// ===== Payment Class =====
class Payment {
    static boolean makePayment(double amount) {
        System.out.println("Processing payment of ₹" + amount + "...");
        System.out.println("Payment Successful ✅");
        return true;
    }
}

// ===== Hotel Class =====
class Hotel {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    Hotel() {
        rooms.add(new Room(101, "Standard", 2000));
        rooms.add(new Room(102, "Standard", 2000));
        rooms.add(new Room(201, "Deluxe", 3500));
        rooms.add(new Room(301, "Suite", 5000));
    }

    void showAvailableRooms() {
        System.out.println("\n🏨 Available Rooms:");
        for (Room r : rooms) {
            if (r.available) {
                System.out.println(r.roomNumber + " | " + r.type + " | ₹" + r.price);
            }
        }
    }

    void bookRoom(String name, String type) {
        for (Room r : rooms) {
            if (r.available && r.type.equalsIgnoreCase(type)) {
                if (Payment.makePayment(r.price)) {
                    r.available = false;
                    Booking b = new Booking(name, r.roomNumber, r.type, r.price);
                    bookings.add(b);
                    saveBooking(b);
                    System.out.println("✅ Room Booked Successfully!");
                    return;
                }
            }
        }
        System.out.println("❌ No rooms available in this category");
    }

    void cancelBooking(int roomNumber) {
        for (Room r : rooms) {
            if (r.roomNumber == roomNumber) {
                r.available = true;
                System.out.println("✅ Booking Cancelled");
                return;
            }
        }
        System.out.println("❌ Room not found");
    }

    void viewBookings() {
        System.out.println("\n📋 Booking Details:");
        for (Booking b : bookings) {
            System.out.println("Name: " + b.customerName +
                    ", Room: " + b.roomNumber +
                    ", Type: " + b.roomType +
                    ", Paid: ₹" + b.amount);
        }
    }

    void saveBooking(Booking b) {
        try {
            FileWriter fw = new FileWriter("bookings.txt", true);
            fw.write(b.customerName + "," + b.roomNumber + "," +
                    b.roomType + "," + b.amount + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("File Error");
        }
    }
}

// ===== Main Class =====
public class HotelBooking{
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int choice;

        do {
            System.out.println("\n===== HOTEL BOOKING MENU =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    hotel.showAvailableRooms();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Room Type (Standard/Deluxe/Suite): ");
                    String type = sc.nextLine();
                    hotel.bookRoom(name, type);
                    break;

                case 3:
                    System.out.print("Enter Room Number to Cancel: ");
                    int roomNo = sc.nextInt();
                    hotel.cancelBooking(roomNo);
                    break;

                case 4:
                    hotel.viewBookings();
                    break;

                case 5:
                    System.out.println("Thank you for using Hotel Booking System 🏨");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
