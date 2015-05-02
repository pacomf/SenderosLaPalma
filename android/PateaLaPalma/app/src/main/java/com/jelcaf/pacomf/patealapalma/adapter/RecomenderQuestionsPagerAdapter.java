package com.jelcaf.pacomf.patealapalma.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jelcaf.pacomf.patealapalma.fragment.QuestionFragment;
import com.jelcaf.pacomf.patealapalma.fragment.RecomenderInfoFragment;
import com.jelcaf.pacomf.patealapalma.fragment.RecomenderResumeFragment;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderForm;

import java.util.Random;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecomenderQuestionsPagerAdapter extends FragmentPagerAdapter {

   private static int NUM_EXTRA_PAGES = 2;

   public RecommenderForm form;
   private RecomenderInfoFragment recomenderInfo;
   private RecomenderResumeFragment recomenderResume;

   public RecomenderQuestionsPagerAdapter(FragmentManager fm, Context ctx) {
      super(fm);

      form = RecommenderForm.getInstance(ctx);

      recomenderResume = new RecomenderResumeFragment();
      recomenderInfo = new RecomenderInfoFragment();
   }

   @Override public Fragment getItem(int i) {
      if (i < form.getQuestions().size()) {
         RecommenderBaseQuestion formQuestion = form.getQuestions().get(i);
         return QuestionFragment.newInstance(formQuestion);
      }
      if (i == form.getQuestions().size()) {
         return recomenderInfo;
      }
      return recomenderResume;
   }

   @Override public int getCount() {
      return form.getQuestions().size() + NUM_EXTRA_PAGES;
   }
}
