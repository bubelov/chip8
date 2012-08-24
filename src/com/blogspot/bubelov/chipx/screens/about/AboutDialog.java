package com.blogspot.bubelov.chipx.screens.about;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 7:38 PM
 */
public class AboutDialog extends JDialog {
    public AboutDialog(Frame owner) {
        super(owner, "About", true);
        initUI();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initUI() {
        rootPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(3, 1, 10, 10));
        add(new JLabel("ChipX Emulator"));
        add(new JLabel("Version: 1.0"));
        add(new JLabel("Author: Igor Bubelov"));
        pack();
    }
}
