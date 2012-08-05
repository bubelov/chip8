package com.blogspot.bubelov.screens;

import com.blogspot.bubelov.core.Cpu;

import javax.swing.*;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:02 PM
 */
public interface EmulatorController {
    Cpu getCpu();

    void setCpu(Cpu cpu);

    JFrame getMainWindow();

    void start();

    void stop();
}
