package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * @author Jorge Carballo
 *         13/03/15
 */
public class SimpleDataView extends RelativeLayout {
   private String mTitle;
   private String mValue;
   private Drawable mImage;

   public SimpleDataView(Context context) {
      super(context);
   }

   public SimpleDataView(Context context, AttributeSet attrs) {
      super(context, attrs);

      //get the attributes specified in attrs.xml using the name we included
      TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleDataView,
            0, 0);

      try {
         //get the text and colors specified using the names in attrs.xml
         mTitle = a.getString(R.styleable.SimpleDataView_mytitle);
         mValue = a.getString(R.styleable.SimpleDataView_myvalue);
         mImage = a.getDrawable(R.styleable.SimpleDataView_image);
      } finally {
         a.recycle();
      }

      LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View view = layoutInflater.inflate(R.layout.simpledataview, this);

      setTitle(mTitle);
      setValue(mValue);
      if (mImage != null) {
         setImage(mImage);
      }
   }

   private void setImage(Drawable mImage) {
      this.mImage = mImage;

      ((ImageView)this.findViewById(R.id.view_image)).setImageDrawable(mImage);
   }

   public SimpleDataView(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
   }

   public void setTitle(String title) {
      this.mTitle = title;

      ((TextView)this.findViewById(R.id.view_title)).setText(mTitle);
   }

   public void setValue(String value) {
      this.mValue = value;

      ((TextView)this.findViewById(R.id.view_value)).setText(mValue);
   }

   public String getTitle() {
      return this.mTitle;
   }

   public String getValue() {
      return this.mValue;
   }
}
