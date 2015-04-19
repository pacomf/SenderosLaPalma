package com.jelcaf.pacomf.patealapalma.binding.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Geo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.jelcaf.pacomf.patealapalma.network.JSONToModel.geoFromJSON;

/**
 * Created by Paco on 19/04/2015.
 */
public class LoadData {

    private static String loadJSONFromAsset(Context ctx, String file) {
        String json = null;
        try {

            InputStream is = ctx.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            return null;
        }
        return json;

    }

    public static JSONObject parserJSONFile(Context ctx, String file){
        try {
            return new JSONObject(loadJSONFromAsset(ctx, file));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void loadLocalData (Context context){
        try {
            JSONObject fileJSON = parserJSONFile(context, context.getResources().getString(R.string.initial_file));

            JSONArray buffer = fileJSON.getJSONArray("features");

            ProgressDialog pd = new ProgressDialog(context);
            pd.setTitle(R.string.process_title);
            pd.setMessage(context.getResources().getString(R.string.load_db));
            pd.setIndeterminate(false);
            pd.setMax(100);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setProgress(0);
            pd.setCancelable(false);
            pd.show();

            DownloadInfo down = new DownloadInfo();
            down.setBuffer(buffer, context, pd);
            down.execute();
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public static class DownloadInfo extends AsyncTask<String , String , Void> {

        JSONArray buffer;
        Context context;
        ProgressDialog pd;
        int porcentaje=0;
        int fallos=0;

        public void setBuffer(JSONArray buffer, Context context, ProgressDialog pd){
            this.buffer = buffer;
            this.context = context;
            this.pd = pd;
        }

        @Override
        protected Void doInBackground(String... params) {

            try {
                for(int i=0; i<buffer.length(); i++){
                    try {
                        String regular_name = buffer.getJSONObject(i).getJSONObject("properties").optString("ID");
                        String version = buffer.getJSONObject(i).getJSONObject("properties").optString("FECHA");
                        Double length = buffer.getJSONObject(i).getJSONObject("properties").optDouble("LONGITUD");
                        String type = buffer.getJSONObject(i).getJSONObject("properties").optString("TIPO");
                        String difficulty = buffer.getJSONObject(i).getJSONObject("properties").optString("DIFICULTAD");

                        JSONArray coordinates = buffer.getJSONObject(i).getJSONObject("geometry").optJSONArray("coordinates");
                        List<Geo> coordinatesList = new ArrayList();
                        for (int j=0; j<coordinates.length(); i++){
                            coordinatesList.add(geoFromJSON(null, coordinates.optJSONObject(i), "coordinate"));
                        }

                        Location start = new Location("sendero");
                        start.setLatitude(coordinatesList.get(0).getLatitud());
                        start.setLongitude(coordinatesList.get(0).getLongitud());

                        Location end = new Location("sendero");
                        end.setLatitude(coordinatesList.get(coordinatesList.size() - 1).getLatitud());
                        end.setLongitude(coordinatesList.get(coordinatesList.size()-1).getLongitud());

                        porcentaje=(int)((i*100)/buffer.length());
                        publishProgress("" + porcentaje);
                    } catch (Exception e){
                        fallos++;
                    }
                }


            } catch (Exception e) {}
            return null;
        }

        protected void onProgressUpdate(String... progress) {
            super.onProgressUpdate(progress);
            pd.setProgress(porcentaje);
            pd.setMessage(context.getResources().getString(R.string.load_db) + " " + porcentaje + "%");
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            pd.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
