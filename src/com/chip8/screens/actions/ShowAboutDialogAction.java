package com.chip8.screens.actions;

import com.chip8.screens.about.AboutDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 7:40 PM
 */
public class ShowAboutDialogAction extends AbstractAction {
    private JFrame owner;

    public ShowAboutDialogAction(JFrame owner) {
        super("About");
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog aboutDialog = new AboutDialog(owner);
        aboutDialog.setVisible(true);
    }
}
