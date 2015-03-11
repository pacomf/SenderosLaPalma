package com.jelcaf.pacomf.patealapalma.network;


import android.app.Activity;
import android.app.ProgressDialog;

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

}
