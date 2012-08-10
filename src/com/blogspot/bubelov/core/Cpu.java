package com.blogspot.bubelov.core;

import com.blogspot.bubelov.core.input.Key;
import com.blogspot.bubelov.core.input.Keyboard;

import java.io.Serializable;
import java.util.Arrays;

import static com.blogspot.bubelov.Constants.*;

/**
 * Author: Igor Bubelov
 * Date: 7/1/12 1:40 PM
 */
public class Cpu implements Serializable {
    private int[] ram;
    private int[] generalRegisters;
    private int addressRegister;
    private int delayRegister;
    private int soundRegister;
    private int programCounter;
    private int[] stack;
    private int stackPointer;
    private Keyboard keyboard;
    private boolean[] display;
    private Rom currentRom;
    private CpuListener listener;

    public Cpu() {
        ram = new int[RAM_SIZE_BYTES];
        generalRegisters = new int[GENERAL_REGISTERS_AMOUNT];
        stack = new int[STACK_SIZE];
        keyboard = new Keyboard();
        display = new boolean[DISPLAY_WIDTH * DISPLAY_HEIGHT];

        for(int i = 0; i < FONT.length; i++) {
            ram[i] = FONT[i];
        }
    }

    public boolean[] getDisplay() {
        return display;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setListener(CpuListener listener) {
        this.listener = listener;
    }

    public void clear() {
        Arrays.fill(ram, PROGRAM_START_ADDRESS, ram.length, 0);
        Arrays.fill(generalRegisters, 0);
        Arrays.fill(stack, 0);
        keyboard.reset();
        Arrays.fill(display, false);
        addressRegister = 0;
        programCounter = currentRom.getStartAddress();
        stackPointer = 0;
        delayRegister = 0;
        soundRegister = 0;
    }

    public void load(Rom rom) {
        this.currentRom = rom;
        clear();

        for (int i = 0; i < rom.getData().length; i++) {
            ram[rom.getStartAddress() + i] = rom.getData()[i];
        }

        if (listener != null) {
            listener.romChanged();
        }
    }

    public void reset() {
        load(currentRom);
    }

    public void cycle() {
        int opCode = (ram[programCounter] << 8) | ram[programCounter + 1];
        programCounter += 2;

        for (int i = 0; i < 16; i++) {
            if (generalRegisters[i] > 255 || generalRegisters[i] < 0) {
                throw new RuntimeException("Address range violation!");
            }
        }

        if (addressRegister > 0xFFF) {
            throw new RuntimeException("Address range violation!");
        }

        switch (opCode & 0xF000) {
            case 0x0000: {
                switch (opCode & 0x000F) {
                    case 0x0000:
                        op00E0();
                        break;

                    case 0x000E:
                        op00EE();
                        break;

                    default:
                        throw new RuntimeException(String.format("Unknown opCode: %4X", opCode));
                }

                break;
            }

            case 0x1000:
                op1NNN(opCode);
                break;

            case 0x2000:
                op2NNN(opCode);
                break;

            case 0x3000:
                op3XNN(opCode);
                break;

            case 0x4000:
                op4XNN(opCode);
                break;

            case 0x5000:
                op5XY0(opCode);
                break;

            case 0x6000:
                op6XNN(opCode);
                break;

            case 0x7000:
                op7XNN(opCode);
                break;

            case 0x8000:
                switch (opCode & 0x000F) {
                    case 0x0000:
                        op8XY0(opCode);
                        break;

                    case 0x0001:
                        op8XY1(opCode);
                        break;

                    case 0x0002:
                        op8XY2(opCode);
                        break;

                    case 0x0003:
                        op8XY3(opCode);
                        break;

                    case 0x0004:
                        op8XY4(opCode);
                        break;

                    case 0x0005:
                        op8XY5(opCode);
                        break;

                    default:
                        throw new RuntimeException(String.format("Unknown opCode: %4X", opCode));
                }

                break;

            case 0x9000:
                op9XY0(opCode);
                break;

            case 0xA000:
                opANNN(opCode);
                break;

            case 0xB000:
                opBNNN(opCode);
                break;

            case 0xC000:
                opCXNN(opCode);
                break;

            case 0xD000:
                opDXYN(opCode);
                break;

            case 0xE000:
                switch (opCode & 0x000F) {
                    case 0x000E:
                        opEX9E(opCode);
                        break;

                    case 0x0001:
                        opEXA1(opCode);
                        break;

                    default:
                        throw new RuntimeException(String.format("Unknown opCode: %4X", opCode));
                }

                break;

            case 0xF000:
                switch (opCode & 0x00FF) {
                    case 0x0007:
                        opFX07(opCode);
                        break;

                    case 0x000A:
                        opFX0A(opCode);
                        break;

                    case 0x0015:
                        opFX15(opCode);
                        break;

                    case 0x0018:
                        opFX18(opCode);
                        break;

                    case 0x001E:
                        opFX1E(opCode);
                        break;

                    case 0x0029:
                        opFX29(opCode);
                        break;

                    case 0x0033:
                        opFX33(opCode);
                        break;

                    case 0x0055:
                        opFX55(opCode);
                        break;

                    case 0x0065:
                        opFX65(opCode);
                        break;

                    default:
                        throw new RuntimeException(String.format("Unknown opCode: %4X", opCode));
                }

                break;

            default:
                throw new RuntimeException(String.format("Unknown opCode: %4X", opCode));
        }

        if (delayRegister > 0) {
            delayRegister--;
        }

        if (soundRegister > 0) {
            soundRegister--;

            if (soundRegister == 0) {
                // BEEP!
            }
        }
    }

    /**
     * Clears the screen
     */
    private void op00E0() {
        Arrays.fill(display, false);
    }

    /**
     * Returns from a subroutine
     */
    private void op00EE() {
        stackPointer--;
        programCounter = stack[stackPointer];
    }

    /**
     * Jumps to address NNN
     */
    private void op1NNN(int opCode) {
        programCounter = opCode & 0x0FFF;
    }

    /**
     * Calls subroutine at NNN
     */
    private void op2NNN(int opCode) {
        stack[stackPointer] = programCounter;
        stackPointer++;
        programCounter = opCode & 0x0FFF;
    }

    /**
     * Skips the next instruction if VX equals NN
     */
    private void op3XNN(int opCode) {
        int vx = generalRegisters[(opCode & 0x0F00) >> 8];
        int nn = opCode & 0x00FF;

        if (vx == nn) {
            programCounter += 2;
        }
    }

    /**
     * Skips the next instruction if VX doesn't equal NN
     */
    private void op4XNN(int opCode) {
        int vx = generalRegisters[(opCode & 0x0F00) >> 8];
        int nn = opCode & 0x00FF;

        if (vx != nn) {
            programCounter += 2;
        }
    }

    /**
     * Skips the next instruction if VX equals VY
     */
    private void op5XY0(int opCode) {
        int vx = generalRegisters[(opCode & 0x0F00) >> 8];
        int vy = generalRegisters[(opCode & 0x00F0) >> 4];

        if (vx == vy) {
            programCounter += 2;
        }
    }

    /**
     * Sets VX to NN
     */
    private void op6XNN(int opCode) {
        int nn = opCode & 0x00FF;
        generalRegisters[(opCode & 0x0F00) >> 8] = nn;
    }

    /**
     * Adds NN to VX
     */
    private void op7XNN(int opCode) {
        int nn = opCode & 0x00FF;
        int vx = generalRegisters[(opCode & 0x0F00) >> 8];
        generalRegisters[(opCode & 0x0F00) >> 8] = (vx + nn) % 256;
    }

    /**
     * Sets VX to the value of VY
     */
    private void op8XY0(int opCode) {
        int vy = generalRegisters[(opCode & 0x00F0) >> 4];
        generalRegisters[(opCode & 0x0F00) >> 8] = vy;
    }

    /**
     * Sets VX to VX or VY
     */
    private void op8XY1(int opCode) {
        generalRegisters[(opCode & 0x0F00) >> 8] |= generalRegisters[(opCode & 0x00F0) >> 4];
    }

    /**
     * Sets VX to VX and VY
     */
    private void op8XY2(int opCode) {
        generalRegisters[(opCode & 0x0F00) >> 8] &= generalRegisters[(opCode & 0x00F0) >> 4];
    }

    /**
     * Sets VX to VX xor VY
     */
    private void op8XY3(int opCode) {
        generalRegisters[(opCode & 0x0F00) >> 8] ^= generalRegisters[(opCode & 0x00F0) >> 4];
    }

    /**
     * Adds VY to VX. VF is set to 1 when there's a carry, and to 0 when there isn't
     */
    private void op8XY4(int opCode) {
        int sum = generalRegisters[(opCode & 0x0F00) >> 8] + generalRegisters[(opCode & 0x00F0) >> 4];
        generalRegisters[0xF] = sum > 0xFF ? 1 : 0;
        generalRegisters[(opCode & 0x0F00) >> 8] = sum % 256;
    }

    /**
     * VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1 when there isn't
     */
    private void op8XY5(int opCode) {
        int vy = generalRegisters[(opCode & 0x00F0) >> 4];
        generalRegisters[0xF] = generalRegisters[(opCode & 0x0F00) >> 8] > vy ? 1 : 0;
        generalRegisters[(opCode & 0x0F00) >> 8] -= generalRegisters[(opCode & 0x00F0) >> 4];

        if (generalRegisters[(opCode & 0x0F00) >> 8] < 0) {
            generalRegisters[(opCode & 0x0F00) >> 8] += 256;
        }
    }

    /**
     * Skips the next instruction if VX doesn't equal VY
     */
    private void op9XY0(int opCode) {
        int vx = generalRegisters[(opCode & 0x0F00) >> 8];
        int vy = generalRegisters[(opCode & 0x00F0) >> 4];

        if (vx != vy) {
            programCounter += 2;
        }
    }

    /**
     * Sets I to the address NNN
     */
    private void opANNN(int opCode) {
        addressRegister = (opCode & 0x0FFF);
    }

    /**
     * Jumps to the address NNN plus V0
     */
    private void opBNNN(int opCode) {
        programCounter = ((opCode & 0x0FFF) + generalRegisters[0]) % (0xFFF + 1);
    }

    /**
     * Sets VX to a random number and NN
     */
    private void opCXNN(int opCode) {
        int nn = opCode & 0x00FF;
        generalRegisters[(opCode & 0x0F00) >> 8] = ((int)(Math.random() * 0xFF)) & nn;
    }

    /**
     * Draws a sprite at coordinate (VX, VY) that has a width of 8 pixels and a height of N pixels.
     * Each row of 8 pixels is read as bit-coded (with the most significant bit of each byte displayed on the left)
     * starting from memory location I; I value doesn't change after the execution of this instruction.
     * As described above, VF is set to 1 if any screen pixels are flipped from set to unset when the sprite
     * is drawn, and to 0 if that doesn't happen
     */
    private void opDXYN(int opCode) {
        int x = generalRegisters[(opCode & 0x0F00) >> 8];
        int y = generalRegisters[(opCode & 0x00F0) >> 4];
        int h = opCode & 0x000F;

        generalRegisters[0xF] = 0;

        for (int row = 0; row < h; row++) {
            int rowByte = ram[addressRegister + row];

            for (int col = 0; col < 8; col++) {
                if ((rowByte & (0x80 >> col)) != 0) {
                    int index = x + col + ((y + row) * 64);

                    if (index >= display.length) {
                        index %= display.length;
                    }

                    if (display[index]) {
                        generalRegisters[0xF] = 1;
                    }

                    display[index] = !display[index];
                }
            }
        }
    }

    /**
     * Skips the next instruction if the key stored in VX is pressed
     */
    private void opEX9E(int opCode) {
        int keyCode = generalRegisters[(opCode & 0x0F00) >> 8];
        Key key = Key.convert(keyCode);

        if (keyboard.isPressed(key)) {
            programCounter += 2;
        }
    }

    /**
     * Skips the next instruction if the key stored in VX isn't pressed
     */
    private void opEXA1(int opCode) {
        int keyCode = generalRegisters[(opCode & 0x0F00) >> 8];
        Key key = Key.convert(keyCode);

        if (!keyboard.isPressed(key)) {
            programCounter += 2;
        }
    }

    /**
     * Sets VX to the value of the delay timer
     */
    private void opFX07(int opCode) {
        generalRegisters[(opCode & 0x0F00) >> 8] = delayRegister;
    }

    /**
     * A key press is awaited, and then stored in VX
     */
    private void opFX0A(int opCode) {
        boolean keyPressed = false;

        for (Key key : Key.values()) {
            if (keyboard.isPressed(key)) {
                generalRegisters[(opCode & 0x0F00) >> 8] = key.ordinal();
                keyPressed = true;
                break;
            }
        }

        if(!keyPressed) {
            programCounter -= 2;
        }
    }

    /**
     * FX15 Sets the delay timer to VX
     */
    private void opFX15(int opCode) {
        delayRegister = generalRegisters[(opCode & 0x0F00) >> 8];
    }

    /**
     * FX18 Sets the sound timer to VX
     */
    private void opFX18(int opCode) {
        soundRegister = generalRegisters[(opCode & 0x0F00) >> 8];
    }

    /**
     * Adds VX to I
     */
    private void opFX1E(int opCode) {
        int sum = addressRegister + generalRegisters[(opCode & 0x0F00) >> 8];

        if (sum > 0xFFF) {
            generalRegisters[0xF] = 1;
            sum %= (0xFFF + 1);
        } else {
            generalRegisters[0xF] = 0;
        }

        addressRegister = sum;
    }

    /**
     * Sets I to the location of the sprite for the character in VX.
     * Characters 0-F (in hexadecimal) are represented by a 4x5 font
     */
    private void opFX29(int opCode) {
        int charIndex = generalRegisters[(opCode & 0x0F00) >> 8];
        addressRegister = charIndex * 5;
    }

    /**
     * Stores the Binary-coded decimal representation of VX, with the most
     * significant of three digits at the address in I, the middle digit
     * at I plus 1, and the least significant digit at I plus 2
     */
    private void opFX33(int opCode) {
        int value = generalRegisters[(opCode & 0x0F00) >> 8];
        ram[addressRegister] = value % 1000  / 100;
        ram[addressRegister + 1] = value % 100 / 10;
        ram[addressRegister + 2] = value % 10;
    }

    /**
     * Stores V0 to VX in memory starting at address I
     */
    private void opFX55(int opCode) {
        int x = (opCode & 0x0F00) >> 8;
        System.arraycopy(generalRegisters, 0, ram, addressRegister, x + 1);
    }

    /**
     * Fills V0 to VX with values from memory starting at address I
     */
    private void opFX65(int opCode) {
        int x = (opCode & 0x0F00) >> 8;
        System.arraycopy(ram, addressRegister, generalRegisters, 0, x + 1);
    }
}
