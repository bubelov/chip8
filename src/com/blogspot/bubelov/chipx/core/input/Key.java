package com.blogspot.bubelov.chipx.core.input;

/**
 * Author: Igor Bubelov
 * Date: 17/07/12 9:01 PM
 */
public enum Key {
    KEY_0, KEY_1, KEY_2, KEY_3,
    KEY_4, KEY_5, KEY_6, KEY_7,
    KEY_8, KEY_9, KEY_A, KEY_B,
    KEY_C, KEY_D, KEY_E, KEY_F;

    public static Key convert(int keyCode) {
        switch (keyCode) {
            case 0x0:
                return KEY_0;
            case 0x1:
                return KEY_1;
            case 0x2:
                return KEY_2;
            case 0x3:
                return KEY_3;
            case 0x4:
                return KEY_4;
            case 0x5:
                return KEY_5;
            case 0x6:
                return KEY_6;
            case 0x7:
                return KEY_7;
            case 0x8:
                return KEY_8;
            case 0x9:
                return KEY_9;
            case 0xA:
                return KEY_A;
            case 0xB:
                return KEY_B;
            case 0xC:
                return KEY_C;
            case 0xD:
                return KEY_D;
            case 0xE:
                return KEY_E;
            case 0xF:
                return KEY_F;
            default:
                throw new RuntimeException("Invalid key code: " + keyCode);
        }
    }
}