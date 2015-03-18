package com.jelcaf.pacomf.patealapalma.binding.parser;

import com.mobandme.android.bind.compiler.Compiler;
import com.mobandme.android.bind.parser.DataParser;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class IntegerParse extends DataParser {
   @Override
   public Object onParse(Compiler.Mapping mapping, Object value, int direction) {
      Object result = value;

      if (value != null) {
         final Class<?> fieldType = mapping.getField().getType();

         if (fieldType == Integer.class || fieldType == int.class) {
            if (value.getClass() == String.class) {
               if (!((String)value).trim().equals("")) {
                  result = Integer.parseInt((String)value);
               }
            } else {
               result = value.toString();
            }
         }
      }

      return result;
   }
}
