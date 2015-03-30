package com.jelcaf.pacomf.patealapalma.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.binding.parser.ISO8601DateParse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paco on 11/03/2015.
 */
public class Request {

   public static void ratingSenderoPOST (final Activity activity, final String idSendero, final String idUser, final int rating, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/rating/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
      params.put("rating", String.valueOf(rating));

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>(){
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responseRatingSenderoPOST(activity, idSendero, idUser, rating, response, pd);
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

   public static void commentSenderoPOST (final Activity activity, final String idSendero, final String idUser, final Double latitude, final Double longitude, final String description, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/comment/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
      if (latitude != null && longitude != null){
          params.put("longitude", String.valueOf(longitude));
          params.put("latitude", String.valueOf(latitude));
      }
      params.put("description", description);

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responseCommentSenderoPOST(activity, idSendero, idUser, description, latitude, longitude, response, pd);
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

   public static void photoSenderoPOST (final Activity activity, final String idSendero, final String idUser, final Double latitude, final Double longitude, final String url, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/photo/"+idUser;

      Map<String, String>  params = new HashMap<String, String>();
       if (latitude != null && longitude != null){
           params.put("longitude", String.valueOf(longitude));
           params.put("latitude", String.valueOf(latitude));
       }
      params.put("url", url);

      JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
            new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                  com.jelcaf.pacomf.patealapalma.network.Response.responsePhotoSenderoPOST(activity, idSendero, idUser, latitude, longitude, url, response, pd);
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

   public static void senderoGET (final Activity activity, final String idSendero, final String idUser, final ProgressDialog pd) {

      // TODO: Recuperar de BBDD Local el Sendero (idSendero)
      //Sendero sendero = null;

      // TODO: Asignar a dateStr la fecha de ultima actualizacion del sendero (getDateUpdated())
      String dateStr = "2015-03-24T04:08:15.162Z";
      //String dateStr = ISO8601DateParse.toString(sendero.getDateUpdated());
      String query = "?date="+dateStr;

      if (idUser != null)
          query+="&user="+idUser;

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+query;

      JsonObjectRequest req = new JsonObjectRequest(URL, null,

              new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                    com.jelcaf.pacomf.patealapalma.network.Response.responseSenderoGET(activity, idSendero, idUser, response, pd);
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

    public static void senderosGET (final Activity activity, final ProgressDialog pd, Double latitude, Double longitude, Integer amount, Double maxDistance, Double maxLength, Double minLength, String difficulty, Boolean noCoordinates, Boolean noWaterPoints) {

        final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos"+Utilities.getQueryParamsByGeo(latitude, longitude, amount, maxDistance, maxLength, minLength, difficulty, noCoordinates, noWaterPoints);

        JsonArrayRequest req = new JsonArrayRequest(URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        com.jelcaf.pacomf.patealapalma.network.Response.responseSenderosGET(activity, response, pd);
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

    public static void senderosGET (Activity activity, ProgressDialog pd) {
        senderosGET(activity, pd, null, null, null, null, null, null, null, null, null);
    }

    public static void senderoCommentsGET (final Activity activity, String idSendero, final ProgressDialog pd) {

        final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/comments";

        JsonArrayRequest req = new JsonArrayRequest(URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        com.jelcaf.pacomf.patealapalma.network.Response.responseSenderoCommentsGET(activity, response, pd);
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

    public static void senderoRatingsGET (final Activity activity, String idSendero, final ProgressDialog pd) {

        final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/ratings";

        JsonArrayRequest req = new JsonArrayRequest(URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        com.jelcaf.pacomf.patealapalma.network.Response.responseSenderoRatingsGET(activity, response, pd);
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

    public static void senderoPhotosGET (final Activity activity, String idSendero, final ProgressDialog pd) {

        final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero+"/photos";

        JsonArrayRequest req = new JsonArrayRequest(URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        com.jelcaf.pacomf.patealapalma.network.Response.responseSenderoPhotosGET(activity, response, pd);
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

    public static void getOpenWeatherInfo(Context ctx, double lat, double lon, final ProgressDialog pd) {
        final String URL = "http://api.openweathermap.org/data/2.5/find?lat=" + lat + "&lon=" + lon + "&cnt=1";
        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        com.jelcaf.pacomf.patealapalma.network.Response.responseOpenWeather(response, pd);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                pd.dismiss();
            }
        }
        );

        // add the request object to the queue to be executed
        ConfigWebServices.addToRequestQueue(ctx, req);
    }
}
