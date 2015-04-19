package com.jelcaf.pacomf.patealapalma.recommender;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public interface IRecommenderQuestion {

   public String getQuestion();
   public String getResumeQuestion();
   public QuestionType getQuestionType();
   public Object getResponse();
   public String getStrResponse();
   public Object getResponseType();
   public boolean isMandatory();

}
