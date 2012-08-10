package com.blogspot.bubelov.screens.actions;

import com.blogspot.bubelov.core.Cpu;
import com.blogspot.bubelov.utils.IOUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 10:54 PM
 */
public class SaveStateAction extends AbstractAction {
    private Cpu cpu;
    private String fileName;

    public SaveStateAction(Cpu cpu, String fileName) {
        super("Save State");
        this.cpu = cpu;
        this.fileName = fileName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            IOUtils.serializeObjectToFile(cpu, new File(fileName));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
