package com.chip8.core.preferences;

import com.chip8.core.input.Key;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Author: Igor Bubelov
 * Date: 7/31/12 6:10 PM
 */
public class DomPreferencesBuilder {
    private Preferences preferences;
    private Document document;

    public Document build(Preferences preferences) {
        this.preferences = preferences;
        document = createDocument();

        Element rootElement = createRootElement();
        document.appendChild(rootElement);

        Element keyboardElement = createKeyboardElement();
        rootElement.appendChild(keyboardElement);

        return document;
    }

    private Document createDocument() {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            return documentBuilder.newDocument();
        } catch (ParserConfigurationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Element createRootElement() {
        Element rootElement = document.createElement("preferences");
        rootElement.setAttribute("speed", preferences.getSpeed().name());
        rootElement.setAttribute("scale", preferences.getScale().name());
        return rootElement;
    }

    private Element createKeyboardElement() {
        Element keyboardElement = document.createElement("keyboard");

        for (Key key : preferences.getKeyMapping().keySet()) {
            Element keyElement = document.createElement("key");
            keyElement.setAttribute("name", key.toString());
            keyElement.setAttribute("code", preferences.getKeyMapping().get(key).toString());
            keyboardElement.appendChild(keyElement);
        }

        return keyboardElement;
    }
}
