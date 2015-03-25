package com.jelcaf.pacomf.patealapalma.binding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderForm {

   private List<QuestionRecommenderForm> questions;

   public RecommenderForm() {
      questions = new ArrayList<>();
   }

   public void addQuestion (QuestionRecommenderForm question) {
      questions.add(question);
   }

}
