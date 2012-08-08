package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.Rom;
import com.blogspot.bubelov.screens.EmulatorController;
import com.blogspot.bubelov.utils.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:23 PM
 */
public class OpenRomAction extends AbstractAction {
    private EmulatorController controller;

    public OpenRomAction(EmulatorController controller) {
        super("Open Rom...");
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Open ROM");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(controller.getMainWindow()) == JFileChooser.APPROVE_OPTION) {
            controller.getCpu().load(new Rom(IOUtils.getBytes(fileChooser.getSelectedFile())));
            controller.romOpened();
            controller.start();
        }
    }
}
