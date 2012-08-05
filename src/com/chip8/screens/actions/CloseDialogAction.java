package com.chip8.screens.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Date: 7/23/12
 * Time: 7:30 PM
 */
public class CloseDialogAction extends AbstractAction {
    private JDialog dialog;

    public CloseDialogAction(JDialog dialog) {
        super("Close");
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        dialog.dispose();
    }
}
