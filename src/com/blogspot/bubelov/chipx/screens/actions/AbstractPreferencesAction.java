package com.blogspot.bubelov.chipx.screens.actions;

import com.blogspot.bubelov.chipx.core.preferences.PreferenceStorage;
import com.blogspot.bubelov.chipx.core.preferences.Preferences;

import javax.swing.*;

/**
 * Author: Igor Bubelov
 * Date: 7/30/12 2:44 PM
 */
public abstract class AbstractPreferencesAction extends AbstractAction {
    protected Preferences preferences;

    public AbstractPreferencesAction(String name, Preferences preferences) {
        super(name);
        this.preferences = preferences;
    }

    protected void savePreferences() {
        new PreferenceStorage().put(preferences);
    }
}
