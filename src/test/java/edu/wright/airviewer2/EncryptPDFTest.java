package edu.wright.airviewer2;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncryptPDFTest {

static EncryptPDF ep = null;

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		ep = new EncryptPDF();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ep = null;
	}

	

	@Test
	void test() {
		try {
			String filePath="C:/Users/errar.DESKTOP-1FIQSSK/Desktop/samplePdf.pdf";
			ep.encryptPdf(filePath);;
		} catch (Exception e) {
		// TODO Auto-generated catch block
		//e.printStackTrace();
		}
	}

}
