package com.chip8.screens.actions;

import com.chip8.screens.EmulatorController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:00 PM
 */
public class ResetAction extends AbstractAction {
    private EmulatorController controller;

    public ResetAction(EmulatorController controller) {
        super("Reset");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.getCpu().reset();
    }
}
