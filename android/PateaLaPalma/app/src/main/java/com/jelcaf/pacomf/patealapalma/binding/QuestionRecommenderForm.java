package com.jelcaf.pacomf.patealapalma.binding;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class QuestionRecommenderForm implements Serializable {

   private String question;
   private List<RecommenderQuestionResponse> posibleResponses;
   private ResponseType type;
   private Integer selectedResponse;

   public QuestionRecommenderForm (String question, List<RecommenderQuestionResponse> posibleResponses, ResponseType type) {
      this.question = question;
      this.posibleResponses = posibleResponses;
      this.type = type;
   }


   public String getQuestion() {
      return question;
   }

   public void addResponses(List<RecommenderQuestionResponse> responses) {
      this.posibleResponses = responses;
   }

   public List<RecommenderQuestionResponse> getPosibleResponses() {
      return posibleResponses;
   }

   public Integer getSelectedResponse() {
      return selectedResponse;
   }

   public void setSelectedResponse(Integer selectedResponse) {
      this.selectedResponse = selectedResponse;
   }
}
