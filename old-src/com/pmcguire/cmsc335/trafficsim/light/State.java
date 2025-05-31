package com.pmcguire.cmsc335.trafficsim.light;

/*
Author:     Philip McGuire
Date:       2021-10-01
Class:      UMGC CMSC-335
Project:    3
 */

/**
 * State represents a stage in traffic lights
 */
public enum State
{
    GREEN,
    YELLOW,
    RED,
    BLANK, // used for flashing
}
