package infotainmentSytemPackage.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import infotainmentSytemPackage.DTC;
import infotainmentSytemPackage.StatusCode;

class DTCTest {

	@Test
	// Tests if checkDTC() returns false when there are no DTCs in the map.
	public void testCheckDTC_NoDTCs() {
		DTC dtc = new DTC();
		assertFalse(dtc.checkDTC());
	}

	@Test
	// Tests if checkDTC() returns false when there are DTCs with values other than
	// 9.
	public void testCheckDTC_NoFailures() {
		DTC dtc = new DTC();
		dtc.setDTC("DTC1", StatusCode.CONFIRMED);
		dtc.setDTC("DTC2", StatusCode.NO_ISSUE);
		assertFalse(dtc.checkDTC());
	}

	@Test
	// Tests if checkDTC() returns true when there is at least one DTC with a value
	// of 9.
	// there is a failure here because of the code itself at line42 in DTC.java file
	public void testCheckDTC_WithFailures() {
		DTC dtc = new DTC();
		dtc.setDTC("DTC1", StatusCode.CONFIRMED_AND_FAILED);
		dtc.setDTC("DTC2", StatusCode.NO_ISSUE);
		assertTrue(dtc.checkDTC());
	}

	@Test
	// Tests if displayDTC() returns an empty string when there are no DTCs in the
	// map.
	public void testDisplayDTC_NoDTCs() {
		DTC dtc = new DTC();
		assertEquals("", dtc.displayDTC());
	}

	@Test
	// Tests if displayDTC() returns the expected string
	// representation when there are DTCs with various values.
	public void testDisplayDTC_WithDTCs() {
		DTC dtc = new DTC();
		dtc.setDTC("DTC1", StatusCode.CONFIRMED);
		dtc.setDTC("DTC2", StatusCode.NO_ISSUE);
		String expected = "DTC1: 0x8\nDTC2: 0x0\n";
		assertEquals(expected, dtc.displayDTC());
	}

	@Test
	// Tests if resetDTC() keeps flash as false and flashToogleTime
	// remains unchanged when there are no DTCs.
	public void testResetDTC_NoDTCs() {
		DTC dtc = new DTC();
		dtc.resetDTC();
		assertFalse(dtc.getFlash());
		assertEquals(500, dtc.getFlashToogleTime());

	}

	@Test
	// Tests if resetDTC() sets flash to false and flashToogleTime
	// remains unchanged when there are DTCs present.
	// Error DTC.java line 65 it should be reset to NO_ISSUE 
	public void testResetDTC_WithDTCs() {
		DTC dtc = new DTC();
		dtc.setDTC("DTC1", StatusCode.CONFIRMED);
		dtc.setDTC("DTC2", StatusCode.CONFIRMED_AND_FAILED);
		
		dtc.resetDTC();
		
		assertFalse(dtc.getFlash());
		assertEquals(500, dtc.getFlashToogleTime());
		assertEquals(StatusCode.NO_ISSUE, dtc.getDTC("DTC1"));
		assertEquals(StatusCode.NO_ISSUE, dtc.getDTC("DTC2"));
		

	}

	@Test
	// Tests if getDTC(String dtc) returns the correct StatusCode for a given DTC.
	void testGetDTC() {
		// Test case for getDTC(String dtc)
		DTC dtc = new DTC();
		dtc.setDTC("DTC1", StatusCode.CONFIRMED);
		assertEquals(StatusCode.CONFIRMED, dtc.getDTC("DTC1"));
	}

}
