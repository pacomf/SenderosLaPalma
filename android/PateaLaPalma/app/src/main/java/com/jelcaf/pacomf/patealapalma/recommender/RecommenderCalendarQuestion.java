package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderCalendarQuestion extends RecommenderBaseQuestion implements Serializable {

   private Date value;

   public RecommenderCalendarQuestion(String question) {
      super(question);
   }

   @Override
   public QuestionType getQuestionType() {
      return QuestionType.Calendar;
   }

   @Override
   public Object getResponse() {
      return value;
   }

   @Override
   public Object getResponseType() {
      return ResponseType.Date;
   }

}
