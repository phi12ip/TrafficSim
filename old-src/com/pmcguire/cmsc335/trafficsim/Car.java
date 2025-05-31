package com.pmcguire.cmsc335.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.timer.TickListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Car
 */
public class Car extends Point implements TickListener, Runnable
{

    private final String name;                              // the car's name
    private ArrayList<Point> obstacles;                     // obstacles on the same road as the car
    private double speed;                                   // speed in meters per second ( 40 mph = 17.88m/s )
    public Thread thread;                                   // thread the car is run on

    private final int DEFAULT_TICK_INTERVAL = 1000;         // how long each tick is in ms
    private JTextArea GUITextArea;                          // text area output in the GUI

    /**
     * Constructor
     *
     * @param name name of the car
     */
    public Car(String name)
    {
        this.name = name;
        thread = new Thread(this);
        speed = Converter.milesPerHourToMetersPerSecond(60);
    }

    /**
     * Start the thread that runs the car
     *
     * @return this
     */
    public Car start()
    {
        thread.start();
        return this;
    }

    /**
     * Function run everytime a tick event happens for the timer
     * @param ticks number of ticks
     */
    @Override
    public synchronized void handleTick(int ticks)
    {
        // don't care about ticks because I'm controlled by the road
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
                System.out.println("Thread ended with interrupt");
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
        // Calculate where I want to go
        double tickDistanceTraveled = (speed * DEFAULT_TICK_INTERVAL / 1000);
        Point newPosition = new Point((int) (getX() + tickDistanceTraveled), (int) getY());

        // For each obstacle, determine if:
        for (Point obstacle : obstacles)
        {
            // (it is between where I am) && (where I want to go)
            double x = obstacle.getX();
            if (x > getX())
            {
                if ((x - 5) <= newPosition.getX())
                {
                    newPosition.setLocation(x - 5, newPosition.getY());
                }
            }


        }

        setLocation(newPosition);
        setTextBoxContent();
        System.out.printf("[Car] %s location updated.\n%s\n", getName(), this);
    }

    /**
     * Update GUI display output
     */
    public void setTextBoxContent()
    {
        GUITextArea.setText(this.toString());
    }

    /**
     * Get the name of this car
     *
     * @return the name
     */
    private String getName()
    {
        return name;
    }

    /**
     * Get the location of this car synchronously
     *
     * @return the location
     */
    @Override
    public synchronized Point getLocation()
    {
        return super.getLocation();
    }

    /**
     * Set the location of this car synchronously
     *
     * @param p location
     */
    @Override
    public synchronized void setLocation(Point p)
    {
        super.setLocation(p);
    }

    /**
     * Update the obstacles object of this car
     *
     * @param obstacles the obstacles
     */
    public synchronized void updateObstacles(ArrayList<Point> obstacles)
    {
        this.obstacles = obstacles;
    }

    /**
     * Set the reference to the text area in the GUI
     * @param textArea the text area
     */
    public void setTextArea(JTextArea textArea)
    {
        GUITextArea = textArea;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Car { name='%s', x=%d, y=%d }",
                getName(),
                (int) getX(),
                (int) getY()
        );
    }

    private static class Converter
    {
        public static double milesPerHourToMetersPerSecond(double mph)
        {
            return mph * 0.44704;
        }
    }
}
