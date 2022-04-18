package edu.wright.airviewer2;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RotateViewerModelTest extends RotateViewerModel {
	
	static RotateViewerModel rot = null;

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		rot = new RotateViewerModel();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		rot = null;
	}

	

	@Test
	void test() {
		try {
			String filePath="C:/Users/errar.DESKTOP-1FIQSSK/Desktop/samplePdf.pdf";
			rot.rotatefn(filePath);;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}
		//fail("Not yet implemented");
	}
	

}
