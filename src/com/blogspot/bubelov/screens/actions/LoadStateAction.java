package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.Cpu;
import com.blogspot.bubelov.screens.main.MainScreen;
import com.blogspot.bubelov.utils.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:55 PM
 */
public class LoadStateAction extends AbstractAction {
    private MainScreen mainScreen;
    private String fileName;

    public LoadStateAction(MainScreen mainScreen, String fileName) {
        super("Load State");
        this.mainScreen = mainScreen;
        this.fileName = fileName;
        setEnabled(stateFileExists());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!stateFileExists()) {
            return;
        }

        try {
            Cpu cpu = (Cpu) IOUtils.deSerializeObjectFromFile(new File("state"));
            mainScreen.setCpu(cpu);
            mainScreen.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean stateFileExists() {
        return new File(fileName).exists();
    }
}
