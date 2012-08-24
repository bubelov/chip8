package com.blogspot.bubelov.chipx.screens.actions;

import com.blogspot.bubelov.chipx.screens.ActivityController;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 8/8/12 8:02 PM
 */
public class StartAction extends AbstractAction {
    private ActivityController controller;

    public StartAction(String name, ActivityController controller) {
        super(name);
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.start();
    }
}
