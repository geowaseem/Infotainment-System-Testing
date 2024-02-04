package infotainmentSytemPackage.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import infotainmentSytemPackage.BackLightController;
import infotainmentSytemPackage.BackLightMode;

class BackLightControllerTest {


	@Test

//  This test case ensures that calling resetBackLightController() after setting 
//  custom values resets the duty cycles and back light status to their default values.
	public void testResetBackLightControllerAfterCustomValues() {
		BackLightController backLightController = new BackLightController();
		backLightController.setDay_DC(80);
		backLightController.setNight_DC(180);
		backLightController.setBackLightStatus(BackLightMode.NIGHT);
		backLightController.resetBackLightController();
		assertEquals(100, backLightController.getDay_DC(), 0.01);
		assertEquals(200, backLightController.getNight_DC(), 0.01);
		assertEquals(BackLightMode.DAY, backLightController.getBackLightStatus());
	}

	@Test
// This test case ensures that setting new custom values after calling 
//  resetBackLightController() works as expected.

	public void testResetBackLightControllerChangeAfterReset() {
		BackLightController backLightController = new BackLightController();
		backLightController.setDay_DC(80);
		backLightController.setNight_DC(180);
		backLightController.setBackLightStatus(BackLightMode.NIGHT);
		backLightController.resetBackLightController();
		backLightController.setDay_DC(120);
		backLightController.setNight_DC(220);
		backLightController.setBackLightStatus(BackLightMode.DAY);
		assertEquals(120, backLightController.getDay_DC(), 0.01);
		assertEquals(220, backLightController.getNight_DC(), 0.01);
		assertEquals(BackLightMode.DAY, backLightController.getBackLightStatus());
	}

}
