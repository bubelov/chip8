package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.screens.ActivityController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 8/8/12 8:05 PM
 */
public class StopAction extends AbstractAction {
    private ActivityController controller;

    public StopAction(String name, ActivityController controller) {
        super(name);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.stop();
    }
}
