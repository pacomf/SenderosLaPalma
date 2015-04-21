package com.jelcaf.pacomf.patealapalma.recommender;

import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public class RecommenderBaseQuestion implements IRecommenderQuestion, Serializable {

   private String question;
   protected boolean mandatory = false;
   private ISenderoFilter mSenderoFilter = null;

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
      return mandatory;
   }

   @Override
   public boolean checkSendero(Sendero sendero) {
      if (mSenderoFilter == null) {
         return true;
      }
      return mSenderoFilter.filterSendero(sendero);
   }

   @Override
   public void addSenderoFilter(ISenderoFilter senderoFilter) {
      mSenderoFilter = senderoFilter;
   }

}
