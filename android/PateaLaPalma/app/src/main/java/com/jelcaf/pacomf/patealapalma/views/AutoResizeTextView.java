package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * @author Jorge Carballo
 *         25/03/15
 */
public class AutoResizeTextView extends TextView {

   private Handler measureHandler = new Handler();
   private Runnable requestLayout = new Runnable() {
      @Override
      public void run() {
         requestLayout();
      }
   };

   public AutoResizeTextView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
   }

   public AutoResizeTextView(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   public AutoResizeTextView(Context context) {
      super(context);
   }

   @Override
   protected void onMeasure(final int widthMeasureSpec,
                            final int heightMeasureSpec) {
      super.onMeasure(widthMeasureSpec, heightMeasureSpec);

      final float maxWidth = getWidth();
      final float maxHeight = getHeight();
      if (maxWidth < 1.0f || maxHeight < 1.0f) {
         return;
      }

      int index = 0;
      int lineCount = 0;
      CharSequence text = getText();
      final TextPaint paint = getPaint();
      while (index < text.length()) {
         index += paint.breakText(text, index, text.length(), true, maxWidth, null);
         lineCount++;
      }
      final float height = lineCount * getLineHeight() + (lineCount > 0 ?
            (lineCount - 1) * paint.getFontSpacing() : 0);
      if (height > maxHeight) {
         final float textSize = getTextSize();
         setTextSize(TypedValue.COMPLEX_UNIT_PX, (textSize - 1));
         measureHandler.post(requestLayout);
      }
   }
}
