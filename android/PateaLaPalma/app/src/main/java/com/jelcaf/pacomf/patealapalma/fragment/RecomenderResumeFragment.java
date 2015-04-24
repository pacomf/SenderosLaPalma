package com.jelcaf.pacomf.patealapalma.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderForm;

import java.util.List;

/**
 * @author Jorge Carballo
 *         08/04/15
 */
public class RecomenderResumeFragment extends Fragment {

   public static final String ARG_FORM = "FORM_ID_BUNDLE_STR";
   private RecommenderForm form;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      if (getArguments().containsKey(ARG_FORM)) {
         // Load the dummy content specified by the fragment
         // arguments. In a real-world scenario, use a Loader
         // to load content from a content provider.
         form = (RecommenderForm)getArguments().getSerializable(ARG_FORM);

      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.recomender_resume_fragment, container, false);

      LinearLayout answersLayout = (LinearLayout)v.findViewById(R.id.answerResume);

      for (RecommenderBaseQuestion question : form.questions) {
         TextView tv = new TextView(getActivity());
         tv.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
         String qAndA = "-" + question.getResumeQuestion();
         tv.setText(qAndA);
         tv.setGravity(Gravity.LEFT);

         TextView tvResponse = new TextView(getActivity());
         tvResponse.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
         tvResponse.setText(" " + question.getResponse() == null
               ? "N/C" : question.getStrResponse());
         tvResponse.setGravity(Gravity.LEFT);
         tvResponse.setPadding(100, 0, 0, 0);

         answersLayout.addView(tv);
         answersLayout.addView(tvResponse);

      }


      return v;
   }
}
