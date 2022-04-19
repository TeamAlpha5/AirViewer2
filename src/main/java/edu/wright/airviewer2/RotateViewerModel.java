package edu.wright.airviewer2;

/*import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import com.google.java.contract.Invariant;
import com.google.java.contract.Requires;
import com.google.java.contract.Ensures;

import com.itextpdf.kernel.exceptions.PdfException;*/
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
/*
 * @author ErraRamDixit
 */
@Invariant("filePath!= null && filePath.length() > 0")
public class RotateViewerModel {
String filePath;

/**
* @throws Exception
* @throws NullPointerException
*/
    
 /* The below method rotates the pdf in all direction(90,180,270,360) this is NOT landscape or potrait mode this functionality
 * rotates the pdf in all the required directions irrespective of rotating the monitor 
 * File created date 04/10/2022 no changes made after this date
 * if any changes made to it please update the date and document the reason for the upgrade
 */
    @Requires("filePath != null && filePath.length() > 0")
	@Ensures("result == true")
public boolean rotatefn(String filePath) throws Exception, NullPointerException {

PdfDocument pdfDoc = new PdfDocument(new PdfReader(filePath), new PdfWriter(filePath+"-1rotate.pdf"));
int rotate=0;
for (int p = 1; p <= pdfDoc.getNumberOfPages(); p++) {
    PdfPage page = pdfDoc.getPage(p);
    rotate = page.getRotation();
    if (rotate == 0) {
        page.setRotation(90);
    } else {
        page.setRotation((rotate + 90) % 360);
    }
}

pdfDoc.close();
    return true;
}


}

