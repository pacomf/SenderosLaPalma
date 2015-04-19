package com.jelcaf.pacomf.patealapalma.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonRectangle;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.activity.RecommenderActivity;
import com.jelcaf.pacomf.patealapalma.binding.utilities.LoadData;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendSenderoFragment extends Fragment {

   private View rootView;

   public RecommendSenderoFragment() {
      // Required empty public constructor
   }


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      rootView = inflater.inflate(R.layout.fragment_recommend_sendero, container, false);

      initializeViews(rootView);

      return rootView;
   }

   private void initializeViews(View rootView) {
      ButtonRectangle startRecommendButton = (ButtonRectangle)rootView.findViewById(R.id.startRecommendButton);

      startRecommendButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent detailIntent = new Intent(getActivity(), RecommenderActivity.class);
            startActivity(detailIntent);
         }
      });
   }


}
