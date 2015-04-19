package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public class RecommenderBaseQuestion implements IRecommenderQuestion, Serializable {

   private String question;

   protected RecommenderBaseQuestion(String question) {
      this.question = question;
   }

   @Override
   public String getQuestion() {
      return question;
   }

   @Override
   public String getResumeQuestion() {
      return getQuestion();
   }

   @Override
   public QuestionType getQuestionType() {
      return QuestionType.Base;
   }

   @Override
   public Object getResponse() {
      return null;
   }

   @Override
   public String getStrResponse() {
      if (getResponse() == null) {
         return "N/C";
      }
      return getResponse().toString();
   }

   @Override
   public Object getResponseType() {
      return null;
   }

   @Override
   public boolean isMandatory() {
      return false;
   }


}
