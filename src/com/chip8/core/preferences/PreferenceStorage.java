package com.chip8.core.preferences;

import com.chip8.utils.DomUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;

/**
 * Author: Igor Bubelov
 * Date: 7/19/12 1:44 PM
 */
public class PreferenceStorage {
    public Preferences get() {
        try {
            InputSource preferencesSource;

            if (userDefinedPreferencesExist()) {
                preferencesSource = new InputSource("preferences.xml");
            } else {
                preferencesSource = new InputSource(getClass().getResourceAsStream("/conf/preferences.xml"));
            }

            return loadPreferencesFromXml(preferencesSource);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void put(Preferences preferences) {
        try {
            Document document = new DomPreferencesBuilder().build(preferences);
            DomUtils.saveDocumentToFile(document, new File("preferences.xml"));
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't save preferences", ex);
        }
    }

    private boolean userDefinedPreferencesExist() {
        return new File("preferences.xml").exists();
    }

    private Preferences loadPreferencesFromXml(InputSource source) throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        XMLPreferenceHandler xmlHandler = new XMLPreferenceHandler();
        xmlReader.setContentHandler(xmlHandler);
        xmlReader.setErrorHandler(xmlHandler);
        xmlReader.parse(source);
        return xmlHandler.getPreferences();
    }
}
