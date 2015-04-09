package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.util.AttributeSet;

import com.jelcaf.pacomf.patealapalma.R;

import me.relex.circleindicator.CircleIndicator;

/**
 * @author Jorge Carballo
 *         09/04/15
 */
public class MyCircleIndicator extends CircleIndicator {

   public MyCircleIndicator(Context context) {
      super(context);
   }

   public MyCircleIndicator(Context context, AttributeSet attrs) {
      super(context, attrs);
   }

   @Override
   public void onPageSelected(int position) {
      super.onPageSelected(position);

//      if (position < mPagerAdapter.getCount()) {
//         mNextButton.setText(getResources().getString(R.string.next_text));
//         return;
//      }
//      mNextButton.setText(getResources().getString(R.string.done_text));
   }

}