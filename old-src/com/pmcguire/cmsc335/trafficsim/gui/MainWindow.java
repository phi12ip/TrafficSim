package com.pmcguire.cmsc335.trafficsim.gui;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.Simulator;
import com.pmcguire.cmsc335.trafficsim.timer.ClockTimer;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

public class MainWindow extends JFrame
{
    private static final String TITLE = "Traffic Simulator";
    private JSplitPane mainPane;                // the main plane for the window
    private TimerToolbar timerToolbar;          // toolbar with timer display and controls
    private LightsDisplay lightsDisplay;        // area where light info is displayed
    private CarsDisplay carsDisplay;            // area where car info is displayed
    private final ClockTimer timer;                   // this will be set by the setTimer() method
    private final Simulator simulator;                // has information about the simulation. i.e cars, roads, intersections

    public MainWindow(Simulator simulator)
    {
        super(TITLE);
        configWindow();
        initializeDisplays();

        this.simulator = simulator;
        this.timer = simulator.getTimer();
        simulator.setLightsDisplay(lightsDisplay);
        simulator.setCarDisplay(carsDisplay);

        setJMenuBar(new MenuBar(simulator).createMenuBar());
        addTimerToolbar();

        createMainSplitPane();
        addPanelsToMainPane();
        addMainPane();
        pack();

    }


    private void configWindow()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 800));
    }

    public void addTimerToolbar()
    {
        timerToolbar = new TimerToolbar(timer);
        add(timerToolbar, BorderLayout.NORTH);
    }

    private void createMainSplitPane()
    {
        mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        mainPane.setOneTouchExpandable(true);
        mainPane.setResizeWeight(0.5);
    }

    public void initializeDisplays()
    {
        lightsDisplay = new LightsDisplay();
        carsDisplay = new CarsDisplay();
    }

    public void addPanelsToMainPane()
    {
        mainPane.setTopComponent(lightsDisplay);
        mainPane.setBottomComponent(carsDisplay);
    }

    public void addMainPane()
    {
        add(mainPane, BorderLayout.CENTER);
        pack();
    }

}
