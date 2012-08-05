package com.chip8.core.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

/**
 * Author: Igor Bubelov
 * Date: 3/07/12 10:45 PM
 */
public class InputHandler extends KeyAdapter {
    private Keyboard keyboard;
    private Map<Key, Integer> keyMapping;

    public InputHandler(Keyboard keyboard, Map<Key, Integer> keyMapping) {
        this.keyboard = keyboard;
        this.keyMapping = keyMapping;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (keyMapping.containsValue(event.getKeyCode())) {
            keyboard.pressKey(getKey(event.getKeyCode()));
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (keyMapping.containsValue(event.getKeyCode())) {
            keyboard.releaseKey(getKey(event.getKeyCode()));
        }
    }

    private Key getKey(Integer code) {
        for (Key key : keyMapping.keySet()) {
            if (keyMapping.get(key).equals(code)) {
                return key;
            }
        }

        throw new RuntimeException("Unknown key code: " + code);
    }
}
