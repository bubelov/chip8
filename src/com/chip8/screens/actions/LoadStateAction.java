package com.chip8.screens.actions;

import com.chip8.core.Cpu;
import com.chip8.screens.EmulatorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:55 PM
 */
public class LoadStateAction extends AbstractAction {
    private EmulatorController controller;

    public LoadStateAction(EmulatorController controller) {
        super("Load State");
        this.controller = controller;
        setEnabled(stateFileExists());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!stateFileExists()) {
            return;
        }

        try {
            File stateFile = new File("state");
            InputStream inputStream = new FileInputStream(stateFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Cpu cpu = (Cpu)objectInputStream.readObject();
            objectInputStream.close();
            controller.setCpu(cpu);
            controller.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean stateFileExists() {
        return new File("state").exists();
    }
}
