package com.chip8.core.preferences;

import com.chip8.core.input.Key;
import com.chip8.core.Scale;
import com.chip8.core.Speed;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Author: Igor Bubelov
 * Date: 7/19/12 2:26 PM
 */
public class XMLPreferenceHandler extends DefaultHandler {
    private Preferences preferences;

    public Preferences getPreferences() {
        return preferences;
    }

    @Override
    public void startDocument() throws SAXException {
        preferences = new Preferences();
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attributes) throws SAXException {
        if (name.equals("preferences")) {
            Speed speed = Speed.valueOf(attributes.getValue("speed"));
            Scale scale = Scale.valueOf(attributes.getValue("scale"));
            preferences.setSpeed(speed);
            preferences.setScale(scale);
        }

        if (name.equals("key")) {
            Key key = Key.valueOf(attributes.getValue("name"));
            Integer code = Integer.valueOf(attributes.getValue("code"));
            preferences.getKeyMapping().put(key, code);
        }
    }
}
