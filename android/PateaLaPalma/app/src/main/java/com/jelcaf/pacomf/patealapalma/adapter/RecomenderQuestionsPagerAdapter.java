package com.jelcaf.pacomf.patealapalma.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jelcaf.pacomf.patealapalma.binding.QuestionRecommenderForm;
import com.jelcaf.pacomf.patealapalma.binding.RecommenderQuestionResponse;
import com.jelcaf.pacomf.patealapalma.binding.ResponseType;
import com.jelcaf.pacomf.patealapalma.fragment.QuestionFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecomenderQuestionsPagerAdapter extends FragmentPagerAdapter {

   private List<QuestionRecommenderForm> form;

   private Random random = new Random();

   public RecomenderQuestionsPagerAdapter(FragmentManager fm) {
      super(fm);

      form = new ArrayList<QuestionRecommenderForm>();

      RecommenderQuestionResponse response1 = new RecommenderQuestionResponse("Respuesta1", "1");
      RecommenderQuestionResponse response2 = new RecommenderQuestionResponse("Respuesta2", "2");
      List<RecommenderQuestionResponse> responses = new ArrayList<RecommenderQuestionResponse>();
      responses.add(response1);
      responses.add(response2);
      QuestionRecommenderForm question = new QuestionRecommenderForm("¿Cuántas personas irán al " +
            "sendero que es super largo y no hay forma de adecuarlo al tamaño que tiene ahora " +
            "mismo?",
            responses,
            ResponseType.IntegerType);

      form.add(question);

      RecommenderQuestionResponse responseA1 = new RecommenderQuestionResponse("Mi 1", "1");
      RecommenderQuestionResponse responseA2 = new RecommenderQuestionResponse("Mi 2", "2");
      responses = new ArrayList<RecommenderQuestionResponse>();
      responses.add(responseA1);
      responses.add(responseA2);
      question = new QuestionRecommenderForm("Pregunta 234", responses,
            ResponseType.IntegerType);

      form.add(question);
   }

   @Override public Fragment getItem(int i) {
      QuestionRecommenderForm formQuestion = form.get(i);
      return QuestionFragment.newInstance(formQuestion);
   }

   @Override public int getCount() {
      return form.size();
   }
}
