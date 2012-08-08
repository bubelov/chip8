package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.screens.EmulatorController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 8/8/12 8:05 PM
 */
public class PauseAction extends AbstractAction {
    private EmulatorController controller;

    public PauseAction(EmulatorController controller) {
        super("Pause");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.stop();
    }
}
