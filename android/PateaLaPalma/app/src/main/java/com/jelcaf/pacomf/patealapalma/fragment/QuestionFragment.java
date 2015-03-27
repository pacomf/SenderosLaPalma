package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;
import com.gc.materialdesign.views.Slider;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderRangeQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderSingleChoiceQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderOptionResponse;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class QuestionFragment extends Fragment {

   private static final String ARG_QUESTION = "question";
   private static final String TAG = QuestionFragment.class.getCanonicalName();

   private ViewGroup mContainer;
   private RecommenderBaseQuestion mQuestion;
   private QuestionFragment mFragment;

   public static QuestionFragment newInstance(RecommenderBaseQuestion question) {
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
         mQuestion = (RecommenderBaseQuestion)getArguments().getSerializable(ARG_QUESTION);
      }
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      mContainer = container;
      View v = inflater.inflate(R.layout.question_fragment, container, false);

      ((TextView)v.findViewById(R.id.question)).setText(mQuestion.getQuestion());

      LinearLayout answerLinearLayout = (LinearLayout)v.findViewById(R.id.answer);

      switch (mQuestion.getQuestionType()) {
         case SingleChoice:
            createSingleChoice(answerLinearLayout, (RecommenderSingleChoiceQuestion)mQuestion);
            break;
         case Range:
            createRange(inflater, answerLinearLayout, (RecommenderRangeQuestion)mQuestion);
            break;
      }

      return v;
   }

   private void createRange(LayoutInflater inflater, LinearLayout answerLinearLayout, RecommenderRangeQuestion question) {
      RangeBar slider = (RangeBar)inflater.inflate(R.layout.question_slider, answerLinearLayout,
            false);
      slider.setTickCount(question.getMaxValue() - question.getMinValue());
//      slider.setLeft(question.getMinValue());
//      slider.setRight(question.getMaxValue());
//      slider.setShowNumberIndicator(true);

      answerLinearLayout.addView(slider);
   }

   private void createSingleChoice(LinearLayout answerLinearLayout, RecommenderSingleChoiceQuestion question) {
      RadioGroup mRadioGroup = new RadioGroup(getActivity());
      mRadioGroup.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
            .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
      answerLinearLayout.addView(mRadioGroup);

      for (int i=0; i < question.getPosibleResponses().size(); i++) {
         createRadioButton(mRadioGroup, question, i);
      }

      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(question.getQuestion() + " -> (" + question.getSelectedResponse() + ")");
      if (question.getSelectedResponse() != null) {
         stringBuilder.append(" " + ((RadioButton)mRadioGroup.getChildAt(question
               .getSelectedResponse()))
               .getText());
         ((RadioButton) mRadioGroup.getChildAt(question.getSelectedResponse())).setChecked(true);
      }
      Log.i(TAG, stringBuilder.toString());
   }

   private void createRadioButton(RadioGroup mRadioGroup, final RecommenderSingleChoiceQuestion form, final int i) {
      RecommenderOptionResponse response = form.getPosibleResponses().get(i);
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