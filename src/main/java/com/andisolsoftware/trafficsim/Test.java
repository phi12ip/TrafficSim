package com.andisolsoftware.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.andisolsoftware.trafficsim.gui.MainWindow;
import com.andisolsoftware.trafficsim.intersection.OneRoadIntersection;
import com.andisolsoftware.trafficsim.timer.ClockTimer;

/**
 * Testing class used to design and build out the API and implementation
 */
public class Test
{
    public static void main(String[] args)
    {
        integratingSimulator();
    }

    public static void integratingSimulator()
    {
        // Create a timer
        ClockTimer timer = new ClockTimer();

        // Create new simulator
        Simulator sim = new Simulator(timer);

        // Create main window
        MainWindow window = new MainWindow(sim);

        window.setVisible(true);

        // Create a road
        Road road = new Road("Road 1").start();
        sim.addRoad(road);

        // Create two cars
        Car carA = new Car("Car A").start();
        Car carB = new Car("Car B").start();

        // Add them to the road
        sim.addCar(carA);
        sim.addCar(carB);

        // Create intersection with reference to road
        OneRoadIntersection intersectionA = new OneRoadIntersection(road).start();

        // Create and second intersection with reference to road
        OneRoadIntersection intersectionB = new OneRoadIntersection(road).start();

        sim.addIntersection(intersectionA);
        sim.addIntersection(intersectionB);

    }

}
