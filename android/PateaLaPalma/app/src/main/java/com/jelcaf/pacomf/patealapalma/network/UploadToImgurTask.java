package com.jelcaf.pacomf.patealapalma.network;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.images.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class UploadToImgurTask extends AsyncTask<String, Void, String> {

   Activity ctx;
   ProgressDialog pd;
   Bitmap imagen;
   String idUser, idSendero;
   double latitude, longitude;

   public void setParams (Activity ctx, ProgressDialog pd, Bitmap imagen, String idUser, String idSendero, double latitude, double longitude){
      this.ctx = ctx;
      this.pd = pd;
      this.imagen = imagen;
      this.idUser = idUser;
      this.idSendero = idSendero;
      this.latitude = latitude;
      this.longitude = longitude;
   }

   @Override
   protected String doInBackground(String... params) {
      final String upload_to = "https://api.imgur.com/3/image";

      HttpClient httpClient = new DefaultHttpClient();
      HttpContext localContext = new BasicHttpContext();
      HttpPost httpPost = new HttpPost(upload_to);
      httpPost.setHeader("Authorization", "Client-ID " + ctx.getString(R.string.clientid_imgur));

      try {
         MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
         entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

         entityBuilder.addTextBody("image", com.jelcaf.pacomf.patealapalma.images.Utilities.ImageToBase64(this.imagen));

         HttpEntity entity = entityBuilder.build();

         httpPost.setEntity(entity);

         final HttpResponse response = httpClient.execute(httpPost, localContext);

         final String response_string = EntityUtils.toString(response.getEntity());

         final JSONObject json = new JSONObject(response_string);

         return json.getJSONObject("data").optString("link");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   protected void onPostExecute(String result) {
      // Mandar link (Result) al servidor
      if (result != null){
         Request.photoSenderoPOST(ctx, idSendero, idUser, latitude, longitude, result, pd);
      } else {
         if ((pd != null) && (pd.isShowing()))
            pd.dismiss();
         // TODO: ¿Mostrar mensaje de error al usuario?
      }
   }

}
