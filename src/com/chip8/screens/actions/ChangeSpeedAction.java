package com.chip8.screens.actions;

import com.chip8.core.preferences.Preferences;
import com.chip8.core.Speed;

import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 12:20 PM
 */
public class ChangeSpeedAction extends AbstractPreferencesAction {
    private Speed speed;

    public ChangeSpeedAction(Speed speed, Preferences preferences) {
        super(speed.toString(), preferences);
        this.speed = speed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        preferences.setSpeed(speed);
        savePreferences();
    }
}
