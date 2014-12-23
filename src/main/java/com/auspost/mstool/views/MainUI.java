package com.auspost.mstool.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by managea on 10/12/2014.
 */
public class MainUI {
    private JTextField serverTextField;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton connectButton;
    private JTable mainTable;
    private JPanel mainPanel;

    public MainUI() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Print stuff in the Console!!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainUI");
        frame.setContentPane(new MainUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}


