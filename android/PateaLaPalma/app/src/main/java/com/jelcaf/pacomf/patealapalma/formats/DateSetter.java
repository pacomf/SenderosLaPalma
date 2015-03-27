package com.jelcaf.pacomf.patealapalma.formats;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * @author Jorge Carballo
 *         27/03/15
 */
public class DateSetter implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

   private EditText mEditText;
   private Calendar mCalendar;
   private Context mContext;

   public DateSetter(EditText editText, Context ctx) {
      this.mEditText = editText;
      this.mEditText.setOnClickListener(this);
      this.mContext = ctx;
      mCalendar = Calendar.getInstance();
   }

   @Override
   public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
      mCalendar.set(Calendar.YEAR, year);
      mCalendar.set(Calendar.MONTH, monthOfYear);
      mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

      mEditText.setText(DateFormats.getDateFormatted(mCalendar.getTime()));
   }

   @Override
   public void onClick(View v) {
      new DatePickerDialog(mContext, this, mCalendar
            .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
            mCalendar.get(Calendar.DAY_OF_MONTH)).show();
   }

}
