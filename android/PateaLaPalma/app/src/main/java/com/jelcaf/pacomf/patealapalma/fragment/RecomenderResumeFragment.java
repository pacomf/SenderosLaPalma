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
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderForm;

import java.util.List;

/**
 * @author Jorge Carballo
 *         08/04/15
 */
public class RecomenderResumeFragment extends Fragment {

   public static final int QUESTIONS_ADD_ON_RESUME = 2;

   public static final String ARG_FORM = "FORM_ID_BUNDLE_STR";
   private RecommenderForm form;
   LinearLayout answersLayout;

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      form = RecommenderForm.getInstance(getActivity());
   }

   @Override
   public void onResume() {
      super.onResume();

      form = RecommenderForm.getInstance(getActivity());

      for (int i = form.getQuestions().size() - QUESTIONS_ADD_ON_RESUME; i < form.getQuestions().size(); i++) {
         TextView tv = new TextView(getActivity());
         tv.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
         String qAndA = "-" + form.getQuestions().get(i).getResumeQuestion();
         tv.setText(qAndA);
         tv.setGravity(Gravity.LEFT);

         TextView tvResponse = new TextView(getActivity());
         tvResponse.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
         tvResponse.setText(" " + form.getQuestions().get(i).getResponse() == null
               ? "N/C" : form.getQuestions().get(i).getStrResponse());
         tvResponse.setGravity(Gravity.LEFT);
         tvResponse.setPadding(100, 0, 0, 0);

         answersLayout.addView(tv);
         answersLayout.addView(tvResponse);
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View v = inflater.inflate(R.layout.recomender_resume_fragment, container, false);

      answersLayout = (LinearLayout)v.findViewById(R.id.answerResume);

      for (int i = 0; i < form.getQuestions().size() - QUESTIONS_ADD_ON_RESUME; i++) {
         RecommenderBaseQuestion question = form.getQuestions().get(i);

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
