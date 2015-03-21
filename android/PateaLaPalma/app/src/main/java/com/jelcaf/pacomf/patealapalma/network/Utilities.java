package com.jelcaf.pacomf.patealapalma.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by Paco on 11/03/2015.
 */
public class Utilities {

   public static boolean haveInternet(Context ctx) {

      NetworkInfo info = (NetworkInfo) ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

      if (info == null || !info.isConnected()) {
         return false;
      }
      if (info.isRoaming()) {
         // here is the roaming option you can change it if you want to
         // disable internet while roaming, just return false
      }
      if (isConnectionFast(info.getType(), info.getSubtype()))
         return true;
      return false;
   }

   public static boolean isConnectionFast(int type, int subType){
      if(type== ConnectivityManager.TYPE_WIFI){
         return true;
      }else if(type==ConnectivityManager.TYPE_MOBILE){
         switch(subType){
            case TelephonyManager.NETWORK_TYPE_1xRTT:
               return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_CDMA:
               return false; // ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:
               return false; // ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
               return true; // ~ 400-1000 kbps
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
               return true; // ~ 600-1400 kbps
            case TelephonyManager.NETWORK_TYPE_GPRS:
               return false; // ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_HSDPA:
               return true; // ~ 2-14 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPA:
               return true; // ~ 700-1700 kbps
            case TelephonyManager.NETWORK_TYPE_HSUPA:
               return true; // ~ 1-23 Mbps
            case TelephonyManager.NETWORK_TYPE_UMTS:
               return true; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion
             * to appropriate level to use these
             */
            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
               return true; // ~ 1-2 Mbps
            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
               return true; // ~ 5 Mbps
            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
               return true; // ~ 10-20 Mbps
            case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
               return false; // ~25 kbps
            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
               return true; // ~ 10+ Mbps
            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
               return false;
         }
      }else{
         return false;
      }
   }

    public static String getQueryParamsByGeo(Double latitude, Double longitude, Integer amount, Double maxDistance, Double maxLength, Double minLength, String difficulty, Boolean noCoordinates, Boolean noWaterPoints){
        String ret = "";
        if ((latitude != null) && (longitude != null)){
            ret+="search=byGeo&latitude="+latitude+"&longitude="+longitude;
            if (maxDistance != null){
                ret+="&maxDistance="+maxDistance;
            }
        } else {
            return ret;
        }
        if (amount != null){
            ret+="&amount="+amount;
        }
        if (maxLength != null){
            ret+="&maxLength="+maxLength;
        }
        if (minLength != null){
            ret+="&minLength="+minLength;
        }
        if (difficulty != null){
            ret+="&difficulty="+difficulty;
        }
        if (noCoordinates != null){
            ret+="&noCoordinates="+noCoordinates;
        }
        if (noWaterPoints != null){
            ret+="&noWaterPoints="+noWaterPoints;
        }
        if (ret.charAt(0) == '&'){
            ret = ret.substring(1);
        }
        return ret;
    }
}
