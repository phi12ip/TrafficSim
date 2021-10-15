package com.pmcguire.cmsc335.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.light.Light;
import com.pmcguire.cmsc335.trafficsim.light.State;
import com.pmcguire.cmsc335.trafficsim.timer.TickListener;

import java.awt.*;
import java.util.ArrayList;

/**
 * Road
 */
public class Road implements TickListener, Runnable
{
    private final String name;                          // name of the road
    private final double LENGTH_BETWEEN_LIGHTS = 1000;  // distance between lights and initial starting length
    private double length;                              // current length of the road in meters

    private final Point startingPoint;                  // beginning of the road
    private final Point endingPoint;                    // current end of the road

    private final ArrayList<Car> waitingList;           // waiting list for cars to enter road
    private final ArrayList<Car> cars;                  // cars that are on the road
    private final ArrayList<Light> lights;              // lights that are relevant for this road
    private int ticks;

    public Thread thread;                               // thread the road is run on

    /**
     * Construct a Road with a given name
     *
     * @param roadName name of the road
     */
    public Road(String roadName)
    {
        name = roadName;                                // set road name
        length = LENGTH_BETWEEN_LIGHTS;                 // set initial length

        waitingList = new ArrayList<>();                // waiting list for cars to enter road
        cars = new ArrayList<>();                       // create cars list
        lights = new ArrayList<>();                     // create lights list

        startingPoint = new Point(0, 0);          // set start
        endingPoint = new Point((int) length, 0);    // set end

        thread = new Thread(this);
    }

    /**
     * Start the thread for the road
     *
     * @return this
     */
    public Road start()
    {
        thread.start();
        return this;
    }

    /**
     * Function run everytime a tick event happens for the timer
     *
     * @param ticks number of ticks
     */
    @Override
    public synchronized void handleTick(int ticks)
    {
        this.ticks = ticks;
        if (ticks == 0)
        {
//            waitingList.addAll(cars);
            while (!cars.isEmpty())
            {
                Car car = cars.remove(0);
                car.setLocation(startingPoint);
                car.setTextBoxContent();
                waitingList.add(car);
            }
        }
        notify();
    }

    /**
     * Run when the thread starts
     */
    @Override
    public synchronized void run()
    {
        boolean running = true;
        while (running)
        {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                System.out.println("Road thread ended with interrupt");
                running = false;
            }
            action();
        }
    }

    /**
     * Action run when thread is notified
     */
    public synchronized void action()
    {
        System.out.printf("[Road] %s received tick.\n", getName());

        if (ticks == 0)
            return;
        // Get list of Points that represent obstacles on the road: cars and red lights
        ArrayList<Point> roadObstacles = constructObstacleList();

        // If there is nothing at the starting point, move a car from the waiting list
        if (!roadObstacles.contains(startingPoint) && !waitingList.isEmpty())
            moveFromWaitingList(waitingList.get(0));

        // Update cars
        updateCars(roadObstacles);
    }

    /**
     * Add a car to the road's waiting list to then be added to the road
     *
     * @param car car to add to the waiting list
     */
    public synchronized void addCar(Car car)
    {
        // Add the car to the waiting list to be added to the actual road
        waitingList.add(car);
    }


    /**
     * Add light to the road
     *
     * @param light light to add
     */
    public synchronized void addLight(Light light)
    {
        // Put light at the end of the road
        light.setLocation(length, 0);
        lights.add(light);

        // Extend the road
        increaseLength();
    }

    /**
     * Construct the list of obstacles for this road
     *
     * @return the list of obstacles
     */
    private ArrayList<Point> constructObstacleList()
    {
        ArrayList<Point> obstacles = new ArrayList<>();

        // Add each car to obstacles
        for (Car car : cars)
        {
            obstacles.add((Point) car.clone());
        }

        // Add each light to obstacles if it is red
        for (Light light : lights)
        {
            if (light.getState() == State.RED)
            {
                obstacles.add((Point) light.clone());
            }
        }

        // Add the end of the road
        obstacles.add((Point) endingPoint.clone());

        return obstacles;
    }

    /**
     * Increase the length of the road when intersection is added
     */
    public void increaseLength()
    {
        length += LENGTH_BETWEEN_LIGHTS;
        endingPoint.setLocation(startingPoint.getX() + length, startingPoint.getY());
    }

    /**
     * Get the name of the road
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * How many lights are currently on the road
     *
     * @return light count
     */
    public synchronized int lightCount()
    {
        return lights.size();
    }

    /**
     * Move car from waiting list to the main road list
     *
     * @param car car to move
     */
    public synchronized void moveFromWaitingList(Car car)
    {
        // Set car location (redundant currently but may change)
        car.setLocation(startingPoint);
        // Add car to list
        waitingList.remove(car);
        cars.add(car);
    }

    /**
     * Prompt the cars on the road to update themselves using the obstacles
     *
     * @param obstacles obstacles on the road
     */
    private synchronized void updateCars(ArrayList<Point> obstacles)
    {
        System.out.println(name + " updating cars...");
        for (Car car : cars)
        {
            car.updateObstacles(obstacles);
            car.handleTick(ticks);
        }
    }

    @Override
    public String toString()
    {
        return String.format(
                "Road{name='%s', startingPoint=(%d, %d), endingPoint=(%d, %d)}",
                name,
                (int) startingPoint.getX(),
                (int) startingPoint.getY(),
                (int) endingPoint.getX(),
                (int) endingPoint.getY()
        );
    }
}

