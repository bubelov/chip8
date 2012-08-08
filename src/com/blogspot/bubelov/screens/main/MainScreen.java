package com.blogspot.bubelov.screens.main;

import com.blogspot.bubelov.EmulatorListener;
import com.blogspot.bubelov.core.*;
import com.blogspot.bubelov.core.Cpu;
import com.blogspot.bubelov.core.preferences.PreferenceListener;
import com.blogspot.bubelov.core.preferences.Preferences;
import com.blogspot.bubelov.screens.EmulatorController;
import com.blogspot.bubelov.core.preferences.PreferenceStorage;
import com.blogspot.bubelov.core.input.InputHandler;
import com.blogspot.bubelov.screens.actions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:01 PM
 */
public class MainScreen extends JFrame implements EmulatorController, PreferenceListener, EmulatorListener {
    private Cpu cpu;
    private Preferences preferences;
    private InputHandler inputHandler;
    private RenderingCanvas renderingCanvas;
    private Timer cpuCycleTimer;
    private JMenuBar menuBar;

    private AbstractAction resumeAction;
    private AbstractAction pauseAction;
    private AbstractAction resetAction;

    public MainScreen() throws HeadlessException {
        super("ChipX");
        cpu = new Cpu();
        initPreferences();
        inputHandler = new InputHandler(cpu.getKeyboard(), preferences.getKeyMapping());
        addKeyListener(inputHandler);
        initCpuCycleTimer();
        initUI();
    }

    private void initPreferences() {
        preferences = new PreferenceStorage().get();
        preferences.addListener(this);
    }

    private void initCpuCycleTimer() {
        cpuCycleTimer = new Timer(preferences.getSpeed().delayMillis(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cpu.cycle();
                renderingCanvas.repaint();
            }
        });
    }

    @Override
    public Cpu getCpu() {
        return cpu;
    }

    @Override
    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
        renderingCanvas.setDisplay(cpu.getDisplay());
        inputHandler.setKeyboard(cpu.getKeyboard());
    }

    @Override
    public JFrame getMainWindow() {
        return this;
    }

    @Override
    public void start() {
        cpuCycleTimer.start();
        resumeAction.setEnabled(false);
        pauseAction.setEnabled(true);

    }

    @Override
    public void stop() {
        cpuCycleTimer.stop();
        resumeAction.setEnabled(true);
        pauseAction.setEnabled(false);
    }

    @Override
    public void romOpened() {
        resumeAction.setEnabled(false);
        pauseAction.setEnabled(true);
        resetAction.setEnabled(true);
    }

    @Override
    public void scaleChanged() {
        renderingCanvas.adjustSize(preferences.getScale().cellSize());
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void speedChanged() {
        if (cpuCycleTimer.isRunning()) {
            cpuCycleTimer.stop();
        }

        cpuCycleTimer.setDelay(preferences.getSpeed().delayMillis());
        cpuCycleTimer.start();
    }

    private void initUI() {
        createMenuBar();
        createRenderingCanvas();

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();
        createFileMenu();
        createRunMenu();
        createMemoryMenu();
        createConfigMenu();
        createHelpMenu();
        add(menuBar, BorderLayout.NORTH);
    }

    private void createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new OpenRomAction(this));
        fileMenu.add(new ExitAction());
        menuBar.add(fileMenu);
    }

    private void createRunMenu() {
        JMenu menu = new JMenu("Run");
        resumeAction = new ResumeAction(this);
        resumeAction.setEnabled(false);
        menu.add(resumeAction);
        pauseAction = new PauseAction(this);
        pauseAction.setEnabled(false);
        menu.add(pauseAction);
        resetAction = new ResetAction(this);
        resetAction.setEnabled(false);
        menu.add(resetAction);
        menuBar.add(menu);
    }

    private void createMemoryMenu() {
        JMenu menu = new JMenu("Memory");
        menu.add(new SaveStateAction(this));
        menu.add(new LoadStateAction(this));
        menuBar.add(menu);
    }

    private void createConfigMenu() {
        JMenu menu = new JMenu("Configuration");
        menu.add(createSpeedMenu());
        menu.add(createScaleMenu());
        menu.add(new ChangeKeyMappingAction(this, preferences));
        menuBar.add(menu);
    }

    private JMenu createSpeedMenu() {
        JMenu menu = new JMenu("Speed");

        for (Speed speed : Speed.values()) {
            menu.add(new ChangeSpeedAction(speed, preferences));
        }

        return menu;
    }

    private JMenu createScaleMenu() {
        JMenu menu = new JMenu("Scale");

        for (Scale scale : Scale.values()) {
            menu.add(new ChangeScaleAction(scale, preferences));
        }

        return menu;
    }

    private void createHelpMenu() {
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new ShowAboutDialogAction(this));
        menuBar.add(helpMenu);
    }

    private void createRenderingCanvas() {
        renderingCanvas = new RenderingCanvas(cpu.getDisplay(), preferences.getScale().cellSize());
        add(renderingCanvas);
    }
}
