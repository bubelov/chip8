package com.blogspot.bubelov.screens.keymapping;

import com.blogspot.bubelov.core.input.Key;
import com.blogspot.bubelov.core.preferences.Preferences;
import com.blogspot.bubelov.screens.actions.ApplyKeyMappingAction;
import com.blogspot.bubelov.screens.actions.CloseDialogAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Igor Bubelov
 * Date: 7/07/12 8:03 PM
 */
public class KeyMappingDialog extends JDialog {
    private Preferences preferences;
    private Map<Key, Integer> keyMapping;

    public KeyMappingDialog(Frame owner, Preferences preferences) {
        super(owner);
        this.preferences = preferences;
        keyMapping = new HashMap<Key, Integer>(preferences.getKeyMapping());
        initUI();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initUI() {
        initKeyboardPanel();
        initActionsPanel();
        pack();
    }

    private void initKeyboardPanel() {
        JPanel keyboardPanel = new JPanel(new GridLayout(4, 4));
        keyboardPanel.add(createPanelForKey(Key.KEY_1));
        keyboardPanel.add(createPanelForKey(Key.KEY_2));
        keyboardPanel.add(createPanelForKey(Key.KEY_3));
        keyboardPanel.add(createPanelForKey(Key.KEY_C));
        keyboardPanel.add(createPanelForKey(Key.KEY_4));
        keyboardPanel.add(createPanelForKey(Key.KEY_5));
        keyboardPanel.add(createPanelForKey(Key.KEY_6));
        keyboardPanel.add(createPanelForKey(Key.KEY_D));
        keyboardPanel.add(createPanelForKey(Key.KEY_7));
        keyboardPanel.add(createPanelForKey(Key.KEY_8));
        keyboardPanel.add(createPanelForKey(Key.KEY_9));
        keyboardPanel.add(createPanelForKey(Key.KEY_E));
        keyboardPanel.add(createPanelForKey(Key.KEY_A));
        keyboardPanel.add(createPanelForKey(Key.KEY_0));
        keyboardPanel.add(createPanelForKey(Key.KEY_B));
        keyboardPanel.add(createPanelForKey(Key.KEY_F));
        add(keyboardPanel);
    }

    private void initActionsPanel() {
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionsPanel.add(new JButton(new ApplyKeyMappingAction(preferences, keyMapping)));
        actionsPanel.add(new JButton(new CloseDialogAction(this)));
        add(actionsPanel, BorderLayout.SOUTH);
    }

    private JPanel createPanelForKey(final Key key) {
        JPanel keyPanel = new JPanel(new FlowLayout());
        keyPanel.add(new JLabel(key.name()));
        final JTextField codeTextField = new JTextField(3);
        codeTextField.setText(KeyEvent.getKeyText(keyMapping.get(key)));

        codeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                codeTextField.setSelectionStart(0);
                codeTextField.setSelectionEnd(codeTextField.getText().length());
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if (codeTextField.getText().isEmpty()) {
                    codeTextField.setText(KeyEvent.getKeyText(keyMapping.get(key)));
                }
            }
        });

        codeTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                codeTextField.setText("");
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                keyMapping.put(key, keyEvent.getKeyCode());
            }
        });

        keyPanel.add(codeTextField);
        return keyPanel;
    }
}
