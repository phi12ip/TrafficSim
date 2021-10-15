# Traffic Sim

<img style="width:30vw;" src="https://github.com/phi12ip/TrafficSim/blob/c757522cfb483db90633373bafb649527774e8e0/ui-screenshot.png" alt="UI Screenshot">

Very naive implementation of a traffic simulator created as part of a one semester object oriented & concurrency class.

## Running the program

**Prerequisites**

Make sure you have java installed on your computer with version Java SE 11 
[Download Link](https://www.oracle.com/java/technologies/downloads/#jdk17-windows)	

1. Clone the git repository

2. Enter the directory

3. Run the following command to start the application

*Linux or macos:*

`java -jar out/artifacts/TrafficSim_jar/TrafficSim.jar`

*Windows:*

`java.exe -jar out\artifacts\TrafficSim_jar\TrafficSim.jar`
	
4. When you are finished, close the window regularly or click File > Exit to exit the program.


## User Interface

**Adding an Intersection**

Click the `File > New > Intersection` menu at the top of the screen

**Adding a Car**

Click the `File > New > Car` menu at the top of the screen

## Assumptions

### Roads:
- There is only one road
- The road is only moving in one direction
- The road is in a straight line along the Y axis
- The road only has one lane of travel
- When a car is added to the road, it enters a waiting list until the start point of the road is available
- When a car reaches the end of the road, it stops


### Cars:
- Cars move at a fixed default speed ( 60mph ) when not obstructed
- Cars consider a red traffic light or another vehicle an obstacle
- Cars stop 5 meters before any obstacle
- Cars stop immediately when an obstacle is in their way ( they do not slow down )


### Intersections:
- Only a One Road Intersection has been implemented because of Road constraints
- Intersections are a single point: they do not take up any space or duration as Cars move through them
- Lights have a fixed light cycle
- Lights are “dumb” objects and are controlled entirely by the Intersections they are a part of
- Lights are yellow and red for an interval before the next cycle starts ( if the intersection had a 2nd light for the cross road )


