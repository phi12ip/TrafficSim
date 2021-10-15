package com.pmcguire.cmsc335.trafficsim;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

import com.pmcguire.cmsc335.trafficsim.gui.CarsDisplay;
import com.pmcguire.cmsc335.trafficsim.gui.LightsDisplay;
import com.pmcguire.cmsc335.trafficsim.intersection.OneRoadIntersection;
import com.pmcguire.cmsc335.trafficsim.timer.ClockTimer;

import java.util.ArrayList;

/**
 * Simulator object keeps tract of all roads, intersections, and cars that are created
 */
public class Simulator
{
    private final ArrayList<Road> roads;                            // all the roads in the simulation
    private final ArrayList<OneRoadIntersection> intersections;     // all the intersections in the simulation
    private final ArrayList<Car> cars;                              // all the cars in the simulation

    private final ClockTimer timer;                                 // shared timer clock timer object
    private CarsDisplay carsDisplay;                                // cars GUI display
    private LightsDisplay lightsDisplay;                            // lights GUI display

    /**
     * Constructor
     * <p>
     * Creates simulator object with shared ClockTimer
     *
     * @param timer shared timer object
     */
    public Simulator(ClockTimer timer)
    {
        this.roads = new ArrayList<>();
        this.intersections = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.timer = timer;
    }

    /**
     * Add car to the simulator
     *
     * @param car car to add
     */
    public void addCar(Car car)
    {
        if (roads.isEmpty())
            return;

        roads.get(0).addCar(car);
        cars.add(car);
        carsDisplay.addCarView(car);
    }

    public void addCar()
    {
        int length = cars.size() + 1;
        Car car = new Car("Car " + length).start();
        addCar(car);
    }

    /**
     * Add intersection to the simulator
     *
     * @param intersection intersection to add
     */
    public void addIntersection(OneRoadIntersection intersection)
    {
        if (roads.isEmpty())
            return;

        boolean success = intersections.add(intersection);

        if (success)
            timer.addListener(intersection);

        lightsDisplay.addLightView(intersection.getLight());
    }

    public void addIntersection()
    {
        if (roads.isEmpty())
            return;

        OneRoadIntersection intersection = new OneRoadIntersection(roads.get(0)).start();

        addIntersection(intersection);
    }

    /**
     * Add road to the simulator
     *
     * @param road road to add
     */
    public void addRoad(Road road)
    {
        boolean success = roads.add(road);
        if (success)
            timer.addListener(road);
    }

    public void addRoad()
    {
        int length = roads.size() + 1;
        Road road = new Road("Road " + length).start();
        addRoad(road);
    }

    /**
     * Return the global timer instance
     *
     * @return timer instance
     */
    public ClockTimer getTimer()
    {
        return timer;
    }

    /**
     * Get the reference to the car display
     *
     * @param carsDisplay the car display
     */
    public void setCarDisplay(CarsDisplay carsDisplay)
    {
        this.carsDisplay = carsDisplay;
    }

    /**
     * Set the reference to the light display
     *
     * @param lightsDisplay the lights display
     */
    public void setLightsDisplay(LightsDisplay lightsDisplay)
    {
        this.lightsDisplay = lightsDisplay;
    }
}
