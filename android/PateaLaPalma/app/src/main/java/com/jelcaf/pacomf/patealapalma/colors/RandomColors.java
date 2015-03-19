package com.jelcaf.pacomf.patealapalma.colors;

import android.graphics.Color;

import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by jelcaf on 21/01/15.
 */
public class RandomColors {

   public static final ColorGenerator generator = ColorGenerator.MATERIAL;

   /**
    * Returns darker version of specified <code>color</code>.
    */
   public static int darker(int color, float factor) {
      int a = Color.alpha(color);
      int r = Color.red(color);
      int g = Color.green(color);
      int b = Color.blue(color);

      return Color.argb(a,
            Math.max((int) (r * factor), 0),
            Math.max((int) (g * factor), 0),
            Math.max((int) (b * factor), 0));
   }
}
