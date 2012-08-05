package com.chip8.screens.main;

import java.awt.*;

import static com.chip8.Constants.*;

/**
 * Author: Igor Bubelov
 * Date: 16/07/12 10:29 PM
 */
public class RenderingCanvas extends Canvas {
    private boolean[] display;
    private int cellSize;
    private Graphics2D graphics;

    public RenderingCanvas(boolean[] display, int cellSize) {
        this.display = display;
        adjustSize(cellSize);
    }

    @Override
    public void paint(Graphics g) {
        graphics = (Graphics2D)g;
        createBufferStrategyIfNotExists();
        fillWithColor(Color.black);
        renderDisplayStateWithColor(Color.white);
    }

    public void adjustSize(int cellSize) {
        if (this.cellSize != cellSize) {
            this.cellSize = cellSize;
            setSize(cellSize * DISPLAY_WIDTH, cellSize * DISPLAY_HEIGHT);
        }
    }

    private void createBufferStrategyIfNotExists() {
        if (getBufferStrategy() == null) {
            createBufferStrategy(2);
        }
    }

    private void fillWithColor(Color fillColor) {
        graphics.setColor(fillColor);
        graphics.fillRect(0, 0, getWidth(), getHeight());
    }

    private void renderDisplayStateWithColor(Color renderColor) {
        graphics.setColor(renderColor);
        renderDisplayState();
    }

    private void renderDisplayState() {
        for (int i = 0; i < DISPLAY_WIDTH * DISPLAY_HEIGHT; i++) {
            if (display[i]) {
                graphics.fillRect((i % DISPLAY_WIDTH) * cellSize, (i / DISPLAY_WIDTH) * cellSize, cellSize, cellSize);
            }
        }
    }
}
