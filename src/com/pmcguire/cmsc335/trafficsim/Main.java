package com.pmcguire.cmsc335.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.gui.MainWindow;
import com.pmcguire.cmsc335.trafficsim.timer.ClockTimer;

public class Main
{

    public static void main(String[] args)
    {

        // Create the global timer instance that will run the toolbar,
        // the lights, and the cars
        ClockTimer timer = new ClockTimer();

        // Create Simulator object that will hold references to all Cars,
        // Roads and Intersections
        Simulator simulator = new Simulator(timer);

        // Create main window
        MainWindow window = new MainWindow(simulator);

        // Create road
        simulator.addRoad();

        // Create cars
        simulator.addCar();
        simulator.addCar();
        simulator.addCar();
        simulator.addCar();

        // Add intersections
        simulator.addIntersection();
        simulator.addIntersection();

        // Show window
        window.setVisible(true);
    }


}

