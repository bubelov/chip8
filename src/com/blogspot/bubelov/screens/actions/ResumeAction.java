package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.screens.EmulatorController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 8/8/12 8:02 PM
 */
public class ResumeAction extends AbstractAction {
    private EmulatorController controller;

    public ResumeAction(EmulatorController controller) {
        super("Resume");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.start();
    }
}
