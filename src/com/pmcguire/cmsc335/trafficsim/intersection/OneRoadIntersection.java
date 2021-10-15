package com.pmcguire.cmsc335.trafficsim.intersection;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.Road;
import com.pmcguire.cmsc335.trafficsim.light.Light;
import com.pmcguire.cmsc335.trafficsim.light.State;

/**
 * A One Road Intersection
 */
public class OneRoadIntersection extends Intersection
{

    private final Road road;                // road for the intersection
    private final Light light;              // light for the road

    /**
     * Create an intersection for road
     *
     * @param road the road for this intersection
     */
    public OneRoadIntersection(Road road)
    {
        super();

        this.road = road;               // set the road
        light = new Light();            // create a new light
        this.road.addLight(light);      // set the new light on the road

        // Set the name based on how many intersections the road currently has
        name = String.format(
                "Intersection %d [%s]",
                road.lightCount(),
                road.getName()
        );
    }

    /**
     * Start the thread for this intersection
     *
     * @return this
     */
    @Override
    public OneRoadIntersection start()
    {
        super.start();
        return this;
    }

    /**
     * Update the state of the lights based on the cycle policy
     */
    protected synchronized void updateLights()
    {
        // Update cycle ticks
        int cycleTime = (currentTicks * DEFAULT_TICK_LENGTH) % DEFAULT_TOTAL_CYCLE_LENGTH;

        // Determine light state for current time in cycle
        if (cycleTime < (DEFAULT_MAJOR_LENGTH - 2 * DEFAULT_INTERVAL))
            light.setState(State.GREEN);
        else if (cycleTime < (DEFAULT_MAJOR_LENGTH - DEFAULT_INTERVAL))
            light.setState(State.YELLOW);
        else
            light.setState(State.RED);

        light.setTextBoxContent();

        // Debug message
        System.out.printf("[Intersection] %s updated.\n", getName());
    }

    /**
     * Get a reference to this intersection's light
     *
     * @return the light
     */
    public Light getLight()
    {
        return light;
    }

    @Override
    public String toString()
    {
        return String.format(
                "Intersection { name='%s', light='%s'",
                getName(),
                light.getState()
        );
    }
}
