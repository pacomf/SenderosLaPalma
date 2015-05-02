package com.jelcaf.pacomf.patealapalma.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.colors.RandomColors;

import java.util.List;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class SenderoAdapter extends BaseAdapter {
   private static final String TAG = SenderoAdapter.class.getCanonicalName();

   private LayoutInflater inflater = null;
   private List<Sendero> senderos;
   private Activity activity;


   public SenderoAdapter(Activity activity, List<Sendero> senderos) {
      super();
      this.activity = activity;
      this.senderos = senderos;
      this.inflater = LayoutInflater.from(activity);
   }

   /**
    * Senderos Data ViewHolder
    */
   static class ViewHolder {
      ImageView imageName;
      TextView name;
      TextView distance;
      TextView time;
      TextView difficulty;
      TextView ratio;
   }

   @Override
   public int getCount() {
      return senderos.size();
   }

   @Override
   public Object getItem(int position) {
      return senderos.get(position);
   }

   @Override
   public long getItemId(int position) {
      return position;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder holder;

      if (convertView == null) {
         convertView = inflater.inflate(R.layout.fragment_sendero_list_detail, null);
         holder = new ViewHolder();
         holder.name = (TextView) convertView.findViewById(R.id.my_sendero_name);
         holder.imageName = (ImageView) convertView.findViewById(R.id.sendero_image);
         holder.distance = (TextView) convertView.findViewById(R.id.sendero_distance);
         holder.time = (TextView) convertView.findViewById(R.id.sendero_time);
         holder.difficulty = (TextView) convertView.findViewById(R.id.sendero_difficulty);
         holder.ratio = (TextView) convertView.findViewById(R.id.sendero_rating);

         convertView.setTag(holder);
      } else {
         holder = (ViewHolder)convertView.getTag();
      }

      Sendero sendero = senderos.get(position);

      holder.name.setText(sendero.getName());
      holder.distance.setText(sendero.getLength().toString() + " km");
      holder.time.setText(SenderosConstants.timeConversion(sendero.getLength() *
            SenderosConstants
            .SECONDS_IN_KM_MEDIUM));
      holder.difficulty.setText(sendero.getRegularName());
      holder.ratio.setText(sendero.getRating() == 0 ? activity.getString(R.string.noRatio) : sendero.getRating().toString
            ());

      int color;
      if (sendero.getDifficulty().equals("Alta")) {
         color = this.activity.getResources().getColor(R.color.sendero_alta);
      } else if (sendero.getDifficulty().equals("Media")) {
         color = this.activity.getResources().getColor(R.color.sendero_media);
      } else if (sendero.getDifficulty().equals("Baja")){
         color = this.activity.getResources().getColor(R.color.sendero_baja);
      } else {
         color = this.activity.getResources().getColor(R.color.sendero_extrema);
      }

      TextDrawable drawable = TextDrawable.builder().buildRound(sendero.getRegularName()
            .substring(0, 2), color);

      holder.imageName.setImageDrawable(drawable);
      return convertView;
   }


}
