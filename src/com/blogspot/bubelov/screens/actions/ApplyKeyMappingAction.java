package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.input.Key;
import com.blogspot.bubelov.core.preferences.Preferences;

import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * Author: Igor Bubelov
 * Date: 7/24/12 8:24 PM
 */
public class ApplyKeyMappingAction extends AbstractPreferencesAction {
    private Map<Key, Integer> keyMapping;

    public ApplyKeyMappingAction(Preferences preferences, Map<Key, Integer> keyMapping) {
        super("Apply", preferences);
        this.keyMapping = keyMapping;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        preferences.getKeyMapping().clear();
        preferences.getKeyMapping().putAll(keyMapping);
        savePreferences();
    }
}

