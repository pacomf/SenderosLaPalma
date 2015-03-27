package com.jelcaf.pacomf.patealapalma.formats;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public class DateFormats {

   private static DateFormat dateFormat = DateFormat.getDateInstance();

   public static String getDateFormatted(Date date) {
      return dateFormat.format(date);
   }
}
