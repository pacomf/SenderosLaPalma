package com.jelcaf.pacomf.patealapalma.factories;

import com.jelcaf.pacomf.patealapalma.binding.QuestionRecommenderForm;
import com.jelcaf.pacomf.patealapalma.binding.RecommenderQuestionResponse;
import com.jelcaf.pacomf.patealapalma.binding.ResponseType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         26/03/15
 */
public class QuestionRecommenderFactory {

   private static final Integer START_VALUE = 1;
   private static QuestionRecommenderFactory instance;
   private List<QuestionRecommenderForm> questionForm;

   public static QuestionRecommenderFactory getInstance() {
      if (instance == null) {
         instance = new QuestionRecommenderFactory();
      }
      return instance;
   }

   public List<QuestionRecommenderForm> obtainQuestionForm() {
      if (questionForm != null) {
         return questionForm;
      }
      questionForm = new ArrayList<QuestionRecommenderForm>();

      String strQuestion = "¿Cuántas personas irán al sendero?";
      ArrayList<String> strResponses = new ArrayList<String>();
      strResponses.add("Menos de 5");
      strResponses.add("Entre 5 y 10");
      strResponses.add("Más de 10");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿Irán niños al sendero?";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿Tiempo del recorrido del sendero?";
      strResponses.clear();
      strResponses.add("Menos de 1 hora");
      strResponses.add("Entre 1 y 2 horas");
      strResponses.add("Menos de 4 horas");
      strResponses.add("Más de 4 horas");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿Dificultad?";
      strResponses.clear();
      strResponses.add("Baja");
      strResponses.add("Media");
      strResponses.add("Alta");
      strResponses.add("Extrema");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿El sendero es Circular?";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿Fecha en la que se desea realizar el sendero";
      strResponses.clear();
      strResponses.add("TODO");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "Distancia a la que se encuentra el sendero desde nuestra posición";
      strResponses.clear();
      strResponses.add("Menos de 10km");
      strResponses.add("Menos de 20km");
      strResponses.add("Menos de 50km");
      strResponses.add("No importa la distancia");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      strQuestion = "¿Tiene puntos de agua potable durante el recorrido";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questionForm.add(createSimpleQuestion(strQuestion, strResponses, ResponseType.IntegerType));

      return questionForm;
   }

   private QuestionRecommenderForm createSimpleQuestion(String strQuestion, List<String> strResponses,
                                     ResponseType type) {
      List<RecommenderQuestionResponse> responses = new ArrayList<RecommenderQuestionResponse>();
      Integer i = START_VALUE;
      for (String strResponse : strResponses) {
         RecommenderQuestionResponse response = new RecommenderQuestionResponse(strResponse, i.toString());
         responses.add(response);

         i++;
      }
      QuestionRecommenderForm question = new QuestionRecommenderForm(strQuestion, responses, type);
      return question;
   }
}
