package com.blogspot.bubelov.chipx.screens.actions;

import com.blogspot.bubelov.chipx.core.preferences.Preferences;
import com.blogspot.bubelov.chipx.screens.keymapping.KeyMappingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:45 PM
 */
public class ChangeKeyMappingAction extends AbstractPreferencesAction {
    private JFrame owner;

    public ChangeKeyMappingAction(JFrame owner, Preferences preferences) {
        super("Key Mapping...", preferences);
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog keyMappingDialog = new KeyMappingDialog(owner, preferences);
        keyMappingDialog.setVisible(true);
    }
}
