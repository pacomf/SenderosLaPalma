package com.jelcaf.pacomf.patealapalma.recommender;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderForm implements Serializable {

   public List<RecommenderBaseQuestion> questions;

   public RecommenderForm() {
      questions = new ArrayList<>();
   }

   public RecommenderForm(List<RecommenderBaseQuestion> questions) {
      this.questions = questions;
   }

   public void addQuestion (RecommenderBaseQuestion question) {
      questions.add(question);
   }

}
