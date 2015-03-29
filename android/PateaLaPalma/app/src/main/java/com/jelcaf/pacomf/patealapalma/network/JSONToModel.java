package com.jelcaf.pacomf.patealapalma.network;

import org.json.JSONObject;

/**
 * Created by Paco on 11/03/2015.
 */
public class JSONToModel {

    public static String ratingFromResponseAddRating (JSONObject response){
        return response.optString("rating", null);
    }
}
