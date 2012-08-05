package com.chip8.screens.actions;

import com.chip8.core.preferences.Preferences;
import com.chip8.core.Scale;

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
