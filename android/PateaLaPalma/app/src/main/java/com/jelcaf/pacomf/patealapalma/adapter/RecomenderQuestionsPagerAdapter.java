package com.jelcaf.pacomf.patealapalma.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.factories.QuestionRecommenderFactory;
import com.jelcaf.pacomf.patealapalma.fragment.QuestionFragment;
import com.jelcaf.pacomf.patealapalma.fragment.RecomenderResumeFragment;
import com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderForm;

import java.util.List;
import java.util.Random;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecomenderQuestionsPagerAdapter extends FragmentPagerAdapter {

   private static int NUM_EXTRA_PAGES = 1;

   public List<RecommenderBaseQuestion> form;
   private RecomenderResumeFragment recomenderResume;

   private Random random = new Random();

   public RecomenderQuestionsPagerAdapter(FragmentManager fm) {
      super(fm);

      form = QuestionRecommenderFactory.getInstance().obtainQuestionForm();

      Bundle arguments = new Bundle();
      arguments.putSerializable(RecomenderResumeFragment.ARG_FORM, new RecommenderForm(form));
      recomenderResume = new RecomenderResumeFragment();
      recomenderResume.setArguments(arguments);
//      fm.beginTransaction()
//            .replace(R.id.sendero_detail_container, recomenderResume)
//            .commit();
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
