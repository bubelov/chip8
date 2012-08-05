package com.blogspot.bubelov.core.input;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Igor Bubelov
 * Date: 17/07/12 9:04 PM
 */
public class Keyboard implements Serializable {
    private Map<Key, Boolean> keyState;

    public Keyboard() {
        keyState = new HashMap<Key, Boolean>();
        reset();
    }

    public void reset() {
        for (Key key : Key.values()) {
            keyState.put(key, false);
        }
    }

    public Boolean isPressed(Key key) {
        return keyState.get(key);
    }

    public void pressKey(Key key) {
        keyState.put(key, true);
    }

    public void releaseKey(Key key) {
        keyState.put(key, false);
    }
}
