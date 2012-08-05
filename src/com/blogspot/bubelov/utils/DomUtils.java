package com.blogspot.bubelov.utils;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Author: Igor Bubelov
 * Date: 7/31/12 6:23 PM
 */
public class DomUtils {
    public static void saveDocumentToFile(Document document, File file) throws Exception {
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        createTransformer().transform(domSource, streamResult);
    }

    private static Transformer createTransformer() throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        return transformer;
    }
}
