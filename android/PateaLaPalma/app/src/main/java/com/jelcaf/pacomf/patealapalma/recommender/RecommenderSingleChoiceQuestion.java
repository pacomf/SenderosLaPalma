package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderSingleChoiceQuestion extends RecommenderBaseQuestion implements Serializable {

   private List<RecommenderOptionResponse> posibleResponses;
   private Integer selectedResponse;

   public RecommenderSingleChoiceQuestion(String question, List<RecommenderOptionResponse> posibleResponses) {
      super(question);

      this.posibleResponses = posibleResponses;
   }


   @Override
   public QuestionType getQuestionType() {
      return QuestionType.SingleChoice;
   }

   @Override
   public Object getResponse() {
      return getSelectedResponse();
   }

   @Override
   public Object getResponseType() {
      return ResponseType.Integer;
   }

   public void addResponses(List<RecommenderOptionResponse> responses) {
      this.posibleResponses = responses;
   }

   public List<RecommenderOptionResponse> getPosibleResponses() {
      return posibleResponses;
   }

   public Integer getSelectedResponse() {
      return selectedResponse;
   }

   public void setSelectedResponse(Integer selectedResponse) {
      this.selectedResponse = selectedResponse;
   }
}
