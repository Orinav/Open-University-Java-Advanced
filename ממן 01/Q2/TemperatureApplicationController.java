package com.example.q2;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The controller of Temperature Application.
 * @author Ori Nave
 * @version 26.03.26
 */
public class TemperatureApplicationController
{
    public static final int MONTHS = 12;
    public static final double TOP_MARGIN = 40;
    public static final double BOTTOM_MARGIN = 40;
    public static final double LEFT_MARGIN = 40;
    public static final double RIGHT_MARGIN = 20;
    public static final double YEAR_TITLE_OFFSET = 10;
    public static final double BARS_GAP = 10;
    public static final double MONTH_TEXT_OFFSET = (double) 1/3;
    public static final double BOTTOM_TEXT_OFFSET = 10;
    public static final double Y_AXIS_STEPS = 5;
    public static final double LEFT_TEXT_OFFSET = 30;


    @FXML
    private Canvas paneCanvas;
    private Temperature t;
    private GraphicsContext gc;

    /**
     * Initializer
     */
    public void initialize()
    {
        t = new Temperature();
        drawGraph();
    }

    /**
     * nextButtonHandler will move the graph a year forward.
     */
    @FXML
    private void nextButtonHandler()
    {
        t.moveToNextYear();
        drawGraph();
    }

    /**
     * getMaxTemperature will return the highest temperature in a temperature array.
     */
    private double getMaxTemperature(double[] temperatures)
    {
        double maxTemperature = temperatures[0];
        for (int i = 1; i < temperatures.length; i++)
        {
            if (maxTemperature < temperatures[i])
                maxTemperature = temperatures[i];
        }
        return maxTemperature;
    }

    /**
     * getMinTemperature will return the lowest temperature in a temperature array.
     */
    private double getMinTemperature(double[] temperatures)
    {
        double minTemperature = temperatures[0];
        for (int i = 1; i < temperatures.length; i++)
        {
            if (minTemperature > temperatures[i])
                minTemperature = temperatures[i];
        }
        return minTemperature;
    }

    /**
     * setColor will set the color of the column in adjacent to this next 3 statements:
     * 1) Red column is the highest temperature in the current year (maxTemperature).
     * 2) Blue column is the lowest temperature in the current year (minTemperature).
     * 3) Gray column is not the highest and not the lowest temperature in the current year.
     */
    private void setColor(double temperature, double maxTemperature, double minTemperature)
    {
        if (temperature == maxTemperature)
            gc.setFill(Color.RED);
        else if (temperature == minTemperature)
            gc.setFill(Color.BLUE);
        else
            gc.setFill(Color.GRAY);

    }

    /**
     * drawGraph will draw the all the columns, months and temperatures marks.
     */
    private void drawGraph()
    {
        gc = paneCanvas.getGraphicsContext2D();
        double width = paneCanvas.getWidth();
        double height = paneCanvas.getHeight();
        gc.clearRect(0, 0, width, height); //Clears the window with every "next" click.

        int year = t.getCurrentYear();
        double[] temperatures = t.getCurrentTemperatures();
        double maxTemperature = getMaxTemperature(temperatures);
        double minTemperature = getMinTemperature(temperatures);

        double graphWidth = width - LEFT_MARGIN - RIGHT_MARGIN;
        double graphHeight = height - TOP_MARGIN - BOTTOM_MARGIN;
        double barWidth = graphWidth/MONTHS;
        double centerX = width/2;

        gc.strokeText("Year " + year, centerX, YEAR_TITLE_OFFSET);

        for (int i = 0; i < MONTHS; i++)
        {
            double temp = temperatures[i];
            double barHeight = (temp / maxTemperature) * graphHeight;
            double x = LEFT_MARGIN + i * barWidth;
            double y = height - BOTTOM_MARGIN - barHeight;

            setColor(temp, maxTemperature, minTemperature);

            gc.fillRect(x, y, barWidth-BARS_GAP, barHeight);
            gc.setFill(Color.BLACK);
            gc.strokeText(String.valueOf(i + 1), x + barWidth*MONTH_TEXT_OFFSET, height - BOTTOM_TEXT_OFFSET);
        }

        for (int i = 0; i <= Y_AXIS_STEPS; i++)
        {
            double value = (maxTemperature / Y_AXIS_STEPS) * i;
            double y = height - BOTTOM_MARGIN - (value / maxTemperature) * graphHeight;
            gc.strokeText(String.format("%.0f", value), LEFT_MARGIN-LEFT_TEXT_OFFSET, y);
        }
    }
}