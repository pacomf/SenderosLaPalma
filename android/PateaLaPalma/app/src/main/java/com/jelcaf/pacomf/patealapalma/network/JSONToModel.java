package com.jelcaf.pacomf.patealapalma.network;

import com.jelcaf.pacomf.patealapalma.binding.parser.ISO8601DateParse;

import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Paco on 11/03/2015.
 */
public class JSONToModel {

    public static String ratingFromResponseAddRating (JSONObject response){
        return response.optString("rating", null);
    }

    public static Date dateFromResponse (JSONObject response){
        String dateStr = response.optString("date");
        try {
            return ISO8601DateParse.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }
}
