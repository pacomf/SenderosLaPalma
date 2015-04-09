package com.jelcaf.pacomf.patealapalma.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.activity.LoginActivity;
import com.jelcaf.pacomf.patealapalma.images.Utilities;
import com.jelcaf.pacomf.patealapalma.preferences.SharedPreferencesUtils;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Paco on 25/03/2015.
 */
public class LoginMethods {

    public static void openFacebookSession(final Activity activity, final Class goTo){
        // set the permissions in the third parameter of the call
        String[] perm = {"public_profile", "user_friends"};
        List permissions = Arrays.asList(perm);
        openActiveSessionFacebook(activity, true, permissions, new Session.StatusCallback() {
            @Override
            public void call(final Session session, SessionState state, Exception exception) {
                if (exception != null) {
                    Log.d("Facebook", exception.getMessage());
                }
                Log.d("Facebook", "Session State: " + session.getState());
                // you can make request to the /me API or do other stuff like post, etc. here
                if (state.equals(SessionState.OPENED)) {
                    Request.newMeRequest(session, new Request.GraphUserCallback() {

                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (session == Session.getActiveSession()) {
                                if (user != null) {
                                    // Display the parsed user info
                                    try {
                                        Intent intent = new Intent(activity, goTo);
                                        saveParamsFacebook(activity, user.getId(), user.getName());
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        activity.startActivity(intent);
                                    } catch (Exception e){
                                        e.getStackTrace();
                                    }
                                }
                            } else {
                                if (response.getError() != null) {
                                    // Handle errors, will do so later.
                                }
                            }
                        }
                    }).executeAsync();
                }
            }
        });
    }

    private static Session openActiveSessionFacebook(Activity activity, boolean allowLoginUI, List permissions, Session.StatusCallback callback) {
        Session.OpenRequest openRequest = new Session.OpenRequest(activity).setPermissions(permissions).setCallback(callback);
        Session session = new Session.Builder(activity).build();
        if (SessionState.CREATED_TOKEN_LOADED.equals(session.getState()) || allowLoginUI) {
            Session.setActiveSession(session);
            session.openForRead(openRequest);
            return session;
        }
        return null;
    }

    public static void saveParamsFacebook (final Activity activity, final String idFacebook, String name){
        SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_id), idFacebook);
        SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_name), name);
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL imageURL = new URL("https://graph.facebook.com/" + idFacebook + "/picture?type=large");
                    Bitmap imgProfile = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                    if (imgProfile != null){
                        String imgProfileStr = Utilities.ImageToBase64(imgProfile);
                        if (imgProfileStr != null){
                            SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_img_profile), imgProfileStr);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void deleteParamsFacebook (Activity activity){
        SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_id), null);
        SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_name), null);
        SharedPreferencesUtils.addString(activity, activity.getString(R.string.sp_fb_img_profile), null);

    }

    public static String getIdFacebook (Activity activity){
        return SharedPreferencesUtils.getString(activity, activity.getString(R.string.sp_fb_id));
    }

    public static String getNameFacebook (Activity activity){
        return SharedPreferencesUtils.getString(activity, activity.getString(R.string.sp_fb_name));
    }

    public static Bitmap getImgProfileFacebook (Activity activity){
        String imgProfileStr = SharedPreferencesUtils.getString(activity, activity.getString(R.string.sp_fb_img_profile));
        if (imgProfileStr != null){
            return Utilities.Base64ToImage(imgProfileStr);
        }
        return null;
    }
    public static void closeFacebookSession(final Activity activity, final Class goTo){
        Session session = Session.getActiveSession();
        session.closeAndClearTokenInformation();
        Intent intent = new Intent(activity, goTo);
        deleteParamsFacebook(activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static void goToLoginScreen (Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    public static boolean checkLogin (final Activity activity){

        if (getIdFacebook(activity) != null){
            return true;
        }

        final CharSequence[] items = { activity.getString(R.string.login), activity.getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.login_required));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(activity.getString(R.string.login))) {
                    goToLoginScreen(activity);
                } else if (items[item].equals(activity.getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
        return false;
    }
}
