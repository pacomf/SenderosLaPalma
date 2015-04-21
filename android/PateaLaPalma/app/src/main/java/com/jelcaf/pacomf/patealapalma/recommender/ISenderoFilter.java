package com.jelcaf.pacomf.patealapalma.recommender;

import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

/**
 * @author Jorge Carballo
 *         21/04/15
 */
public interface ISenderoFilter {

   public boolean filterSendero(Sendero sendero);
}
