package infotainmentSytemPackage.test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import infotainmentSytemPackage.ButtonStatus;
import infotainmentSytemPackage.InfoButton;
import infotainmentSytemPackage.Mute;
import infotainmentSytemPackage.OnOff;

public class InfoButtonTest {
	private InfoButton infoButton;

	@BeforeEach
	public void setUp() {
		infoButton = new InfoButton(); 
	}

	// TC1:Func_Req_008 : for when Time is Greater than TSTUCK
	// Expected output: Mute and on-off should be non-functional
	@Test
	public void testUpdateStates_ForTimeGreaterThanTSTUCK() {
		infoButton.updateStates(60001);
		assertEquals(Mute.NON_FUNCTIONAL, infoButton.getMuteStatus());
		assertEquals(OnOff.NON_FUNCTIONAL, infoButton.getOnOffStatus());
	}
	// TC2:Func_Req_003: for when Time is Greater than TLONG and Mute is non-functional
	// Expected output: Mute should be non-functional while on-off should be functional
	@Test
	public void testUpdateStates_ForTimeGreaterThanTLONG_WhenMuteIsNonFunctional() {
		infoButton.setMuteStatus(Mute.NON_FUNCTIONAL);
		infoButton.setOnOffStatus(OnOff.NON_FUNCTIONAL);
		infoButton.updateStates(20001);
		assertEquals(Mute.NON_FUNCTIONAL, infoButton.getMuteStatus());
		assertEquals(OnOff.FUNCTIONAL, infoButton.getOnOffStatus());
	}

	// TC3:Func_Req_003: for when Time is Greater than TLONG and Mute is functional
	// Expected output: Mute should be functional while on-off should be non-functional
	@Test
	public void testUpdateStates_ForTimeGreaterThanTLONG_WhenMuteIsFunctional() {
		infoButton.setMuteStatus(Mute.FUNCTIONAL);
		infoButton.setOnOffStatus(OnOff.FUNCTIONAL);
		infoButton.updateStates(20001);
		assertEquals(Mute.FUNCTIONAL, infoButton.getMuteStatus());
		assertEquals(OnOff.NON_FUNCTIONAL, infoButton.getOnOffStatus());
	} 

	// TC4:Func_Req_002: for when Time is Greater than TSHORT and OnOffIsNonFunctional
	// Expected output: on-off should be non-functional while mute should be functional
	@Test
	public void testUpdateStates_ForTimeGreaterThanTSHORT_OnOffIsNonFunctional() {
		infoButton.setOnOffStatus(OnOff.NON_FUNCTIONAL);
		infoButton.setMuteStatus(Mute.NON_FUNCTIONAL);
		infoButton.updateStates(1001);
		assertEquals(OnOff.NON_FUNCTIONAL, infoButton.getOnOffStatus());
		assertEquals(Mute.FUNCTIONAL, infoButton.getMuteStatus());
	}

	// TC5:Func_Req_002: for when Time is Greater than TSHORT and OnOffIsFunctional
	// Expected output: on-off should be functional while mute should be non-functional
	@Test
	public void testUpdateStates_ForTimeGreaterThanTSHORT_OnOffIsFunctional() {
		infoButton.setOnOffStatus(OnOff.FUNCTIONAL);
		infoButton.setMuteStatus(Mute.FUNCTIONAL);
		infoButton.updateStates(1001);
		assertEquals(OnOff.FUNCTIONAL, infoButton.getOnOffStatus());
		assertEquals(Mute.NON_FUNCTIONAL, infoButton.getMuteStatus());
	}
	// TC6:Func_Req_002: for when Time is less than or equal to TSHORT
	// Expected output: on-off and mute should remain non-functional
	@Test 
	public void testUpdateStates_ForTimeLessThanOrEqualToTSHORT_FUNCTIONAL() {
		infoButton.setOnOffStatus(OnOff.FUNCTIONAL);
		infoButton.setMuteStatus(Mute.FUNCTIONAL);
		infoButton.updateStates(1000);
		assertEquals(OnOff.FUNCTIONAL, infoButton.getOnOffStatus());
		assertEquals(Mute.FUNCTIONAL, infoButton.getMuteStatus());
	} 

	// TC1:Func_Req_09,Func_Req_010,Func_Req_015 Test for the resetInfoButton() method
	/* Expected output: Mute and on-off should be non-functional, button status should be released,
	 *  TSHORT should be 1000, and TLONG should be 2000
	 */
    @Test
    public void testResetInfoButton() {
    	infoButton.setButtonStatus(ButtonStatus.HOLD);
        infoButton.setMuteStatus(Mute.FUNCTIONAL);
    	infoButton.setOnOffStatus(OnOff.FUNCTIONAL);
    	infoButton.setTLONG(2500);
    	infoButton.setTSHORT(1500);
    	
        infoButton.resetInfoButton();
        
        assertEquals(ButtonStatus.RELEASED, infoButton.getButtonStatus());
        assertEquals(1000, infoButton.getTSHORT()); 
        assertEquals(2000, infoButton.getTLONG());
        assertEquals(Mute.NON_FUNCTIONAL, infoButton.getMuteStatus());
        assertEquals(OnOff.NON_FUNCTIONAL, infoButton.getOnOffStatus());
    }

}
