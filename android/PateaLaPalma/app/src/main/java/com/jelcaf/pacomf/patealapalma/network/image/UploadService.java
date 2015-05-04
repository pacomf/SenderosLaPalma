package com.jelcaf.pacomf.patealapalma.network.image;

/**
 * Created by Paco on 26/04/2015.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.jelcaf.pacomf.patealapalma.network.Request;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.mime.TypedFile;

/**
 * Created by AKiniyalocts on 1/12/15.
 *
 * Our upload service. This creates our restadapter, uploads our image, and notifies us of the response.
 *
 *
 */
public class UploadService extends AsyncTask<Void, Void, Void> {
    public final static String TAG = UploadService.class.getSimpleName();


    public String title, description, albumId;
    private ImageResponse response;
    private Activity activity;
    private File image;
    private ProgressDialog pd;
    String idSendero, idUser;
    Double latitud, longitud;



    public UploadService(Upload upload, Activity activity, ProgressDialog pd, String idSendero, String idUser, Double latitud, Double longitud){
        this.image = upload.image;
        this.title = upload.title;
        this.description = upload.description;
        this.albumId = upload.albumId;
        this.activity = activity;
        this.pd = pd;
        this.idSendero = idSendero;
        this.idUser = idUser;
        this.latitud = latitud;
        this.longitud = longitud;

    }

    @Override protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override protected Void doInBackground(Void... params) {

        /*
          Create rest adapter using our imgur API
         */
        RestAdapter imgurAdapter = new RestAdapter.Builder()
                .setEndpoint(ImgurAPI.server)
                .build();

        /*
          Set rest adapter logging if we're already logging
         */

        if(Constant.LOGGING)
            imgurAdapter.setLogLevel(RestAdapter.LogLevel.FULL);

        /*
          Upload image, get response for image
         */

        response = imgurAdapter.create(ImgurAPI.class)
                .postImage(
                        Constant.getClientAuth(), title, description, albumId, null, new TypedFile("image/*", image)
                );


        return null;
    }

    @Override protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // TODO: La imagen se borra, guardarla en el futuro?
        this.image.delete();

        if(response != null){

            if(response.success) {
                Request.photoSenderoPOST(activity, idSendero, idUser, latitud, longitud, response.data.link, pd);
            } else {
                pd.dismiss();
            }

        }
        pd.dismiss();
    }

}