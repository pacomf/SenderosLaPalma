package com.jelcaf.pacomf.patealapalma.network;

import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paco on 11/03/2015.
 */
public class Request {

   public static void ratingSenderoPOST (final Activity activity, String idSendero, String idUser, int rating, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/rating/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
      params.put("rating", String.valueOf(rating));

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>(){
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responseRatingSenderoPOST(activity, response, pd);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  pd.dismiss();
                  VolleyLog.e("Error: ", error.getMessage());
               }
            }
      );

      // add the request object to the queue to be executed
      ConfigWebServices.addToRequestQueue(activity, req);
   }

   public static void commentSenderoPOST (final Activity activity, String idSendero, String idUser, double latitude, double longitude, final String description, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/comment/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
      params.put("longitude", String.valueOf(longitude));
      params.put("latitude", String.valueOf(longitude));
      params.put("description", description);

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responseCommentSenderoPOST(activity, response, pd);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  pd.dismiss();
                  VolleyLog.e("Error: ", error.getMessage());
               }
            }
      );

      // add the request object to the queue to be executed
      ConfigWebServices.addToRequestQueue(activity, req);
   }

   public static void photoSenderoPOST (final Activity activity, String idSendero, String idUser, double latitude, double longitude, String url, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/photo/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
      params.put("longitude", String.valueOf(longitude));
      params.put("latitude", String.valueOf(longitude));
      params.put("url", url);

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responsePhotoSenderoPOST(activity, response, pd);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  pd.dismiss();
                  VolleyLog.e("Error: ", error.getMessage());
               }
            }
      );

      // add the request object to the queue to be executed
      ConfigWebServices.addToRequestQueue(activity, req);
   }


}
