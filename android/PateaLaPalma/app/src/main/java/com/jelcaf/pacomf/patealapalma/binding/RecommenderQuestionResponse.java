package com.jelcaf.pacomf.patealapalma.binding;

import java.io.Serializable;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderQuestionResponse implements Serializable {

   public String text;
   public String value;

   public RecommenderQuestionResponse (String text, String value) {
      this.text = text;
      this.value = value;
   }
}
