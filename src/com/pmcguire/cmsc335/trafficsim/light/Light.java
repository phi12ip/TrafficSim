package com.pmcguire.cmsc335.trafficsim.light;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import javax.swing.*;
import java.awt.*;

/**
 * Traffic Light
 */
public class Light extends Point
{
    private volatile State state = State.GREEN;      // the state of the light
    private JTextArea GUITextArea;

    /**
     * Return the current state of the light
     *
     * @return state of the light
     */
    public synchronized State getState()
    {
        return state;
    }

    /**
     * Set the current state of the light
     *
     * @param state current state of the light
     */
    public synchronized void setState(State state)
    {
        this.state = state;
    }

    /**
     * Set the textarea reference for the GUI
     * @param textArea textArea reference
     */
    public void setTextArea(JTextArea textArea)
    {
        GUITextArea = textArea;
    }

    /**
     * Update the contents of the textArea
     */
    public void setTextBoxContent()
    {
        GUITextArea.setText(this.toString());
    }

    @Override
    public synchronized String toString()
    {
        return String.format(
                "Light { state='%s', location=(%d, %d) }",
                getState(),
                (int) getX(),
                (int) getY()
        );
    }


}
