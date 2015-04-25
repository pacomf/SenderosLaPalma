package com.jelcaf.pacomf.patealapalma.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderForm;

/**
 * @author Jorge Carballo
 *         08/04/15
 */
public class RecomenderInfoFragment extends Fragment {

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.recomender_info_fragment, container, false);

      return v;
   }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
   }
}
