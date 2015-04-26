package com.jelcaf.pacomf.patealapalma.images;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Base64;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;
import com.jelcaf.pacomf.patealapalma.network.image.Upload;
import com.jelcaf.pacomf.patealapalma.network.image.UploadService;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Paco on 11/03/2015.
 */
public class Utilities {

   public static final int GALLERY_INTENT=415;
   public static final int CAMERA_INTENT=416;

   public static String ImageToBase64 (Bitmap bm){
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      bm.compress(Bitmap.CompressFormat.JPEG, 70, baos); //bm is the bitmap object
      byte[] b = baos.toByteArray();
      String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
      return encodedImage;
   }

   public static Bitmap Base64ToImage (String previouslyEncodedImage){
      if( !previouslyEncodedImage.equalsIgnoreCase("") ){
         byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
         Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
         return bitmap;
      }
      return null;
   }

   public static void uploadImage (Activity ctx, File fimg, Bitmap imagen, String uri, String idUser, String idSendero, double latitude, double longitude, ProgressDialog pd){
      if (!com.jelcaf.pacomf.patealapalma.network.Utilities.haveInternet(ctx)){
         pd.dismiss();
         Snackbar.with(ctx) // context
                 .text(ctx.getString(R.string.fail_internet)) // text to display
                 .type(SnackbarType.MULTI_LINE)
                 .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
                 .show(ctx); // activity where it is displayed
         return;
      }

      Upload upload = new Upload();

      upload.image = fimg;
      upload.title = "Patea La Palma";
      upload.description = idUser+":"+idSendero;

      new UploadService(upload, ctx, pd, idSendero, idUser, latitude, longitude).execute();

   }

   public static void selectImage (final Activity activity){
      final CharSequence[] items = { activity.getString(R.string.take_picture), activity.getString(R.string.gallery_picture), activity.getString(R.string.cancel)};

      AlertDialog.Builder builder = new AlertDialog.Builder(activity);
      builder.setTitle(activity.getString(R.string.select_picture));
      builder.setItems(items, new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int item) {
            if (items[item].equals(activity.getString(R.string.take_picture))) {
               selectImageCamera(activity);
            } else if (items[item].equals(activity.getString(R.string.gallery_picture))) {
               selectImageGallery(activity);
            } else if (items[item].equals(activity.getString(R.string.cancel))) {
               dialog.dismiss();
            }
         }
      });
      builder.show();
   }

   public static void selectImageCamera (Activity activity){
      Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      File f = new File(android.os.Environment.getExternalStorageDirectory(), activity.getString(R.string.temp_picture));
      intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
      activity.startActivityForResult(intent, CAMERA_INTENT);
   }

   public static void selectImageGallery (Activity activity){
      if (Build.VERSION.SDK_INT < 19) {
         Intent intent = new Intent();
         intent.setType("image/*");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         activity.startActivityForResult(Intent.createChooser(intent, activity.getString(R.string.select_picture)), GALLERY_INTENT);
      } else {
         Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
         intent.addCategory(Intent.CATEGORY_OPENABLE);
         intent.setType("image/*");
         activity.startActivityForResult(intent, GALLERY_INTENT);
      }
   }

   public static Bitmap getBitMapFromUri (Context ctx, Uri pictureUri) throws Exception{
      if (Build.VERSION.SDK_INT < 19) {
         String selectedImagePath = getPathPictureFromUri(ctx, pictureUri);
         return BitmapFactory.decodeFile(selectedImagePath);
      } else {
         ParcelFileDescriptor parcelFileDescriptor;
         try {
            parcelFileDescriptor = ctx.getContentResolver().openFileDescriptor(pictureUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();

            return bitmap;

         } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
         } catch (IOException e) {
            e.printStackTrace();
            throw e;
         }
      }
   }

   public static String getPathPictureFromUri (Context ctx, Uri uri) {
      if( uri == null ) {
         return null;
      }
      String[] projection = { MediaStore.Images.Media.DATA };
      Cursor cursor = ctx.getContentResolver().query(uri, projection, null, null, null);
      if( cursor != null ){
         int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
         cursor.moveToFirst();
         return cursor.getString(column_index);
      }
      return uri.getPath();
   }

   public static void getCameraPictureAndUpload (final Activity activity, String idSendero){
      File f = new File(Environment.getExternalStorageDirectory().toString());
      for (File temp : f.listFiles()) {
         if (temp.getName().equals(activity.getString(R.string.temp_picture))) {
            f = temp;
            break;
         }
      }
      try {
         BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
         final Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);

         ProgressDialog pd = ProgressDialog.show(activity, activity.getResources().getText(R.string.upload_picture), activity.getResources().getText(R.string.procesando));
         Utilities.uploadImage(activity, f, bm, f.getAbsolutePath(), LoginMethods.getIdFacebook(activity), idSendero, 0, 0, pd);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static String getURIIconWeather (String icon){
      return "http://openweathermap.org/img/w/"+icon+".png";
   }

}

