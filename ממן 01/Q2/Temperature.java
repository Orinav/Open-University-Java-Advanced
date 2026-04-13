package com.example.q2;

/**
 * Temperature class will represent the average temperature in each month between the years 2022-2026.
 * @author Ori Nave
 * @version 26.03.26
 */
public class Temperature
{

    private final int[] years;
    private final double[][] temperatures;
    private int currentYearIndex;

    /**
     * Constructor of Temperature object.
     */
    public Temperature()
    {
        years = new int[]{2022, 2023, 2024, 2025, 2026};
        temperatures = new double[][] {
                {10.5, 12.0, 15.6, 20.7, 25.5, 30.3, 32.5, 33.0, 28.0, 22.5, 16.0, 11.5},
                {60.5, 72.0, 85.5, 10.0, 55.5, 31.5, 34.0, 69.4, 67.7, 9.3, 18.7, 22.1},
                {40.5, 65.0, 14.0, 11.5, 53.5, 31.3, 34.6, 71.6, 36.0, 1.0, 100.5, 24.9},
                {20.0, 14.0, 17.0, 12.5, 52.4, 46.3, 41.6, 21.6, 27.1, 53.0, 0.1, 99.9},
                {20.2, 1.1, 11.1, 33.3, 44.4, 55.5, 66.6, 77.7, 88.8, 99.9, 100, 0.0}

        };
        currentYearIndex = 0;
    }

    /**
     * gerCurrentYear will return the current year of the temperature object.
     */
    public int getCurrentYear() {
        return this.years[currentYearIndex];
    }

    /**
     * gerCurrentTemperature will return the temperatures of the current year of the temperature object.
     */
    public double[] getCurrentTemperatures() {
        return this.temperatures[currentYearIndex];
    }

    /**
     * moveToNextYear will promote the current year index value by 1.
     * If we're on the last year and moveToNextYear is called then we'll be moved back to the first year.
     */
    public void moveToNextYear() {
        this.currentYearIndex = (currentYearIndex + 1) % years.length;
    }
}