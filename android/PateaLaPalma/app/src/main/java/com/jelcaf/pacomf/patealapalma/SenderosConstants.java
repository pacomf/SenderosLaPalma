package com.jelcaf.pacomf.patealapalma;

import android.content.Context;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class SenderosConstants {

   public static class Arguments {
      public static final String RECOMMENDED = "RECOMMENDED_SENDEROS";
      public static final String RECOMMENDED_GROUPS = "RECOMMENDED_GROUPS";
      public static final String RECOMMENDED_GROUPS_STRING = "RECOMMENDED_GROUPS_STRING";

   }

   public static class Tabs {
      public static final int GROUP_RECOMMEND_SENDEROS = 0;
      public static final int RECOMMENDED_SENDEROS = 1;
      public static final int ALL_SENDEROS = 2;
   }

   public static final double SECONDS_IN_KM_EASY = 17.5 * 60;
   public static final double SECONDS_IN_KM_MEDIUM = 18.5 * 60;
   public static final double SECONDS_IN_KM_HARD = 19.5 * 60;
   public static final double SECONDS_IN_KM_EXTREME = 20.5 * 60;

   public static final double WATER_BY_KM = 0.057;

   public static final int MINUTES_IN_AN_HOUR = 60;
   public static final int SECONDS_IN_A_MINUTE = 60;
   public static final int SECONDS_IN_A_HOUR = 60 * 60;

   public static String timeConversion(double secondsD) {
      int seconds = (int)secondsD;

      int minutes = seconds / SECONDS_IN_A_MINUTE;

      int hours = minutes / MINUTES_IN_AN_HOUR;
      minutes -= hours * MINUTES_IN_AN_HOUR;

      if (hours == 0) {
         return minutes + "min";
      }

      return hours + "h " + minutes + "min";
   }

   public static double getSecondsByDifficulty(String difficultyInLang, Context ctx) {
      Map<String, String> diffilcultyMap = getMapDifficulty(ctx);
      if ("Baja".equals(diffilcultyMap.get(difficultyInLang))) {
         return SECONDS_IN_KM_EASY;
      }
      if ("Alta".equals(diffilcultyMap.get(difficultyInLang))) {
         return SECONDS_IN_KM_HARD;
      }
      if ("Exrema".equals(diffilcultyMap.get(difficultyInLang))) {
         return SECONDS_IN_KM_EXTREME;
      }
      return SECONDS_IN_KM_MEDIUM;
   }

   // Usado para asignar el valor de la varible (en el idioma correspondiente) de la dificultad del sendero al valor que tiene el propio sendero
   public static Map<String, String> getMapDifficulty (Context ctx){
      Map <String, String> difficultySendero = new HashMap<>();
      difficultySendero.put(ctx.getString(R.string.qDifficultyLow), "Baja");
      difficultySendero.put(ctx.getString(R.string.qDifficultyMedium), "Media");
      difficultySendero.put(ctx.getString(R.string.qDifficultyHigh), "Alta");
      difficultySendero.put(ctx.getString(R.string.qDifficultyExtreme), "Extrema");
      return difficultySendero;
   }

   public static DecimalFormat WaterFormat = new DecimalFormat("0.00");

}
