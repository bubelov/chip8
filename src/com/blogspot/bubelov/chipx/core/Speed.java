package com.blogspot.bubelov.chipx.core;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 12:39 PM
 */
public enum Speed {
    PERCENT_50,
    PERCENT_100,
    PERCENT_200;

    public int delayMillis() {
        switch (this) {
            case PERCENT_50:
                return 12;
            case PERCENT_100:
                return 6;
            case PERCENT_200:
                return 3;
            default:
                throw new UnsupportedOperationException("Unknown speed:" + name());
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case PERCENT_50:
                return "50%";
            case PERCENT_100:
                return "100%";
            case PERCENT_200:
                return "200%";
            default:
                throw new UnsupportedOperationException("Unknown speed:" + name());
        }
    }
}
