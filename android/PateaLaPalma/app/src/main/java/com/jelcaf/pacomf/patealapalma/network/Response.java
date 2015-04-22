package com.jelcaf.pacomf.patealapalma.network;


import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;

import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Photo;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Paco on 11/03/2015.
 */
public class Response {

   public static void responseRatingSenderoPOST (Activity activity, String idSendero, String idUser, int rating_user, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            Double rating = JSONToModel.ratingFromResponseAddRating(response);
            if (rating != null) {
                Sendero sendero = Sendero.getByIdServer(idSendero);
                sendero.setRating(rating);
                sendero.setUserRating(rating_user);
                sendero.save();
            }

         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responseCommentSenderoPOST (Activity activity, String idSendero, String idUser, String nameOwner, String description, Double latitude, Double longitude, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            Date date = JSONToModel.dateFromResponse(response);
            Location location = new Location("comment");
            if (latitude != null && longitude != null) {
                location.setLatitude(latitude);
                location.setLongitude(longitude);
            }

            Sendero sendero = Sendero.getByIdServer(idSendero);
            Comment comentario = new Comment(sendero, idUser, nameOwner, description, date, 0, location);
            comentario.save();

         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responsePhotoSenderoPOST (Activity activity, String idSendero, String idUser, Double latitude, Double longitude, String url, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
             Date date = JSONToModel.dateFromResponse(response);
             Location location = new Location("photo");
             if (latitude != null && longitude != null) {
                 location.setLatitude(latitude);
                 location.setLongitude(longitude);
             }

             Sendero sendero = Sendero.getByIdServer(idSendero);
             Photo photo = new Photo(sendero, url, idUser, date, 0, location);
             photo.save();
         }
      } catch (Exception e) {
         pd.dismiss();
         // TODO: ¿Mostrar mensaje de Fallo al usuario?
         return;
      }
   }

   public static void responseSenderoGET (Activity activity, String idSendero, String idUser, JSONObject response, ProgressDialog pd){

      try {

         if ((response.optString("res") != null) && (response.optString("res").equals("err"))) {
            pd.dismiss();
            // TODO: ¿Mostrar mensaje de Fallo al usuario?
            return;
         } else {
            pd.dismiss();
            System.out.println("Fin: " + response);
            JSONToModel.senderoInfoFromResponse(response, idSendero, idUser);

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
                Sendero sendero;
                for (int i=0; i<response.length(); i++){
                    sendero = JSONToModel.senderoFromJSON(response.getJSONObject(i));
                    //TODO: ¿Buscar en Local el Sendero y Borrarlo para Sobreescribirlo?
                    //TODO: Guardar el sendero
                }
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
