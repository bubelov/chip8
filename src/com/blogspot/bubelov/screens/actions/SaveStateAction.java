package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.screens.EmulatorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:54 PM
 */
public class SaveStateAction extends AbstractAction {
    private EmulatorController controller;

    public SaveStateAction(EmulatorController controller) {
        super("Save State");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            File stateFile = new File("state");
            OutputStream outputStream = new FileOutputStream(stateFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(controller.getCpu());
            objectOutputStream.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
