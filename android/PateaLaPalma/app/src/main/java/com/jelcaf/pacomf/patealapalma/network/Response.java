package com.jelcaf.pacomf.patealapalma.network;


import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
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

}
