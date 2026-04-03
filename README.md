# Ontario Driver's License System

![Java](https://img.shields.io/badge/Java-8+-007396?style=for-the-badge&logo=java&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-4-25A162?style=for-the-badge&logo=junit5&logoColor=white)

A clean and well-structured **Object-Oriented Java** project that models a realistic Ontario Driver's License.  

## Features

- **16 encapsulated attributes** based on real Ontario driver's license data
- Proper **constructor chaining** (No-arg → Partial → Full)
- Dynamic date handling using `java.time` API (age, expiry, issuance calculations)
- Height conversion (cm → inches)
- Formatted license report generation
- Interactive console input with `Scanner`
- Comprehensive **JUnit 4** testing with 18+ assertions covering normal and boundary cases

## Technologies

- **Java 8+**
- **JUnit 4**
- **Javadoc**

## Project Structure

```bash
ON-drivers-license-java/
├── src/
│   └── driverslicense/
│       ├── DriversLicense.java
│       ├── Tester.java
│       └── DriversLicenseTest.java
├── README.md
├── .gitignore
└── screenshots/

## How to Run
Eclipse

Import the project
Right-click Tester.java → Run As → Java Application

Command Line
Bash
# Compile
javac -d bin src/driverslicense/*.java

# Run
java -cp bin driverslicense.Tester

## Testing
Includes a full JUnit 4 test suite with strong boundary coverage:

License expiring today, tomorrow, and yesterday
Age edge cases
Height conversion (extreme, zero, negative values)
Issue date variations

Run tests via: Right-click DriversLicenseTest.java → Run As → JUnit Test

## Screenshots

## Program Running
<img src="screenshots/program-run.png" alt="Program Output">

## JUnit Results
<img src="screenshots/junit-green-bar.png" alt="JUnit Green Bar">

## Javadoc
<img src="screenshots/javadoc-sample.png" alt="Javadoc Sample">

## Author
Oladimeji Durojaiye
