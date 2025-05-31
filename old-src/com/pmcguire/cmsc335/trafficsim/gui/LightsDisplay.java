package com.pmcguire.cmsc335.trafficsim.gui;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.light.Light;

import javax.swing.*;
import java.awt.*;

/**
 * Holds light information in the GUI
 */
public class LightsDisplay extends JPanel
{

    /**
     * Constructor
     */
    public LightsDisplay()
    {
        super();
        setMinimumSize(new Dimension(100, 100));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel mainLabel = new JLabel("Lights");
        add(mainLabel);
    }

    /**
     * Add a connection between this display and a light object so light can update its status
     *
     * @param light light to add connection to
     */
    public void addLightView(Light light)
    {
        JTextArea textArea = new JTextArea();
        light.setTextArea(textArea);
        textArea.setText(light.toString());
        textArea.setEditable(false);
        add(textArea);
    }

}
