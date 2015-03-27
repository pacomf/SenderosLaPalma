package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderRangeQuestion extends RecommenderBaseQuestion implements Serializable {

   private Integer minValue = 0;
   private Integer maxValue = 100;
   private Integer value;

   public RecommenderRangeQuestion(String question, Integer minValue, Integer maxValue) {
      super(question);

      this.minValue = minValue;
      this.maxValue = maxValue;
   }


   @Override
   public QuestionType getQuestionType() {
      return QuestionType.Range;
   }

   @Override
   public Object getResponse() {
      return value;
   }

   @Override
   public Object getResponseType() {
      return ResponseType.Integer;
   }

   public Integer getMinValue() {
      return minValue;
   }

   public Integer getMaxValue() {
      return maxValue;
   }
}
