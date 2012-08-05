package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.preferences.Preferences;
import com.blogspot.bubelov.core.Scale;

import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 12:55 PM
 */
public class ChangeScaleAction extends AbstractPreferencesAction {
    private Scale scale;

    public ChangeScaleAction(Scale scale, Preferences preferences) {
        super(scale.toString(), preferences);
        this.scale = scale;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        preferences.setScale(scale);
        savePreferences();
    }
}
