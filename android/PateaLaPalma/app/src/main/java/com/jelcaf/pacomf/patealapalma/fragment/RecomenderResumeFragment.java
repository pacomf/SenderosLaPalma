package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * @author Jorge Carballo
 *         08/04/15
 */
public class RecomenderResumeFragment extends Fragment {

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.recomender_resume_fragment, container, false);
      return v;
   }
}
