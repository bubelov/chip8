package com.blogspot.bubelov.chipx.screens.actions;

import com.blogspot.bubelov.chipx.core.Cpu;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:00 PM
 */
public class ResetAction extends AbstractAction {
    private Cpu cpu;

    public ResetAction(Cpu cpu) {
        super("Reset");
        this.cpu = cpu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cpu.reset();
    }
}
