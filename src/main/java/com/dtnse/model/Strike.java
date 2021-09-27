package com.dtnse.model;

import java.util.Date;

public class Strike
{
    private FlashType flashType;
    private long strikeTime;
    private Double latitude;
    private Double longitude;
    private int peakAmps;
    private String reserved;
    private int icHeight;
    private long receivedTime;
    private int numberOfSensors;
    private int multiplicity;

    public FlashType getFlashType()
    {
        return flashType;
    }

    public long getStrikeTime()
    {
        return strikeTime;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public int getPeakAmps()
    {
        return peakAmps;
    }

    public String getReserved()
    {
        return reserved;
    }

    public int getIcHeight()
    {
        return icHeight;
    }

    public long getReceivedTime()
    {
        return receivedTime;
    }

    public int getNumberOfSensors()
    {
        return numberOfSensors;
    }

    public int getMultiplicity()
    {
        return multiplicity;
    }
}
