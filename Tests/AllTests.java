package infotainmentSytemPackage.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BackLightControllerTest.class, DTCTest.class, InfoButtonTest.class, InfoSysTest.class, })
public class AllTests {

}
