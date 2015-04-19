package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.jelcaf.pacomf.patealapalma.R;

/**
 * @author Jorge Carballo
 *         19/04/15
 */
public class PhotoSliderView extends BaseSliderView {

   public ImageView target;
   private LinearLayout mTextLayout;
   private int textBackgroundColor;

   public PhotoSliderView(Context context) {
      super(context);
   }

   @Override
   public View getView() {
      View v = LayoutInflater.from(getContext()).inflate(R.layout.render_type_slider, null);
      target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
      mTextLayout = (LinearLayout)v.findViewById(R.id.description_layout);
      mTextLayout.setBackgroundColor(textBackgroundColor);
      bindEventAndShow(v, target);
      return v;
   }

   public BaseSliderView textBackgroundColor(int color){
      textBackgroundColor = color;
      return this;
   }

}
