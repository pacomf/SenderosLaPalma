package com.jelcaf.pacomf.patealapalma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.activity.SenderosSwipeActivity;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoSecurityFragment extends Fragment {

    private View rootView;

    public InfoSecurityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_info_security, container, false);

        initializeViews(rootView);

        return rootView;
    }

    private void initializeViews(View rootView) {

    }

}
