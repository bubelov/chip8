package com.chip8.core;

import java.io.Serializable;

import static com.chip8.Constants.*;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 11:07 PM
 */
public class Rom implements Serializable {
    private int[] data;
    private int startAddress;

    public Rom(byte[] data) {
        this(data, PROGRAM_START_ADDRESS);
    }

    public Rom(byte[] data, int startAddress) {
        this.data = new int[data.length];

        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i] & 0xFF;
        }

        this.startAddress = startAddress;
    }

    public int[] getData() {
        return data;
    }

    public int getStartAddress() {
        return startAddress;
    }
}
