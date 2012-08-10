package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.Cpu;
import com.blogspot.bubelov.core.Rom;
import com.blogspot.bubelov.utils.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:23 PM
 */
public class OpenRomAction extends AbstractAction {
    private Cpu cpu;
    private JFrame owner;

    public OpenRomAction(Cpu cpu, JFrame owner) {
        super("Open Rom...");
        this.cpu = cpu;
        this.owner = owner;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = createFileChooser();

        if (fileChooser.showOpenDialog(owner) == JFileChooser.APPROVE_OPTION) {
            cpu.load(new Rom(IOUtils.getBytes(fileChooser.getSelectedFile())));
        }
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Open ROM");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        return fileChooser;
    }
}
