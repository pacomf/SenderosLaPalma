package com.jelcaf.pacomf.patealapalma.recommender;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderForm {

   private List<RecommenderSingleChoiceQuestion> questions;

   public RecommenderForm() {
      questions = new ArrayList<>();
   }

   public void addQuestion (RecommenderSingleChoiceQuestion question) {
      questions.add(question);
   }

}
