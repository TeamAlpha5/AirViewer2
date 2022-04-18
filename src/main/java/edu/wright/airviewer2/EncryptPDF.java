package edu.wright.airviewer2;

import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
/*
 * @author erraramdixit
 *
 */
public class EncryptPDF {

	/*
	 * THe below method encrypts the pdf and keeps them secure with a password
	 * File created date 04/16/2022 no changes made after this date
	 * any changes made to it please update the date and document the reason for the upgrade
	 */
	public EncryptPDF() {
		// TODO Auto-generated constructor stub
	}

	
	public void encryptPdf(String fileName) throws Exception {
   	 String OWNER_PASSWORD = "World";
       PdfDocument pdfDoc = new PdfDocument(
               new PdfReader(fileName),
               new PdfWriter(fileName+"-1encryted", new WriterProperties().setStandardEncryption(
                       // null user password argument is equal to empty string,
                       // this means that no user password required
                       null,
                       OWNER_PASSWORD.getBytes(),
                       EncryptionConstants.ALLOW_PRINTING,
                       EncryptionConstants.ENCRYPTION_AES_128 | EncryptionConstants.DO_NOT_ENCRYPT_METADATA))
       );
       pdfDoc.close();
   }
}
