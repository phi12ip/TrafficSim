package com.pmcguire.cmsc335.trafficsim.timer;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer that runs on its own thread and emits an event every tick
 */
public class ClockTimer
{
    private final ArrayList<TickListener> listeners = new ArrayList<>();      // objects that listen to clock ticks

    private int ticks = 0;                          // the number of ticks since the start of the timer
    public final int TICK_MILLISECONDS = 1000;      // the amount of milliseconds in one tick
    public Timer timer = null;                      // timer object used for dispatching the timer on its own thread
    private boolean running;                        // the timer has been started

    /**
     * Start the timer
     */
    public void start()
    {
        if (running) // if already started
            return;

        // Create a new timer
        timer = new Timer();

        // Schedule task to run at fixed time
        timer.scheduleAtFixedRate(new Incrementer(), TICK_MILLISECONDS, TICK_MILLISECONDS);
        running = true;
    }

    /**
     * Stop the timer
     */
    public void stop()
    {
        // Pause removes timer task
        pause();

        // Reset time to zero and notify listeners
        ticks = 0;
        running = false;
        notifyListeners();
    }

    /**
     * Pause the timer
     */
    public void pause()
    {
        if (timer == null) // if timer isn't running
            return;

        cancelTimer();
    }

    /**
     * Restart the timer
     */
    public void restart()
    {
        stop();
        start();
    }

    /**
     * Removes timer
     */
    private void cancelTimer()
    {
        timer.cancel();
        timer = null;
        running = false;
    }

    /**
     * Add a listener to the timer
     *
     * @param listener listener to add
     */
    public void addListener(TickListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Notify class that implements TickListener to respond to a tick
     */
    private void notifyListeners()
    {
        for (TickListener listener : listeners)
        {
            listener.handleTick(ticks);
        }
    }

    /**
     * Return the time in seconds of the timer
     *
     * @return time in seconds
     */
    public double getSeconds()
    {
        return (double) ticks * TICK_MILLISECONDS / 1000;
    }

    /**
     * Inner class which is runs at a fixed interval to add 1 to the tick count
     */
    class Incrementer extends TimerTask
    {

        /**
         * This function is run at a fixed interval
         */
        @Override
        public void run()
        {
            // Update tick count
            ticks++;
            System.out.printf("\n[ClockTimer] Current time: %.2f seconds\n", getSeconds());

            // Send new tick count to each listener
            notifyListeners();
        }


    }


}
