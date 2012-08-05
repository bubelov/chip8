package com.blogspot.bubelov;

import com.blogspot.bubelov.screens.main.MainScreen;

import javax.swing.*;

/**
 * Author: Igor Bubelov
 * Date: 5/07/12 11:23 PM
 */
public class ChipXApplication {
    private JFrame mainScreen;

    public ChipXApplication() {
        initMainScreen();
        showMainScreen();
    }

    private void initMainScreen() {
        mainScreen = new MainScreen();
    }

    private void showMainScreen() {
        mainScreen.setVisible(true);
    }

    public static void main(String[] args) {
        new ChipXApplication();
    }
}
