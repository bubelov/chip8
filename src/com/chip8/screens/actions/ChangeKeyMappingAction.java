package com.chip8.screens.actions;

import com.chip8.core.preferences.Preferences;
import com.chip8.screens.keymapping.KeyMappingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:45 PM
 */
public class ChangeKeyMappingAction extends AbstractPreferencesAction {
    private JFrame owner;

    public ChangeKeyMappingAction(JFrame owner, Preferences preferences) {
        super("Change Key Mapping", preferences);
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog keyMappingDialog = new KeyMappingDialog(owner, preferences);
        keyMappingDialog.setVisible(true);
    }
}
