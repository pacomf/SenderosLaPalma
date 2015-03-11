package com.jelcaf.pacomf.patealapalma.images;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Base64;

import com.jelcaf.pacomf.patealapalma.network.UploadToImgurTask;

import java.io.ByteArrayOutputStream;

/**
 * Created by Paco on 11/03/2015.
 */
public class Utilities {

   public static String filePath;

   public static int chooserType;

   public static String ImageToBase64 (Bitmap bm){
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      bm.compress(Bitmap.CompressFormat.JPEG, 70, baos); //bm is the bitmap object
      byte[] b = baos.toByteArray();
      String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
      return encodedImage;
   }

   public static void uploadImage (Activity ctx, Bitmap imagen, String idUser, String idSendero, double latitude, double longitude, ProgressDialog pd){
      UploadToImgurTask uploadToImgurTask = new UploadToImgurTask();
      uploadToImgurTask.setParams(ctx, pd, imagen, idUser, idSendero, latitude, longitude);
      uploadToImgurTask.execute();
   }

}

