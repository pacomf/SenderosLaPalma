package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.QuestionRecommenderForm;
import com.jelcaf.pacomf.patealapalma.binding.RecommenderQuestionResponse;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class QuestionFragment extends Fragment {

   private static final String ARG_QUESTION = "question";
   private static final String TAG = QuestionFragment.class.getCanonicalName();

   private ViewGroup mContainer;
   private QuestionRecommenderForm mQuestion;
   private QuestionFragment mFragment;

   public static QuestionFragment newInstance(QuestionRecommenderForm question) {
      QuestionFragment fragment = new QuestionFragment();
      Bundle args = new Bundle();
      args.putSerializable(ARG_QUESTION, question);
      fragment.setArguments(args);
      return fragment;
   }

   public QuestionFragment() {

   }

   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (getArguments() != null) {
         mQuestion = (QuestionRecommenderForm)getArguments().getSerializable(ARG_QUESTION);
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      mContainer = container;
      View v = inflater.inflate(R.layout.question_fragment, container, false);

      ((TextView)v.findViewById(R.id.question)).setText(mQuestion.getQuestion());

      RadioGroup mRadioGroup = (RadioGroup)v.findViewById(R.id.answer);

      for (int i=0; i < mQuestion.getPosibleResponses().size(); i++) {
         createRadioButton(mRadioGroup, mQuestion, i);
      }

      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(mQuestion.getQuestion() + " -> (" + mQuestion.getSelectedResponse() + ")");
      if (mQuestion.getSelectedResponse() != null) {
         stringBuilder.append(" " + ((RadioButton)mRadioGroup.getChildAt(mQuestion
               .getSelectedResponse()))
               .getText());
         ((RadioButton) mRadioGroup.getChildAt(mQuestion.getSelectedResponse())).setChecked(true);
      }
      Log.i(TAG, stringBuilder.toString());

      return v;
   }

   private void createRadioButton(RadioGroup mRadioGroup, final QuestionRecommenderForm form, final int i) {
      RecommenderQuestionResponse response = form.getPosibleResponses().get(i);
      RadioButton option = new RadioButton(getActivity());
      option.setText(response.text);
      option.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      option.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            form.setSelectedResponse(i);
            // TODO: Descomentar para pasar de pregunta cuando seleccionemos una respuesta
            // ((ViewPager)mContainer).setCurrentItem(((ViewPager)mContainer).getCurrentItem() + 1, true);
         }
      });
      mRadioGroup.addView(option);
   }
}