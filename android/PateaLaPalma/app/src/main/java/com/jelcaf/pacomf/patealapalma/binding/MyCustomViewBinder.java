package com.jelcaf.pacomf.patealapalma.binding;

import android.view.View;

import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.views.SimpleDataView;
import com.mobandme.android.bind.Binder;
import com.mobandme.android.bind.binder.DataBinder;
import com.mobandme.android.bind.compiler.*;
import com.mobandme.android.bind.compiler.Compiler;

/**
 * @author Jorge Carballo
 *         24/04/15
 */
public class MyCustomViewBinder extends DataBinder {

   @Override
   public void onBind(Compiler.Mapping mapping, Object object, View view, int direction) {
//      String value = mapping.getField().toString();
      String value = "";
      Sendero sendero = (Sendero)object;

      if (direction == Binder.DIRECTION_OBJECT_TO_VIEWS) {
         if ("length".equals(mapping.getField().getName())) {
            value = sendero.getLength().toString() + "km";
         } else if ("difficulty".equals(mapping.getField().getName())) {
            value = sendero.getDifficulty();
         } else if ("type".equals(mapping.getField().getName())) {
            value = sendero.getType().toString();
         }
         ((SimpleDataView)view).setValue(value);
      } else {
//         mapping.getField().set(object, ((SimpleDataView)view).getTitle());
      }
   }
}