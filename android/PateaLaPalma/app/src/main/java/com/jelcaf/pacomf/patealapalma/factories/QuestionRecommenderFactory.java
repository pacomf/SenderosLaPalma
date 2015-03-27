package com.jelcaf.pacomf.patealapalma.factories;

import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderRangeQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderSingleChoiceQuestion;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderOptionResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         26/03/15
 */
public class QuestionRecommenderFactory {

   private static final Integer START_VALUE = 1;
   private static QuestionRecommenderFactory instance;
   private List<RecommenderBaseQuestion> questionForm;

   public static QuestionRecommenderFactory getInstance() {
      if (instance == null) {
         instance = new QuestionRecommenderFactory();
      }
      return instance;
   }

   public List<RecommenderBaseQuestion> obtainQuestionForm() {
      if (questionForm != null) {
         return questionForm;
      }
      questionForm = new ArrayList<RecommenderBaseQuestion>();

      String strQuestion = "¿Cuántas personas irán al sendero?";
      ArrayList<String> strResponses = new ArrayList<String>();
      strResponses.add("Menos de 5");
      strResponses.add("Entre 5 y 10");
      strResponses.add("Más de 10");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Irán niños al sendero?";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Tiempo del recorrido del sendero?";
      strResponses.clear();
      strResponses.add("Menos de 1 hora");
      strResponses.add("Entre 1 y 2 horas");
      strResponses.add("Menos de 4 horas");
      strResponses.add("Más de 4 horas");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Dificultad?";
      strResponses.clear();
      strResponses.add("Baja");
      strResponses.add("Media");
      strResponses.add("Alta");
      strResponses.add("Extrema");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿El sendero es Circular?";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Fecha en la que se desea realizar el sendero";
      strResponses.clear();
      strResponses.add("TODO");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "Distancia a la que se encuentra el sendero desde nuestra posición";
      strResponses.clear();
      strResponses.add("Menos de 10km");
      strResponses.add("Menos de 20km");
      strResponses.add("Menos de 50km");
      strResponses.add("No importa la distancia");
      questionForm.add(createRangeQuestion(strQuestion, 0, 100));

      strQuestion = "¿Tiene puntos de agua potable durante el recorrido";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSingleChoiceQuestion(strQuestion, strResponses));

      return questionForm;
   }

   private RecommenderSingleChoiceQuestion createSingleChoiceQuestion(String strQuestion, List<String> strResponses) {
      List<RecommenderOptionResponse> responses = new ArrayList<RecommenderOptionResponse>();
      Integer i = START_VALUE;
      for (String strResponse : strResponses) {
         RecommenderOptionResponse response = new RecommenderOptionResponse(strResponse, i.toString());
         responses.add(response);

         i++;
      }
      RecommenderSingleChoiceQuestion question = new RecommenderSingleChoiceQuestion(strQuestion, responses);
      return question;
   }

   private RecommenderRangeQuestion createRangeQuestion(String strQuestion, Integer minValue, Integer maxValue) {
      List<RecommenderOptionResponse> responses = new ArrayList<RecommenderOptionResponse>();
      Integer i = START_VALUE;

      RecommenderRangeQuestion question = new RecommenderRangeQuestion(strQuestion, minValue, maxValue);
      return question;
   }
}
