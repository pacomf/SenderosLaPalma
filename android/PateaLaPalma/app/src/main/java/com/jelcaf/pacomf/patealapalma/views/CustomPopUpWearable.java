package com.jelcaf.pacomf.patealapalma.views;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * Created by Paco on 01/04/2015.
 */
public class CustomPopUpWearable extends DialogFragment {

    public static CustomPopUpWearable newInstance() {
        CustomPopUpWearable customPopUpComments = new CustomPopUpWearable();
        return customPopUpComments;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Remove the default background
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Inflate the new view with margins and background
        View v = inflater.inflate(R.layout.popup_layout_wearable, container, false);



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

}
