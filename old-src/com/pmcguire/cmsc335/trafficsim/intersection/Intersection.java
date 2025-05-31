package com.pmcguire.cmsc335.trafficsim.intersection;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.timer.TickListener;

import javax.swing.*;

/**
 * Abstract Intersection class
 */
public abstract class Intersection implements TickListener, Runnable
{
    protected String name = "INTERSECTION DEFAULT";           // name for the intersection
    protected final int DEFAULT_TOTAL_CYCLE_LENGTH = 60_000;  // default total length of one light cycle in ms
    protected final int DEFAULT_MAJOR_LENGTH = 20_000;        // default length of major light out of full cycle in ms
    protected final int DEFAULT_INTERVAL = 3_000;             // default interval when changing light modes in ms
    protected final int DEFAULT_TICK_LENGTH = 1000;           // default length of one tick in ms

    protected int currentTicks;                               // current ticks
    protected JTextArea GUITextArea;                          // text area connected in the GUI

    public Thread thread;                                     // thread the intersection is run on

    /**
     * Constructor
     */
    public Intersection()
    {
        currentTicks = 0;
        thread = new Thread(this);
    }

    /**
     * Start the intersection thread
     *
     * @return this
     */
    public Intersection start()
    {
        thread.start();
        return this;
    }

    /**
     * Get the name of the Intersection
     *
     * @return the name of the Intersection
     */
    public String getName()
    {
        return name;
    }

    /**
     * Tick Handler when an Intersection receives a tick
     *
     * @param ticks current ticks sent from the clock
     */
    @Override
    public synchronized void handleTick(int ticks)
    {
        currentTicks = ticks;
        notify();
    }

    /**
     * Run loop for Intersection. Waits on tick and then updates lights
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
                System.out.println("Thread ended with interrupt. Stopping.");
                running = false;
            }
            updateLights();
        }
    }

    /**
     * Update the state of the lights based on the currentTicks
     */
    abstract protected void updateLights();

}
