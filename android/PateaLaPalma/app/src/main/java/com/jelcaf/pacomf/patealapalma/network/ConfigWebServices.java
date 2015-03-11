package com.jelcaf.pacomf.patealapalma.network;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.jelcaf.pacomf.patealapalma.R;

/**
 * Created by Paco on 11/03/2015.
 */
public class ConfigWebServices {

   /**
    * Log or request TAG
    */
   public static final String TAG = "VolleyPatterns";

   /**
    * Global request queue for Volley
    */
   public static RequestQueue mRequestQueue;

   /**
    * @return The Volley Request queue, the queue will be created if it is null
    */
   public static RequestQueue getRequestQueue(Context ctx) {
      // lazy initialize the request queue, the queue instance will be
      // created when it is accessed for the first time
      if (ConfigWebServices.mRequestQueue == null) {
         ConfigWebServices.mRequestQueue = Volley.newRequestQueue(ctx);
      }

      return ConfigWebServices.mRequestQueue;
   }

   /**
    * Adds the specified request to the global queue, if tag is specified
    * then it is used else Default TAG is used.
    *  @param req
    * @param tag
    */
   public static <T> void addToRequestQueue(Context ctx, Request<T> req, String tag) {
      // set the default tag if tag is empty
      req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

      VolleyLog.d("Adding request to queue: %s", req.getUrl());

      ConfigWebServices.getRequestQueue(ctx).add(req);
   }

   /**
    * Adds the specified request to the global queue using the Default TAG.
    *
    * @param req
    */
   public static <T> void addToRequestQueue(Context ctx, Request<T> req) {
      // set the default tag if tag is empty
      req.setTag(TAG);

      ConfigWebServices.getRequestQueue(ctx).add(req);
   }

   /**
    * Cancels all pending requests by the specified TAG, it is important
    * to specify a TAG so that the pending/ongoing requests can be cancelled.
    *
    * @param tag
    */
   public static void cancelPendingRequests(Object tag) {
      if (ConfigWebServices.mRequestQueue != null) {
         ConfigWebServices.mRequestQueue.cancelAll(tag);
      }
   }

   public static String getURLServer (Context ctx){
      return ctx.getString(R.string.server_ip)+":"+ctx.getString(R.string.server_port);
   }
}
