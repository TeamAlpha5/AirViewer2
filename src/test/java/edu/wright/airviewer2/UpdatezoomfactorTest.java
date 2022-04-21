package edu.wright.airviewer2;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class UpdatezoomfactorTest {

	// Here object will be initialized
	static AIRViewerController airViewercontroller;

	/**
	 * @throws Exception
	 */
	@BeforeAll
	public static void setUp() throws Exception {
		// Here pdf file path will be given to test the file
		airViewercontroller = new AIRViewerController();
	}

	/**
	 * @throws Exception
	 */
	@AfterAll
	public static void tearDown() throws Exception {
		// Here object will be initialized to null
		airViewercontroller = null;
	}

	/**
	 * testing method
	 */
	@org.junit.jupiter.api.Test
	public void test() {
		/*
		 * Here the functionality will be tested and returns the test case results
		 */
		try {
			/*
			 * here a call to the method will take place
			 */
			airViewercontroller.updateZoomFactor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
