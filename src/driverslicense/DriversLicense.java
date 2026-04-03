
package driverslicense;


import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Represents an Ontario Driver's License with relevant attributes and computations.
 * @author Oladimeji 
 * @version 1.0
 */
public class DriversLicense {

    /** The unique license number (e.g., starts with first letter of last name). */
    private String licenseNumber;

    /** Driver's first name. */
    private String firstName;

    /** Driver's last name. */
    private String lastName;

    /** Street address. */
    private String address;

    /** City of residence. */
    private String city;

    /** Province (usually ON). */
    private String province;

    /** Postal code. */
    private String postalCode;

    /** Date of birth. */
    private LocalDate dob;

    /** Date the license was issued. */
    private LocalDate issueDate;

    /** Date the license expires. */
    private LocalDate expiryDate;

    /** License class (e.g., G, G1, G2). */
    private String licenseClass;

    /** Height in centimeters. */
    private double heightCm;

    /** Eye color. */
    private String eyeColor;

    /** Hair color. */
    private String hairColor;

    /** Sex (M, F, X). */
    private String sex;

    /** Any restrictions or conditions (e.g., "Corrective lenses"). */
    private String restrictions;

    /**
     * No-arg constructor – uses defaults (Gollum-inspired + system date).
     */
    public DriversLicense() {
        this("DEFAULT1234567", "Gollum", "Smeagol", "Under the Mountain", "Mordor", "ON", "A1B 2C3",
             LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now().plusYears(5),
             "G", 70.0, "BLUE", "GREY", "M", "");
    }

    /**
     * Partial parameterized constructor (common fields) – chains to full constructor.
     * @param firstName driver's first name
     * @param lastName driver's last name
     * @param dob date of birth
     * @param licenseClass license class (G, G1, G2, etc.)
     */
    public DriversLicense(String firstName, String lastName, LocalDate dob, String licenseClass) {
        this("DEFAULT1234567", firstName, lastName, "123 Fake St", "Nepean", "ON", "K2H 1B9",
             dob, LocalDate.now(), LocalDate.now().plusYears(5),
             licenseClass, 170.0, "BROWN", "BLACK", "M", "");
    }

    /**
     * Full parameterized constructor – sets all fields.
     * @param licenseNumber license number
     * @param firstName first name
     * @param lastName last name
     * @param address street address
     * @param city city
     * @param province province
     * @param postalCode postal code
     * @param dob date of birth
     * @param issueDate issue date
     * @param expiryDate expiry date
     * @param licenseClass class
     * @param heightCm height in cm
     * @param eyeColor eye color
     * @param hairColor hair color
     * @param sex sex (M/F/X)
     * @param restrictions restrictions/conditions
     */
    public DriversLicense(String licenseNumber, String firstName, String lastName, String address,
                          String city, String province, String postalCode, LocalDate dob,
                          LocalDate issueDate, LocalDate expiryDate, String licenseClass,
                          double heightCm, String eyeColor, String hairColor, String sex,
                          String restrictions) {
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.dob = dob;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.licenseClass = licenseClass;
        this.heightCm = heightCm;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.sex = sex;
        this.restrictions = restrictions;
    }

    /**
     * Computes days remaining until expiry (negative if expired).
     * @return number of days to expiry
     */
    public int computeDaysToExpiry() {
        return (int) ChronoUnit.DAYS.between(LocalDate.now(), expiryDate);
    }

    /**
     * Computes current age in whole years.
     * @return age in years
     */
    public int computeDriverAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    /**
     * Computes days since the license was issued.
     * @return number of days since issue
     */
    public int computeDaysSinceIssue() {
        return (int) ChronoUnit.DAYS.between(issueDate, LocalDate.now());
    }

    /**
     * Computes years since issue (fractional).
     * @return years as double
     */
    public double computeYearsSinceIssue() {
        return ChronoUnit.DAYS.between(issueDate, LocalDate.now()) / 365.25;
    }

    /**
     * Converts height from cm to inches.
     * @return height in inches
     */
    public double computeHeightInInches() {
        return heightCm / 2.54;
    }

    /**
     * Generates a formatted report of all license details and computed values.
     * @return formatted string (table-like)
     */
    public String printLicenseReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================\n");
        sb.append("     ONTARIO DRIVER'S LICENSE REPORT\n");
        sb.append("=====================================\n\n");

        sb.append(String.format("%-20s: %s%n", "License Number", licenseNumber));
        sb.append(String.format("%-20s: %s %s%n", "Name", firstName, lastName));
        sb.append(String.format("%-20s: %s, %s %s%n", "Address", address, city, postalCode));
        sb.append(String.format("%-20s: %s%n", "DOB", dob));
        sb.append(String.format("%-20s: %d years old%n", "Age", computeDriverAge()));
        sb.append(String.format("%-20s: %s%n", "Issue Date", issueDate));
        sb.append(String.format("%-20s: %.2f years ago%n", "Time Since Issue", computeYearsSinceIssue()));
        sb.append(String.format("%-20s: %s%n", "Expiry Date", expiryDate));
        sb.append(String.format("%-20s: %d days left%n", "Days to Expiry", computeDaysToExpiry()));
        sb.append(String.format("%-20s: %s%n", "Class", licenseClass));
        sb.append(String.format("%-20s: %.1f cm (%.1f in)%n", "Height", heightCm, computeHeightInInches()));
        sb.append(String.format("%-20s: %s / %s%n", "Eye / Hair Color", eyeColor, hairColor));
        sb.append(String.format("%-20s: %s%n", "Sex", sex));
        sb.append(String.format("%-20s: %s%n", "Restrictions", restrictions.isEmpty() ? "None" : restrictions));

        sb.append("=====================================\n");
        return sb.toString();
    }
}
