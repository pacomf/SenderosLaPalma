package com.jelcaf.pacomf.patealapalma.views;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.facebook.widget.ProfilePictureView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.CommentAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;
import com.jelcaf.pacomf.patealapalma.network.Request;
import com.jelcaf.pacomf.patealapalma.network.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Paco on 01/04/2015.
 */
public class CustomPopUp extends DialogFragment {

    public static CustomPopUp newInstance() {
        CustomPopUp customPopUp = new CustomPopUp();
        //customPopUp.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog);
        return customPopUp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.popup_layout, container, false);

        // TODO: Acciones de elementos del popup, imagen, edittext, upload.
        final EditText editText = (EditText) v.findViewById(R.id.writeComment);
        ImageView uploadIV = (ImageView) v.findViewById(R.id.sendIV);
        ProfilePictureView profilePictureView = (ProfilePictureView) v.findViewById(R.id.profilePicture);

        try {
            profilePictureView.setProfileId(LoginMethods.getIdFacebook(getActivity()));
        } catch (Exception e){}

        uploadIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()){
                    if (LoginMethods.checkLogin(getActivity())) {
                        System.out.println("Valgo C: " + editText.getText().toString());
                        //TODO: Enviar comentario a servidor
                        //ProgressDialog pd = Utilities.getProgressDialog(getActivity(), getString(R.string.upload_comment), getString(R.string.procesando));
                        //Request.commentSenderoPOST(getActivity(), idSendero, LoginMethods.getIdFacebook(getActivity()), LoginMethods.getNameFacebook(getActivity()), latitude, longitude, editText.getText().toString(), pd);
                    }
                }
            }
        });

        ListView listView = (ListView)v.findViewById(R.id.listView);
        setList(listView);

        // Set up a click listener to dismiss the popup if they click outside
        // of the background view
        v.findViewById(R.id.popup_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    void setList(ListView listView){

        // TODO: Recuperar Listas de Comentarios del Sendero en cuestion
        ArrayList<Comment> commentsList = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            commentsList.add(new Comment(null, LoginMethods.getIdFacebook(getActivity()), "Paco", "Un comentario muy bonito y de lo boniuto que es es superior y asi para qued uro mas tiempo", new Date(), 0, null));
        }

        CommentAdapter commentAdapter = new CommentAdapter(getActivity(), commentsList);
        listView.setAdapter(commentAdapter);
    }

}
