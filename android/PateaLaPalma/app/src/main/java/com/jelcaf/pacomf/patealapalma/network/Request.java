package com.jelcaf.pacomf.patealapalma.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
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
      params.put("latitude", String.valueOf(latitude));
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
      params.put("latitude", String.valueOf(latitude));
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

   public static void senderoGET (final Activity activity, String idSendero, final ProgressDialog pd) {

      final String URL = ConfigWebServices.getURLServer(activity)+"/api/senderos/"+idSendero;

      JsonObjectRequest req = new JsonObjectRequest(URL, null,

              new Response.Listener<JSONObject>() {
                 @Override
                 public void onResponse(JSONObject response) {
                    com.jelcaf.pacomf.patealapalma.network.Response.responseSenderoGET(activity, response, pd);
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
