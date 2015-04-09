package com.jelcaf.pacomf.patealapalma.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jelcaf.pacomf.patealapalma.fragment.RecomenderResumeFragment;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.factories.QuestionRecommenderFactory;
import com.jelcaf.pacomf.patealapalma.fragment.QuestionFragment;

import java.util.List;
import java.util.Random;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecomenderQuestionsPagerAdapter extends FragmentPagerAdapter {

   private static int NUM_EXTRA_PAGES = 1;

   private List<RecommenderBaseQuestion> form;
   private RecomenderResumeFragment recomenderResume;

   private Random random = new Random();

   public RecomenderQuestionsPagerAdapter(FragmentManager fm) {
      super(fm);

      form = QuestionRecommenderFactory.getInstance().obtainQuestionForm();
      recomenderResume = new RecomenderResumeFragment();
   }

   @Override public Fragment getItem(int i) {
      if (i < form.size()) {
         RecommenderBaseQuestion formQuestion = form.get(i);
         return QuestionFragment.newInstance(formQuestion);
      }
      return recomenderResume;
   }

   @Override public int getCount() {
      return form.size() + NUM_EXTRA_PAGES;
   }
}
