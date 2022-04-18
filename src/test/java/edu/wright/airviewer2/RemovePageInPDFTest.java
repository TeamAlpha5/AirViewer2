package edu.wright.airviewer2;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RemovePageInPDFTest {

static RemovePageInPDF rem = null;

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		rem = new RemovePageInPDF();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		rem = null;
	}

	

	@Test
	void test() {
		try {
			String filePath="C:/Users/errar.DESKTOP-1FIQSSK/Desktop/samplePdf.pdf";
			rem.removePagePdf(filePath);;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}

}
}
