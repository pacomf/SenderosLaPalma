package com.jelcaf.pacomf.patealapalma.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

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
                                        intent.putExtra("idfb", user.getId());
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
}
