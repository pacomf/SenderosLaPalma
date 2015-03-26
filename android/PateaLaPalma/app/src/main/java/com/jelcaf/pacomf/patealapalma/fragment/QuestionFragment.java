package com.jelcaf.pacomf.patealapalma.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

   private ViewGroup mContainer;
   private QuestionRecommenderForm mQuestion;

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
         createRadioButton(mRadioGroup, mQuestion.getPosibleResponses().get(i));
      }

      return v;
   }

   private void createRadioButton(RadioGroup mRadioGroup, RecommenderQuestionResponse response) {
      RadioButton option = new RadioButton(getActivity());
      option.setText(response.text);
      option.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            // TODO: Descomentar para pasar de pregunta cuando seleccionemos una respuesta
            // ((ViewPager)mContainer).setCurrentItem(((ViewPager)mContainer).getCurrentItem() + 1, true);
         }
      });
      mRadioGroup.addView(option);
   }
}