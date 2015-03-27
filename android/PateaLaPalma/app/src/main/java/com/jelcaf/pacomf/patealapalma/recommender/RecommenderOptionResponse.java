package com.jelcaf.pacomf.patealapalma.recommender;

import java.io.Serializable;

/**
 * @author Jorge Carballo
 *         24/03/15
 */
public class RecommenderOptionResponse implements Serializable {

   public String text;
   public String value;

   public RecommenderOptionResponse(String text, String value) {
      this.text = text;
      this.value = value;
   }
}
