package com.blogspot.bubelov.chipx.core.preferences;

import com.blogspot.bubelov.chipx.core.Scale;
import com.blogspot.bubelov.chipx.core.Speed;
import com.blogspot.bubelov.chipx.core.input.Key;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 12:36 PM
 */
public class Preferences {
    private Speed speed;
    private Scale scale;
    private Map<Key, Integer> keyMapping;
    private List<PreferenceListener> listeners;

    public Preferences() {
        keyMapping = new HashMap<Key, Integer>();
        listeners = new ArrayList<PreferenceListener>();
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        if (!speed.equals(this.speed)) {
            this.speed = speed;
            notifySpeedChanged();
        }
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        if (!scale.equals(this.scale)) {
            this.scale = scale;
            notifyScaleChanged();
        }
    }

    public Map<Key, Integer> getKeyMapping() {
        return keyMapping;
    }

    public void addListener(PreferenceListener listener) {
        this.listeners.add(listener);
    }

    private void notifySpeedChanged() {
        for (PreferenceListener listener : listeners) {
            listener.speedChanged();
        }
    }

    private void notifyScaleChanged() {
        for (PreferenceListener listener : listeners) {
            listener.scaleChanged();
        }
    }
}
