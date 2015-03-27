package com.jelcaf.pacomf.patealapalma.recommender;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public interface IRecommenderQuestion {

   public String getQuestion();
   public QuestionType getQuestionType();
   public Object getResponse();
   public Object getResponseType();

}
