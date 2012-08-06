package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.Cpu;
import com.blogspot.bubelov.screens.EmulatorController;
import com.blogspot.bubelov.utils.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

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
            Cpu cpu = (Cpu) IOUtils.deSerializeObjectFromFile(new File("state"));
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
