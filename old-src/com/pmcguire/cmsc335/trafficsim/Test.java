package com.pmcguire.cmsc335.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.gui.MainWindow;
import com.pmcguire.cmsc335.trafficsim.intersection.OneRoadIntersection;
import com.pmcguire.cmsc335.trafficsim.timer.ClockTimer;
import com.pmcguire.cmsc335.trafficsim.timer.Runner;

/**
 * Testing class used to design and build out the API and implementation
 */
public class Test
{
    public static void main(String[] args)
    {
//        testTimerThread();
//        testIntersection();
//        testCar();
        integratingSimulator();
    }

    private static void testTimerThread()
    {
        // Create a ClockTimer
        ClockTimer timer = new ClockTimer();

        // Create new Runner
        Runner runner = new Runner();
        Thread t = new Thread(runner);
        t.start();

        // Add timer listener
        timer.addListener(runner);

        timer.start();
    }

    public static void testIntersection()
    {

        // Create a timer
        ClockTimer timer = new ClockTimer();

        // Create a road
        Road majorRoad = new Road("Road 1");

        // Start thread for road
        Thread roadThread = new Thread(majorRoad);
        roadThread.start();

        // Create intersection with reference to road
        OneRoadIntersection intersectionA = new OneRoadIntersection(majorRoad);

        Thread intersectionThreadA = new Thread(intersectionA);
        intersectionThreadA.start();

        // Create and second intersection with reference to road
        OneRoadIntersection intersectionB = new OneRoadIntersection(majorRoad);

        Thread intersectionThreadB = new Thread(intersectionB);
        intersectionThreadB.start();

        // Add listeners
        timer.addListener(majorRoad);
        timer.addListener(intersectionA);
        timer.addListener(intersectionB);
        timer.start();

    }

    public static void testCar()
    {
        // Create a timer
        ClockTimer timer = new ClockTimer();

        // Create a road
        Road road = new Road("Road 1").start();

//        // Start thread for road
//        Thread roadThread = new Thread(road);
//        roadThread.start();

        // Create two cars
        Car carA = new Car("Car A").start();
        Car carB = new Car("Car B").start();

//        Thread carThreadA = new Thread(carA);
//        carThreadA.start();
//        Thread carThreadB = new Thread(carB);
//        carThreadB.start();

        // Add them to the road
        road.addCar(carA);
        road.addCar(carB);

        // Create intersection with reference to road
        OneRoadIntersection intersectionA = new OneRoadIntersection(road).start();

//        Thread intersectionThreadA = new Thread(intersectionA);
//        intersectionThreadA.start();

        // Create and second intersection with reference to road
        OneRoadIntersection intersectionB = new OneRoadIntersection(road).start();

//        Thread intersectionThreadB = new Thread(intersectionB);
//        intersectionThreadB.start();

        // Add listeners
        timer.addListener(road);
        timer.addListener(intersectionA);
        timer.addListener(intersectionB);
        timer.start();


    }

    public static void integratingSimulator()
    {
        // Create a timer
        ClockTimer timer = new ClockTimer();

        // Create new simulator
        Simulator sim = new Simulator(timer);

        // Create main window
        MainWindow window = new MainWindow(sim);

//        CarsDisplay carsDisplay = window.getCarsDisplay();


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

        // Add them to the GUI display
//        carsDisplay.addCarView(carA);
//        carsDisplay.addCarView(carB);

        // Create intersection with reference to road
        OneRoadIntersection intersectionA = new OneRoadIntersection(road).start();

        // Create and second intersection with reference to road
        OneRoadIntersection intersectionB = new OneRoadIntersection(road).start();

        sim.addIntersection(intersectionA);
        sim.addIntersection(intersectionB);


    }

}
