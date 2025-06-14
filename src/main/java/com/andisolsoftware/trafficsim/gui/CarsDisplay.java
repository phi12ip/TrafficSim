package com.andisolsoftware.trafficsim.gui;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.andisolsoftware.trafficsim.car.Car;

import javax.swing.*;
import java.awt.*;

/**
 * Holds Car information in the GUI
 */
public class CarsDisplay extends JPanel
{

    /**
     * Constructor
     */
    public CarsDisplay()
    {
        super();
        setMinimumSize(new Dimension(100, 100));

        // Add Main Label
        JLabel mainLabel = new JLabel("Cars\n");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(mainLabel);
    }

    /**
     * Add a connection between this display and a car object so car can update its status
     * @param car car to connect to
     */
    public void addCarView(Car car)
    {
        JTextArea textArea = new JTextArea();
        car.setTextArea(textArea);
        textArea.setText(car.toString());
        textArea.setEditable(false);
        add(textArea);
    }

}
