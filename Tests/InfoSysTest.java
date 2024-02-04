package infotainmentSytemPackage.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import infotainmentSytemPackage.BackLightMode;
import infotainmentSytemPackage.DTC;
import infotainmentSytemPackage.Ign;
import infotainmentSytemPackage.InfoSys;
import infotainmentSytemPackage.Mute;
import infotainmentSytemPackage.OnOff;
import infotainmentSytemPackage.StatusCode;
import infotainmentSytemPackage.Sys;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import javax.swing.ImageIcon;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class InfoSysTest {
	private InfoSys infoSys;

	@BeforeEach
	public void setUp() {
		infoSys = new InfoSys();
	}

	// TC1: Test for the DisableSystem() method
	// Expected output: All components should be disabled, Ignite should be OFF and
	// colored red
	@Order(1)
	@DisplayName("TC1: DisableSystem")
	@Test
	public void testDisableSystem() {
		infoSys.DisableSystem();
		assertFalse(infoSys.btnPower.isEnabled());
		assertEquals(Color.black, infoSys.lblInfoSys.getBackground());
		assertFalse(infoSys.lblMute.isVisible());
		assertEquals(Color.red, infoSys.btnIgnite.getForeground());
		assertFalse(infoSys.rdbtnDay.isEnabled());
		assertFalse(infoSys.rdbtnNight.isEnabled());
		assertEquals("Ignite: OFF", infoSys.btnIgnite.getText());
	}

	// TC2: Test for the EnableSystem() method
	// Expected output: All components should be enabled, Ignite should be ON and
	// colored green
	@Order(2)
	@DisplayName("TC2: EnableSystem")
	@Test
	public void testEnableSystem() {
		infoSys.EnableSystem();
		assertTrue(infoSys.btnPower.isEnabled());
		assertTrue(infoSys.lblMute.isVisible());
		assertEquals(Color.green, infoSys.btnIgnite.getForeground());
		assertTrue(infoSys.rdbtnDay.isEnabled());
		assertTrue(infoSys.rdbtnNight.isEnabled());
		assertEquals(Color.white, infoSys.lblInfoSys.getBackground()); //assume white background when enabling system
		assertEquals("Ignite: ON", infoSys.btnIgnite.getText());
	} 

	// Test Case 3: System is OFF, Ignite is ON
	// Expected: IGN ON, SYS OFF
	@Order(3)
	@DisplayName("TC3: testToggleIgniteOnOff_SystemOff_IgniteOn")
	@Test
	public void testToggleIgniteOnOff_SystemOff_IgniteOn() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.igniteStatus = Ign.ON;
		
	    infoSys.ToggleIgniteOnOff();
	    
	    assertEquals(Ign.ON, infoSys.igniteStatus);
	    assertEquals(Sys.OFF, infoSys.systemStatus);
	    testDisableSystem();
	}

	// Test Case 4: System is OFF, Ignite is OFF
	// Expected: System turns ON, Ignite turns ON
	@Order(4)
	@DisplayName("TC4: testToggleIgniteOnOff_SystemOff_IgniteOff")
	@Test
	public void testToggleIgniteOnOff_SystemOff_IgniteOff() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.igniteStatus = Ign.OFF;
		
		infoSys.ToggleIgniteOnOff();
		
	    assertEquals(Sys.OFF, infoSys.systemStatus);
	    assertEquals(Ign.OFF, infoSys.igniteStatus);
	    testEnableSystem();
	}

	// Test Case 5: System is ON, Ignite is ON
	// Expected: System turns OFF, Ignite OFF
	@Order(5)
	@DisplayName("TC5: testToggleIgniteOnOff_SystemOn_IgniteOn")
	@Test
	public void testToggleIgniteOnOff_SystemOn_IgniteOn() {
		infoSys.systemStatus = Sys.ON;
		infoSys.igniteStatus = Ign.ON;
		
	    infoSys.ToggleIgniteOnOff();
	    
	    assertEquals(Sys.OFF, infoSys.systemStatus);
	    assertEquals(Ign.OFF, infoSys.igniteStatus);
	    testDisableSystem();
	}

	// Test Case 4: System is ON, Ignite is OFF
	// Expected: System OFF, Ignite turns ON
	@Order(6)
	@DisplayName("TC6: testToggleIgniteOnOff_SystemOn_IgniteOff")
	@Test
	public void testToggleIgniteOnOff_SystemOn_IgniteOff() {
		infoSys.systemStatus = Sys.ON;
		infoSys.igniteStatus = Ign.OFF;
		
		infoSys.ToggleIgniteOnOff();
		
	    assertEquals(Sys.OFF, infoSys.systemStatus);
	    assertEquals(Ign.ON, infoSys.igniteStatus);
	    testEnableSystem();
	}

	
	
	@Order(7)
	@DisplayName("TC7: testToggleSystemOnOff_SystemOn")
	@Test
	void testToggleSystemOnOff_SystemOn() {

		infoSys.systemStatus = Sys.ON; // Ensure system is on

		infoSys.ToggleSystemOnOff();

		assertEquals(Sys.OFF, infoSys.systemStatus);
		
		testSystemOff();

	}
	@Order(8)
	@DisplayName("TC8: testToggleSystemOnOff_SystemOff")
	@Test
	void testToggleSystemOnOff_SystemOff() {

		infoSys.systemStatus = Sys.OFF; // Ensure system is off

		infoSys.ToggleSystemOnOff();

		assertEquals(Sys.ON, infoSys.systemStatus);

		testSystemOn();
	}
	@Order(9)
	@DisplayName("TC8: testToggleSystemOnOff_textTLongBiggertextTShort")
	@Test
	public void testToggleSystemOnOff_textTLongBiggertextTShort() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("10");
		infoSys.textTLong.setText("20");

		infoSys.ToggleSystemOnOff();

		assertEquals("10", infoSys.textTShort.getText());
		assertEquals("20", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	@Order(10)
	@DisplayName("TC8: testToggleSystemOnOff_textTLongSmallertextTShort")
	@Test
	public void testToggleSystemOnOff_textTLongSmallertextTShort() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("20");
		infoSys.textTLong.setText("10");

		infoSys.ToggleSystemOnOff();

		assertEquals("20", infoSys.textTShort.getText());
		assertEquals("10", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	
	@Test
	public void testToggleSystemOnOff_textTLongTShortEmpty() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("");
		infoSys.textTLong.setText("");

		infoSys.ToggleSystemOnOff();

		assertEquals("", infoSys.textTShort.getText());
		assertEquals("", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textTLongEmpty() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("10");
		infoSys.textTLong.setText("");

		infoSys.ToggleSystemOnOff();

		assertEquals("10", infoSys.textTShort.getText());
		assertEquals("", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textTShortEmpty() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("");
		infoSys.textTLong.setText("20");

		infoSys.ToggleSystemOnOff();

		assertEquals("", infoSys.textTShort.getText());
		assertEquals("20", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textInvalid() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("georgie");
		infoSys.textTLong.setText("farida");

		infoSys.ToggleSystemOnOff();

		assertEquals("georgie", infoSys.textTShort.getText());
		assertEquals("farida", infoSys.textTLong.getText());
		// Add assertion for SystemOn
	}
	
	@Test
	public void testToggleSystemOnOff_textTShortBiggerThanTLONG() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("3000");

		infoSys.ToggleSystemOnOff();

		assertEquals("3000", infoSys.textTShort.getText());

		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textTShortInvalid() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTShort.setText("andrew");

		infoSys.ToggleSystemOnOff();

		assertEquals("andrew", infoSys.textTShort.getText());

		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textTLongBiggerThanTLONG() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTLong.setText("3000");

		infoSys.ToggleSystemOnOff();

		assertEquals("3000", infoSys.textTLong.getText());

		// Add assertion for SystemOn
	}
	@Test
	public void testToggleSystemOnOff_textTLongInvalid() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textTLong.setText("andrew");

		infoSys.ToggleSystemOnOff();

		assertEquals("andrew", infoSys.textTLong.getText());

		// Add assertion for SystemOn
	}
	/////////////////////

	@Test
	void testToggleSystemOnOff_EmptyFields() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("");
		infoSys.textNight_DC.setText("");
		infoSys.ToggleSystemOnOff();
		assertEquals("", infoSys.textDay_DC.getText());
		assertEquals("", infoSys.textNight_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_DayEmpty() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("");
		infoSys.textNight_DC.setText("30");
		infoSys.ToggleSystemOnOff();
		assertEquals("", infoSys.textDay_DC.getText());
		assertEquals("30", infoSys.textNight_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_NightEmpty() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("30");
		infoSys.textNight_DC.setText("");
		infoSys.ToggleSystemOnOff();
		assertEquals("30", infoSys.textDay_DC.getText());
		assertEquals("", infoSys.textNight_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_InvalidDayorNight() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("$$$$$$");
		infoSys.textNight_DC.setText("caEoEFAIUAFEBIEUY");
		infoSys.ToggleSystemOnOff();
		assertEquals("$$$$$$", infoSys.textDay_DC.getText());
		assertEquals("caEoEFAIUAFEBIEUY", infoSys.textNight_DC.getText());
	}


	@Test
	void testToggleSystemOnOff_Day30Night70() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("30");
		infoSys.textNight_DC.setText("70");
		infoSys.ToggleSystemOnOff();
		assertEquals("30", infoSys.textDay_DC.getText());
		assertEquals("70", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_Day70Night30() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("70");
		infoSys.textNight_DC.setText("30");
		infoSys.ToggleSystemOnOff();
		assertEquals("70", infoSys.textDay_DC.getText());
		assertEquals("30", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_Day150Night200() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("150");
		infoSys.textNight_DC.setText("200");
		infoSys.ToggleSystemOnOff();
		assertEquals("150", infoSys.textDay_DC.getText());
		assertEquals("200", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_NegativeDayNegativeNight() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("-200");
		infoSys.textNight_DC.setText("-150");
		infoSys.ToggleSystemOnOff();
		assertEquals("-200", infoSys.textDay_DC.getText());
		assertEquals("-150", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_Negative11Day10Night() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("-11");
		infoSys.textNight_DC.setText("10");
		infoSys.ToggleSystemOnOff();
		assertEquals("-11", infoSys.textDay_DC.getText());
		assertEquals("10", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_Day90Night100() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("90");
		infoSys.textNight_DC.setText("100");
		infoSys.ToggleSystemOnOff();
		assertEquals("90", infoSys.textDay_DC.getText());
		assertEquals("100", infoSys.textNight_DC.getText());
	}

	@Test
	void testToggleSystemOnOff_Day100Night90() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("100");
		infoSys.textNight_DC.setText("90");
		infoSys.ToggleSystemOnOff();
		assertEquals("100", infoSys.textDay_DC.getText());
		assertEquals("90", infoSys.textNight_DC.getText());
	}

	
	@Test
	void testToggleSystemOnOff_DayBiggerNight_DC() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("90");
		infoSys.ToggleSystemOnOff();
		assertEquals("90", infoSys.textDay_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_DaySmallerZero() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("-10");
		infoSys.ToggleSystemOnOff();
		assertEquals("-10", infoSys.textDay_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_DayBigger100() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("110");
		infoSys.ToggleSystemOnOff();
		assertEquals("110", infoSys.textDay_DC.getText());
	}
	
	@Test
	void testToggleSystemOnOff_DayInvalid() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textDay_DC.setText("ayahagaa");
		infoSys.ToggleSystemOnOff();
		assertEquals("ayahagaa", infoSys.textDay_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_NightBiggerDay() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textNight_DC.setText("90");
		infoSys.ToggleSystemOnOff();
		assertEquals("90", infoSys.textNight_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_NightSmallerZero() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textNight_DC.setText("-10");
		infoSys.ToggleSystemOnOff();
		assertEquals("-10", infoSys.textNight_DC.getText());
	}
	@Test
	void testToggleSystemOnOff_NightBigger100() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textNight_DC.setText("110");
		infoSys.ToggleSystemOnOff();
		assertEquals("110", infoSys.textNight_DC.getText());
	}
	
	@Test
	void testToggleSystemOnOff_NightInvalid() {
		infoSys.systemStatus = Sys.OFF;
		infoSys.textNight_DC.setText("ayahagaa");
		infoSys.ToggleSystemOnOff();
		assertEquals("ayahagaa", infoSys.textNight_DC.getText());
	}
	
	
	@Test
	void testDayModeSelected() {
		infoSys.rdbtnDay.setSelected(true);
		infoSys.DayMode();
		assertFalse(infoSys.rdbtnNight.isSelected());
		assertEquals(BackLightMode.DAY, infoSys.backLightController.getBackLightStatus());
	}

	@Test
	void testDayModeNotSelected() {
		infoSys.rdbtnDay.setSelected(false);
		infoSys.DayMode();
		assertTrue(infoSys.rdbtnNight.isSelected());
	}

	@Test
	void testNightModeSelected() {
		infoSys.rdbtnNight.setSelected(true);
		infoSys.NightMode();
		assertEquals(BackLightMode.NIGHT, infoSys.backLightController.getBackLightStatus());
		assertFalse(infoSys.rdbtnDay.isSelected());
	}

	@Test
	void testNightModeNotSelected() {
		infoSys.rdbtnNight.setSelected(false);
		infoSys.NightMode();
		assertTrue(infoSys.rdbtnNight.isSelected());

	}

	@Test
	public void testSystemOff() {
		infoSys.igniteStatus = Ign.ON;
		infoSys.EnableSystem();
		infoSys.textTLong.setEnabled(true);
		infoSys.textTShort.setEnabled(true);
		infoSys.textDay_DC.setEnabled(true);
		infoSys.textNight_DC.setEnabled(true);
		infoSys.btnSystem.setText("System: OFF");
		infoSys.btnSystem.setForeground(Color.green);
		infoSys.btnIgnite.setEnabled(true);
		infoSys.textAreaDTC.setText("Some text");
		infoSys.resetRadioButtons();
		infoSys.infoButton.resetInfoButton();
		infoSys.lblDTCFlasher.setIcon(new ImageIcon("some icon"));
		infoSys.backLightController.resetBackLightController();
		infoSys.dtc.resetDTC();

		infoSys.SystemOff();
		// Check the expected outcomes
		assertEquals(Mute.NON_FUNCTIONAL, infoSys.infoButton.getMuteStatus()); //per requirement
	    assertEquals(OnOff.NON_FUNCTIONAL, infoSys.infoButton.getOnOffStatus());//per requirement
	    assertFalse(infoSys.dtc.getFlash());//per requirement
	    assertEquals(Sys.OFF, infoSys.systemStatus);
		assertEquals(Ign.OFF, infoSys.igniteStatus);
		assertFalse(infoSys.textTLong.isEnabled());
		assertFalse(infoSys.textTShort.isEnabled());
		assertFalse(infoSys.textDay_DC.isEnabled());
		assertFalse(infoSys.textNight_DC.isEnabled());
		assertEquals("System: OFF", infoSys.btnSystem.getText());
		assertEquals(Color.red, infoSys.btnSystem.getForeground());
		assertFalse(infoSys.btnIgnite.isEnabled());
		assertEquals("", infoSys.textAreaDTC.getText());
		assertNull(infoSys.lblDTCFlasher.getIcon());
	}

	@Test
	public void testSystemOn() {
		infoSys.igniteStatus = Ign.OFF;
		infoSys.DisableSystem();
		infoSys.textTLong.setEnabled(false);
		infoSys.textTShort.setEnabled(false);
		infoSys.textDay_DC.setEnabled(false);
		infoSys.textNight_DC.setEnabled(false);
		infoSys.btnSystem.setText("System: ON");
		infoSys.btnSystem.setForeground(Color.red);
		infoSys.btnIgnite.setEnabled(false);

		infoSys.SystemOn();
		// Check the expected outcomes
		assertEquals(Mute.FUNCTIONAL, infoSys.infoButton.getMuteStatus()); //per requirement
	    assertEquals(OnOff.FUNCTIONAL, infoSys.infoButton.getOnOffStatus());//per requirement
	    assertTrue(infoSys.dtc.getFlash());//per requirement
	    assertEquals(Sys.ON, infoSys.systemStatus);
		assertTrue(infoSys.textTLong.isEnabled());
		assertTrue(infoSys.textTShort.isEnabled());
		assertTrue(infoSys.textDay_DC.isEnabled());
		assertTrue(infoSys.textNight_DC.isEnabled());
		assertEquals("System: ON", infoSys.btnSystem.getText());
		assertEquals(Color.green, infoSys.btnSystem.getForeground());
		assertTrue(infoSys.btnIgnite.isEnabled());
	}

	@Test
	public void testInitSystem() {
		DTC dtc = new DTC();
		dtc.setDTC("B91212", StatusCode.CONFIRMED_AND_FAILED);
		
		infoSys.InitSystem();
		
		assertEquals(Ign.OFF, infoSys.igniteStatus);
		assertEquals(Sys.OFF, infoSys.systemStatus);
		assertEquals(Mute.NON_FUNCTIONAL, infoSys.infoButton.getMuteStatus()); //per requirement
	    assertEquals(OnOff.NON_FUNCTIONAL, infoSys.infoButton.getOnOffStatus());//per requirement
	    assertFalse(infoSys.dtc.getFlash());//per requirement
		assertNotNull(infoSys.dtc);
		assertEquals(StatusCode.NO_ISSUE, dtc.getDTC("B91212"));
		assertNotNull(infoSys.infoButton);
		assertNotNull(infoSys.backLightController);
		testResetRadioButtons();
		testSystemOff();

	}

	@Test
	public void testResetRadioButtons() {
		infoSys.rdbtnDay.setSelected(false);
		infoSys.rdbtnNight.setSelected(false);
		infoSys.resetRadioButtons();
		// Check the expected outcomes
		assertTrue(infoSys.rdbtnDay.isSelected());
		assertFalse(infoSys.rdbtnNight.isSelected());
	}
}
