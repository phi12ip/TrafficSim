package com.pmcguire.cmsc335.trafficsim.gui;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.timer.ClockTimer;
import com.pmcguire.cmsc335.trafficsim.timer.TickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerToolbar extends JPanel implements ActionListener, TickListener
{
    private static final String START = "START";
    private static final String PAUSE = "Pause";
    private static final String STOP = "STOP";
    private static final String RESTART = "RESTART";

    private final ClockTimer timer;
    private final JLabel timerDisplay;

    public TimerToolbar(ClockTimer timer)
    {
        // Config
        super(new BorderLayout());

        this.timer = timer;
        JToolBar toolbar = new JToolBar("Timer Control");
        JButton button;

        // Add Start button
        button = makeButton("Start", START);
        toolbar.add(button);

        // Add Pause button
        button = makeButton("Pause", PAUSE);
        toolbar.add(button);

        // Add Stop button
        button = makeButton("Stop", STOP);
        toolbar.add(button);

        // Add Restart button
        button = makeButton("Restart", RESTART);
        toolbar.add(button);

        // Add Timer Display
        timerDisplay = new JLabel();
        timerDisplay.setText(String.format("%.2f seconds", (float) timer.getSeconds()));

        timer.addListener(this);
        toolbar.add(timerDisplay);

        add(toolbar);

    }

    private JButton makeButton(String text, String actionCommand)
    {
        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.addActionListener(this);
        button.setText(text);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        System.out.println(actionEvent.getActionCommand());

        switch (actionEvent.getActionCommand())
        {
            case START:
                timer.start();
                break;
            case PAUSE:
                timer.pause();
                break;
            case STOP:
                timer.stop();
                break;
            case RESTART:
                timer.restart();
                break;
        }
    }

    @Override
    public void handleTick(int ticks)
    {
        timerDisplay.setText(String.format("%.2f seconds", (float) timer.getSeconds()));
    }
}
