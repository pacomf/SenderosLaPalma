package com.jelcaf.pacomf.patealapalma;

import java.text.DecimalFormat;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class SenderosConstants {

   public static class Arguments {
      public static final String RECOMMENDED = "RECOMMENDED_SENDEROS";

   }

   public static class Tabs {
      public static final int GROUP_RECOMMEND_SENDEROS = 0;
      public static final int RECOMMENDED_SENDEROS = 1;
      public static final int ALL_SENDEROS = 2;
   }

   public static final double SECONDS_IN_KM_MEDIUM = 13 * 60;

   public static final double WATER_BY_KM = 0.057;

   public static String timeConversion(double secondsD) {
      int seconds = (int)secondsD;

      final int MINUTES_IN_AN_HOUR = 60;
      final int SECONDS_IN_A_MINUTE = 60;

      int minutes = seconds / SECONDS_IN_A_MINUTE;

      int hours = minutes / MINUTES_IN_AN_HOUR;
      minutes -= hours * MINUTES_IN_AN_HOUR;

      if (hours == 0) {
         return minutes + "min";
      }

      return hours + "h " + minutes + "min";
   }

   public static DecimalFormat WaterFormat = new DecimalFormat("0.00");

}
