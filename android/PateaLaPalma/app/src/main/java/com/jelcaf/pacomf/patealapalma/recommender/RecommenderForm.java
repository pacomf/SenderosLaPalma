package com.jelcaf.pacomf.patealapalma.recommender;

import android.os.Parcelable;

import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderForm {

   private static final Integer START_VALUE = 1;
   private static RecommenderForm instance;
   private static List<RecommenderBaseQuestion> questions;

   public static RecommenderForm getInstance() {
      if (instance == null) {
         instance = new RecommenderForm();
         questions = new ArrayList<>();
         initializeQuestionForm();
      }
      return instance;
   }

   public List<RecommenderBaseQuestion> getQuestions() {
      return questions;
   }

   public List<RecommenderBaseQuestion> getQuestions(int exceptLastNumber) {
      return questions;
   }

   public void addQuestion (RecommenderBaseQuestion question) {
      questions.add(question);
   }

   public void add(RecommenderBaseQuestion question) {
      questions.add(question);
   }

   public static RecommenderForm initializeQuestionForm() {

      String strQuestion = "¿Cuántas personas irán al sendero?";
      ArrayList<String> strResponses = new ArrayList<String>();
      strResponses.add("Menos de 5");
      strResponses.add("Entre 5 y 10");
      strResponses.add("Más de 10");
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses, true));

      strQuestion = "¿Irán niños al sendero?";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Tiempo del recorrido del sendero?";
      strResponses.clear();
      strResponses.add("Menos de 1 hora");
      strResponses.add("Menos de 2 horas");
      strResponses.add("Menos de 4 horas");
      strResponses.add("Más de 4 horas");
      final RecommenderSingleChoiceQuestion timeQuestion = createSingleChoiceQuestion(strQuestion, strResponses);
      timeQuestion.addSenderoFilter(new ISenderoFilter() {

         @Override
         public boolean filterSendero(Sendero sendero) {
            if (timeQuestion.getSelectedResponse() == null) {
               return true;
            }
            if (timeQuestion.getSelectedResponse().equals(0)) {
               return (sendero.getLength() * SenderosConstants.SECONDS_IN_KM_MEDIUM) < SenderosConstants.SECONDS_IN_A_HOUR;
            } else if (timeQuestion.getSelectedResponse().equals(1)) {
               return (sendero.getLength() * SenderosConstants.SECONDS_IN_KM_MEDIUM) < (SenderosConstants.SECONDS_IN_A_HOUR * 2);
            } else if (timeQuestion.getSelectedResponse().equals(2)) {
               return (sendero.getLength() * SenderosConstants.SECONDS_IN_KM_MEDIUM) < (SenderosConstants.SECONDS_IN_A_HOUR * 4);
            } else if (timeQuestion.getSelectedResponse().equals(3)) {
               return (sendero.getLength() * SenderosConstants.SECONDS_IN_KM_MEDIUM) > (SenderosConstants.SECONDS_IN_A_HOUR * 4);
            }
            return false;
         }
      });
      questions.add(timeQuestion);

      strQuestion = "¿Dificultad?";
      strResponses.clear();
      strResponses.add("Baja");
      strResponses.add("Media");
      strResponses.add("Alta");
      strResponses.add("Extrema");
      final RecommenderSingleChoiceQuestion difficultyQuestion = createSingleChoiceQuestion(strQuestion, strResponses);
      difficultyQuestion.addSenderoFilter(new ISenderoFilter() {
         @Override
         public boolean filterSendero(Sendero sendero) {
            if (difficultyQuestion.getSelectedResponse() == null || sendero.getDifficulty() == null
                  || sendero.getDifficulty().equals(difficultyQuestion.getStrResponse())) {
               return true;
            }
            return false;
         }
      });
      questions.add(difficultyQuestion);

//      strQuestion = "¿El sendero es Circular?";
//      strResponses.clear();
//      strResponses.add("Si");
//      strResponses.add("No");
//      questions.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = "¿Fecha en la que se desea realizar el sendero";
      questions.add(createCalendarQuestion(strQuestion));

//      strQuestion = "Distancia a la que se encuentra el sendero desde nuestra posición";
//      strResponses.clear();
//      strResponses.add("Menos de 10km");
//      strResponses.add("Menos de 20km");
//      strResponses.add("Menos de 50km");
//      strResponses.add("No importa la distancia");
//      questions.add(createRangeQuestion(strQuestion, 0, 100));

      strQuestion = "¿Tiene puntos de agua potable durante el recorrido";
      strResponses.clear();
      strResponses.add("Si");
      strResponses.add("No");
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses));

      return RecommenderForm.getInstance();
   }

   private static RecommenderSingleChoiceQuestion createSingleChoiceQuestion(String strQuestion,
                                                                       List<String> strResponses) {
      return createSingleChoiceQuestion(strQuestion, strResponses, false);
   }

   private static RecommenderSingleChoiceQuestion createSingleChoiceQuestion(String strQuestion,
                                                                      List<String> strResponses, Boolean mandatory) {
      List<RecommenderOptionResponse> responses = new ArrayList<RecommenderOptionResponse>();
      Integer i = START_VALUE;
      for (String strResponse : strResponses) {
         RecommenderOptionResponse response = new RecommenderOptionResponse(strResponse, i.toString());
         responses.add(response);

         i++;
      }
      RecommenderSingleChoiceQuestion question = new RecommenderSingleChoiceQuestion(strQuestion, responses);
      question.setMandatory(mandatory);
      return question;
   }

   private static RecommenderRangeQuestion createRangeQuestion(String strQuestion,
                                                               Integer minValue, Integer maxValue) {
      RecommenderRangeQuestion question = new RecommenderRangeQuestion(strQuestion, minValue, maxValue);
      return question;
   }

   private static RecommenderCalendarQuestion createCalendarQuestion(String strQuestion) {
      RecommenderCalendarQuestion question = new RecommenderCalendarQuestion(strQuestion);
      return question;
   }
}
