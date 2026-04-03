package driverslicense;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Tester class for DriversLicense – collects user input and displays the license report.
 * 
 * @author Oladimeji 
 * @version 1.0
 */
public class Tester {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Ontario Driver's License Entry ===");

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Date of Birth (YYYY-MM-DD): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine().trim());

        System.out.print("License Class (e.g. G, G1, G2): ");
        String licenseClass = scanner.nextLine().trim();

        // You can add more prompts later if you want full input
        // For now using partial constructor + defaults for rest

        DriversLicense license = new DriversLicense(firstName, lastName, dob, licenseClass);

        System.out.println("\nGenerated License Report:\n");
        System.out.println(license.printLicenseReport());

        scanner.close();
    }
}
