package com.jelcaf.pacomf.patealapalma.views;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.widget.ProfilePictureView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.CommentAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paco on 01/04/2015.
 */
public class CustomPopUpComments extends DialogFragment {

    public String idSendero;

    public static CustomPopUpComments newInstance(String idSendero) {
        CustomPopUpComments customPopUpComments = new CustomPopUpComments();
        customPopUpComments.idSendero = idSendero;
        //customPopUpComments.setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_DeviceDefault_Dialog);
        return customPopUpComments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.popup_layout_comments, container, false);

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

        TextView noComments = (TextView) v.findViewById(R.id.no_comments);

        ListView listView = (ListView)v.findViewById(R.id.listView);
        setList(listView, noComments);

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

    void setList(ListView listView, TextView noComments){

        Sendero mSendero = Sendero.getByIdServer(this.idSendero);
        List<Comment> commentsList = mSendero.comments();

        if (commentsList.isEmpty()){
            noComments.setVisibility(View.VISIBLE);
        } else {
            CommentAdapter commentAdapter = new CommentAdapter(getActivity(), commentsList, false);
            listView.setAdapter(commentAdapter);
        }

    }

}
