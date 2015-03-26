package com.jelcaf.pacomf.patealapalma.network;


import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Paco on 11/03/2015.
 */
public class Response {

   public static void responseRatingSenderoPOST (Activity activity, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            System.out.println("Fin: "+response);
            // TODO: ¿A dónde redirigir?
         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responseCommentSenderoPOST (Activity activity, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            System.out.println("Fin: "+response);
            // TODO: ¿A dónde redirigir?
         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responsePhotoSenderoPOST (Activity activity, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            System.out.println("Fin: "+response);
            // TODO: ¿A dónde redirigir?
         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responseSenderoGET (Activity activity, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            System.out.println("Fin: "+response);
            // TODO: ¿A dónde redirigir?
         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

    public static void responseSenderosGET (Activity activity, JSONArray response, ProgressDialog pd){

        try {

            if (response.isNull(0)) {
                pd.dismiss();
                // TODO: ¿Mostrar mensaje de Fallo al usuario?
                return;
            } else {
                pd.dismiss();
                System.out.println("Fin: "+response);
                // TODO: ¿A dónde redirigir?
            }
        } catch (Exception e) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
        }
    }

    public static void responseSenderoCommentsGET (Activity activity, JSONArray response, ProgressDialog pd){

        try {

            if (response.isNull(0)) {
                pd.dismiss();
                // TODO: ¿Mostrar mensaje de Fallo al usuario?
                return;
            } else {
                pd.dismiss();
                System.out.println("Fin: "+response);
                // TODO: ¿A dónde redirigir?
            }
        } catch (Exception e) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
        }
    }

    public static void responseSenderoRatingsGET (Activity activity, JSONArray response, ProgressDialog pd){

        try {

            if (response.isNull(0)) {
                pd.dismiss();
                // TODO: ¿Mostrar mensaje de Fallo al usuario?
                return;
            } else {
                pd.dismiss();
                System.out.println("Fin: "+response);
                // TODO: ¿A dónde redirigir?
            }
        } catch (Exception e) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
        }
    }

    public static void responseSenderoPhotosGET (Activity activity, JSONArray response, ProgressDialog pd){

        try {

            if (response.isNull(0)) {
                pd.dismiss();
                // TODO: ¿Mostrar mensaje de Fallo al usuario?
                return;
            } else {
                pd.dismiss();
                System.out.println("Fin: "+response);
                // TODO: ¿A dónde redirigir?
            }
        } catch (Exception e) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
        }
    }

    public static void responseOpenWeather (JSONObject response, ProgressDialog pd){

        OpenWeather.setDefault();
        try{
            OpenWeather.temperature_celsius = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("temp")) - 273.15;
        } catch (Exception e) {}
        try{
            OpenWeather.humidity_percentage = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("humidity"));
        } catch (Exception e) {}
        try{
            OpenWeather.pressure_hpa = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("main").getString("pressure"));
        } catch (Exception e) {}
        try{
            OpenWeather.wind_speed_kmps = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getString("speed")) * 3.6;
        } catch (Exception e) {}
        try{
            OpenWeather.wind_direction_degrees = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("wind").getString("deg"));
        } catch (Exception e) {}
        try{
            OpenWeather.cloudiness_percentage = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("clouds").getString("all"));
        } catch (Exception e) {}
        try{
            OpenWeather.rain_last3hours_mm = Double.parseDouble(response.getJSONArray("list").getJSONObject(0).getJSONObject("rain").getString("3h"));
        } catch (Exception e) {}
        try{
            OpenWeather.icon_current_weather = response.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("icon");
        } catch (Exception e) {}

        pd.dismiss();

    }

}
