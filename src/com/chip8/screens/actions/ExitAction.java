package com.chip8.screens.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 7:21 PM
 */
public class ExitAction extends AbstractAction {
    public ExitAction() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
