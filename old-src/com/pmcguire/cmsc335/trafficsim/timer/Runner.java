package com.pmcguire.cmsc335.trafficsim.timer;

import com.pmcguire.cmsc335.trafficsim.timer.TickListener;

/**
 * Sample Runner class used for testing
 */
public class Runner implements TickListener, Runnable
{
    private Object sync = new Object();

    @Override
    public void handleTick(int ticks)
    {
        synchronized (sync)
        {
            sync.notify();
        }
    }

    @Override
    public void run()
    {
        System.out.println("Thread started");

        while (true)
        {
            System.out.println("Waiting.");
            synchronized (sync)
            {

                try
                {
                    sync.wait();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

            }
            action();
        }
    }

    public void action()
    {
        System.out.println("Stopped waiting.");
    }
}
