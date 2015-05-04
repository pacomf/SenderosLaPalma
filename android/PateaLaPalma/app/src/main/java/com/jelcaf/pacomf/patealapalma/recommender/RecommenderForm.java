package com.jelcaf.pacomf.patealapalma.recommender;

import android.content.Context;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderForm {

   private static final Integer START_VALUE = 1;
   private static RecommenderForm instance;
   private static List<RecommenderBaseQuestion> questions;

   public static RecommenderForm getInstance(Context ctx) {
      if (instance == null) {
         instance = new RecommenderForm();
         questions = new ArrayList<>();
         initializeQuestionForm(ctx);
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

   public static RecommenderForm initializeQuestionForm(final Context ctx) {

      String strQuestion = ctx.getString(R.string.qPeople);
      ArrayList<String> strResponses = new ArrayList<String>();
      strResponses.add(ctx.getString(R.string.qPeopleLess5));
      strResponses.add(ctx.getString(R.string.qPeople5y10));
      strResponses.add(ctx.getString(R.string.qPeopleMore10));
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses, true));

      strQuestion = ctx.getString(R.string.qChildren);
      strResponses.clear();
      strResponses.add(ctx.getString(R.string.yes));
      strResponses.add(ctx.getString(R.string.no));
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses));

      strQuestion = ctx.getString(R.string.qTime);
      strResponses.clear();
      strResponses.add(ctx.getString(R.string.qTimeLess1));
      strResponses.add(ctx.getString(R.string.qTimeLess2));
      strResponses.add(ctx.getString(R.string.qTimeLess4));
      strResponses.add(ctx.getString(R.string.qTimeMore4));
      final RecommenderSingleChoiceQuestion timeQuestion = createSingleChoiceQuestion(strQuestion, strResponses);
      timeQuestion.addSenderoFilter(new ISenderoFilter() {

         @Override
         public boolean filterSendero(Sendero sendero) {
            double seconds = SenderosConstants.getSecondsByDifficulty(sendero.getDifficulty(), ctx);
            if (timeQuestion.getSelectedResponse() == null) {
               return true;
            }
            if (timeQuestion.getSelectedResponse().equals(0)) {
               return (sendero.getLength() * seconds) < SenderosConstants.SECONDS_IN_A_HOUR;
            } else if (timeQuestion.getSelectedResponse().equals(1)) {
               return (sendero.getLength() * seconds) < (SenderosConstants.SECONDS_IN_A_HOUR * 2);
            } else if (timeQuestion.getSelectedResponse().equals(2)) {
               return (sendero.getLength() * seconds) < (SenderosConstants.SECONDS_IN_A_HOUR * 4);
            } else if (timeQuestion.getSelectedResponse().equals(3)) {
               return (sendero.getLength() * seconds) > (SenderosConstants.SECONDS_IN_A_HOUR * 4);
            }
            return false;
         }
      });
      questions.add(timeQuestion);

      strQuestion = ctx.getString(R.string.qDifficulty);
      strResponses.clear();
      strResponses.add(ctx.getString(R.string.qDifficultyLow));
      strResponses.add(ctx.getString(R.string.qDifficultyMedium));
      strResponses.add(ctx.getString(R.string.qDifficultyHigh));
      strResponses.add(ctx.getString(R.string.qDifficultyExtreme));
      final RecommenderSingleChoiceQuestion difficultyQuestion = createSingleChoiceQuestion(strQuestion, strResponses);
      difficultyQuestion.addSenderoFilter(new ISenderoFilter() {
         @Override
         public boolean filterSendero(Sendero sendero) {
            Map<String, String> diffilcultyMap = SenderosConstants.getMapDifficulty(ctx);
            if (difficultyQuestion.getSelectedResponse() == null || sendero.getDifficulty() == null
                  || sendero.getDifficulty().equals(diffilcultyMap.get(difficultyQuestion.getStrResponse()))) {
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

      strQuestion = ctx.getString(R.string.qDate);
      questions.add(createCalendarQuestion(strQuestion));

//      strQuestion = "Distancia a la que se encuentra el sendero desde nuestra posición";
//      strResponses.clear();
//      strResponses.add("Menos de 10km");
//      strResponses.add("Menos de 20km");
//      strResponses.add("Menos de 50km");
//      strResponses.add("No importa la distancia");
//      questions.add(createRangeQuestion(strQuestion, 0, 100));

      strQuestion = ctx.getString(R.string.qWaterPoints);
      strResponses.clear();
      strResponses.add(ctx.getString(R.string.yes));
      strResponses.add(ctx.getString(R.string.no));
      questions.add(createSingleChoiceQuestion(strQuestion, strResponses));

      return RecommenderForm.getInstance(ctx);
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
