package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfoCodesFragment extends Fragment {

    private View rootView;

    public InfoCodesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_info_codes, container, false);

        initializeViews(rootView);

        return rootView;
    }

    private void initializeViews(View rootView) {

    }

}
