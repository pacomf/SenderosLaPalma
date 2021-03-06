package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderCalendarQuestion extends RecommenderBaseQuestion implements Serializable {

   private Calendar value;

   public RecommenderCalendarQuestion(String question) {
      super(question);

      value = Calendar.getInstance();
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

   @Override
   public String getStrResponse() {
      if (!Calendar.getInstance().equals(getResponse())) {
         String theDate = value.get(Calendar.DAY_OF_MONTH) + "/" + value.get(Calendar
               .MONTH) + "/" + value.get(Calendar.YEAR);
         return theDate;
      }
      return "N/C";
   }

   public Calendar getSelectedResponse() {
      return value;
   }

   public void setSelectedResponse(Calendar selectedResponse) {
      this.value = selectedResponse;
   }

}
