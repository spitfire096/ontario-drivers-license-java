
package driverslicense;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

/**
 * Represents an Ontario Driver's License with relevant attributes and computations.
 * @author Oladimeji 
 * @version 1.0
 */


public class DriversLicenseTest {   

    private DriversLicense license;

    @Before
    public void setUp() {
        // Neutral base license: ~30 years old, issued ~3 years ago, expires in ~2 years
        license = new DriversLicense(
                "TEST123456",
                "Jane",
                "Doe",
                "456 Test Ave",
                "Ottawa",
                "ON",
                "K1P 5G3",
                LocalDate.of(1995, 7, 20),
                LocalDate.now().minusYears(3).minusDays(100),
                LocalDate.now().plusYears(2).plusDays(50),
                "G",
                165.0,
                "GREEN",
                "BROWN",
                "F",
                "Corrective lenses"
        );
    }

   
    // computeDaysToExpiry(): int
    @Test
    public void testDaysToExpiry_NormalFuture() {
        assertTrue(license.computeDaysToExpiry() > 0);
        // assertEquals("Rough check: about 2 years + extra days", 2 * 730 + 50 - 100, license.computeDaysToExpiry());
    }

    @Test
    public void testDaysToExpiry_ExpiresToday() {
        license = new DriversLicense("EXP0", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(40),
                LocalDate.now().minusDays(1825),
                LocalDate.now(),
                "G", 170.0, "BLUE", "BLACK", "M", "");
        assertEquals("Should be exactly 0 on expiry date", 0, license.computeDaysToExpiry());
    }

    @Test
    public void testDaysToExpiry_BoundaryYesterdayTomorrow() {
        // Tomorrow
        license = new DriversLicense("EXP1", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(25),
                LocalDate.now().minusDays(365),
                LocalDate.now().plusDays(1),
                "G", 170.0, "BLUE", "BLACK", "M", "");
        assertEquals("Expiry tomorrow → 1 day", 1, license.computeDaysToExpiry());

        // Yesterday (expired)
        license = new DriversLicense("EXP-1", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(25),
                LocalDate.now().minusDays(365),
                LocalDate.now().minusDays(1),
                "G", 170.0, "BLUE", "BLACK", "M", "");
        assertEquals("Expired yesterday → -1 day", -1, license.computeDaysToExpiry());
    }

    
    //computeDriverAge(): int
    @Test
    public void testDriverAge_Normal() {
        assertEquals("Should be approximately 30", 30, license.computeDriverAge());
    }

    @Test
    public void testDriverAge_BirthdayToday() {
        LocalDate today = LocalDate.now();
        license = new DriversLicense("AGE0", "A", "B", "...", "City", "ON", "123",
                today.minusYears(28),
                today.minusYears(5),
                today.plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals("Birthday today just turned 28", 28, license.computeDriverAge());
    }

    @Test
    public void testDriverAge_BirthdayYesterday_EdgeYoungOld() {
        // Yesterday → already next age
        license = new DriversLicense("AGE1", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(19).minusDays(1),
                LocalDate.now().minusDays(100),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals(19, license.computeDriverAge());

        // Very young edge (just 16)
        license = new DriversLicense("AGE16", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(16).minusDays(10),
                LocalDate.now(),
                LocalDate.now().plusYears(5),
                "G1", 165.0, "", "", "", "");
        assertEquals(16, license.computeDriverAge());
    }

    
    // computeDaysSinceIssue(): int
    @Test
    public void testDaysSinceIssue_Normal() {
        assertTrue(license.computeDaysSinceIssue() > 1000);
    }

   @Test
    public void testDaysSinceIssue_IssuedToday() {
        license = new DriversLicense("ISSUE0", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30),
                LocalDate.now(),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals("Issued today 0 days", 0, license.computeDaysSinceIssue());
    }

    @Test
    public void testDaysSinceIssue_FutureIssue_OneYearAgo() {
        // Unusual: issue date in future (should give negative)
        license = new DriversLicense("ISSUE-FUT", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30),
                LocalDate.now().plusDays(30),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertTrue(license.computeDaysSinceIssue() < 0);

        // Exactly 365 days ago
        license = new DriversLicense("ISSUE365", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30),
                LocalDate.now().minusDays(365),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals(365, license.computeDaysSinceIssue());
    }

    
    // computeYearsSinceIssue(): double
   @Test
    public void testYearsSinceIssue_Normal() {
        double years = license.computeYearsSinceIssue();
        assertTrue(years > 2.5 && years < 3.5);
    }

   @Test
    public void testYearsSinceIssue_IssuedToday() {
        license = new DriversLicense("YRS0", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(25),
                LocalDate.now(),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals(0.0, license.computeYearsSinceIssue(), 0.001);
    }

    @Test
    public void testYearsSinceIssue_Exact5Years_LeapYearApprox() {
        license = new DriversLicense("YRS5", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(20),
                LocalDate.now().minusYears(5),
                LocalDate.now().plusYears(5),
                "G", 170.0, "", "", "", "");
        assertEquals(5.0, license.computeYearsSinceIssue(), 0.05);
    }

    
    // computeHeightInInckes(): double
    @Test
    public void testHeightInches_Normal() {
        assertEquals(165.0 / 2.54, license.computeHeightInInches(), 0.01);
    }

    @Test
    public void testHeightInches_ExtremeValues() {
        license = new DriversLicense("HGT-SHORT", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now().plusYears(5),
                "G", 50.0, "", "", "", "");
        assertEquals(50.0 / 2.54, license.computeHeightInInches(), 0.001);

        license = new DriversLicense("HGT-TALL", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now().plusYears(5),
                "G", 220.0, "", "", "", "");
        assertEquals(220.0 / 2.54, license.computeHeightInInches(), 0.001);
    }

    @Test
    public void testHeightInches_ZeroAndNegative_Unusual() {
        license = new DriversLicense("HGT0", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now().plusYears(5),
                "G", 0.0, "", "", "", "");
        assertEquals(0.0, license.computeHeightInInches(), 0.001);

        // Negative height (invalid but testing method robustness)
        license = new DriversLicense("HGT-NEG", "A", "B", "...", "City", "ON", "123",
                LocalDate.now().minusYears(30), LocalDate.now(), LocalDate.now().plusYears(5),
                "G", -10.0, "", "", "", "");
        assertEquals(-10.0 / 2.54, license.computeHeightInInches(), 0.001);
    }
}
