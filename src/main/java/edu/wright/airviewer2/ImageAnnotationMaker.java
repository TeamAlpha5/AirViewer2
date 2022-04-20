/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wright.airviewer2;

import java.awt.geom.AffineTransform;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationSquareCircle;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.util.Matrix;

/**
 *
 * @author erik
 */
public class ImageAnnotationMaker {

    public static List<PDAnnotation> make(PDDocument document,
            ArrayList<String> arguments) {
        assert null != arguments && arguments.size() == 6;
        assert null != document;

        List<PDAnnotation> result = null;

        try {
            int pageNumber = parseInt(arguments.get(0));
            float lowerLeftX = parseFloat(arguments.get(1));
            float lowerLeftY = parseFloat(arguments.get(2));
            float width = parseFloat(arguments.get(3));
            float height = parseFloat(arguments.get(4));
            String path = arguments.get(5);

            try {
                PDPage page = document.getPage(pageNumber);
                PDImageXObject pdImage = PDImageXObject.createFromFile(path,document);
                pdImage.setWidth((int)width);
                pdImage.setWidth((int)height);
                PDPageContentStream contents = new PDPageContentStream(document, page);
                contents.drawImage(pdImage, lowerLeftX, lowerLeftY);
                contents.close();                                 

            } catch (IOException ex) {
                result = null;
            }
        } catch (NumberFormatException | NullPointerException ex) {
            result = null;
        }
        return result;
    }

}
