package com.jelcaf.pacomf.patealapalma.network;

/**
 * Created by Paco on 26/03/2015.
 */
public class OpenWeather {

    public static double temperature_celsius;
    public static double humidity_percentage;
    public static double pressure_hpa;
    public static double wind_speed_kmps;
    public static double wind_direction_degrees;
    public static double cloudiness_percentage;
    public static double rain_last3hours_mm;
    public static String icon_current_weather;

    public static void setDefault (){
        temperature_celsius = 0;
        humidity_percentage = 0;
        pressure_hpa = 0;
        wind_speed_kmps = 0;
        wind_direction_degrees = 0;
        cloudiness_percentage = 0;
        rain_last3hours_mm = 0;
        icon_current_weather = "";
    }
}
