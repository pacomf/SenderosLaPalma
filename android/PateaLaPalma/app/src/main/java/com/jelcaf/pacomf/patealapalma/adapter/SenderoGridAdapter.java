package com.jelcaf.pacomf.patealapalma.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.SenderosConstants;
import com.jelcaf.pacomf.patealapalma.binding.dao.Photo;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
import java.util.Random;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class SenderoGridAdapter extends BaseAdapter {
   private static final String TAG = SenderoGridAdapter.class.getCanonicalName();

   private LayoutInflater inflater = null;
   private List<Sendero> senderos;
   private Activity activity;

   private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
   private final Random mRandom;


   public SenderoGridAdapter(Activity activity, List<Sendero> senderos, Context context) {
      super();
      this.activity = activity;
      this.senderos = senderos;
      this.inflater = LayoutInflater.from(activity);
      this.mRandom = new Random();

      DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
            .cacheOnDisk(true)
            .build();

      ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
            .defaultDisplayImageOptions(defaultOptions)
            .build();

      ImageLoader.getInstance().init(config);
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
         convertView = inflater.inflate(R.layout.fragment_sendero_list_detail_grid, null);
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
      if (sendero.getRating() != null && sendero.getRating() != 0) {
         holder.ratio.setText(sendero.getRating().toString());
         holder.ratio.setVisibility(View.VISIBLE);
      } else {
         holder.ratio.setVisibility(View.INVISIBLE);
      }

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
      holder.imageName.setBackgroundColor(color);
      holder.difficulty.setBackgroundColor(color);

      List<Photo> photos = sendero.photos();
      if (photos.size() > 0) {
         ImageLoader.getInstance().displayImage(photos.get(0).getUrl(), holder.imageName);
      }

      TextDrawable drawable = TextDrawable.builder().buildRound(sendero.getRegularName()
            .substring(0, 2), color);

      return convertView;
   }

   private double getPositionRatio(final int position) {
      double ratio = sPositionHeightRatios.get(position, 0.0);
      // if not yet done generate and stash the columns height
      // in our real world scenario this will be determined by
      // some match based on the known height and width of the image
      // and maybe a helpful way to get the column height!
      if (ratio == 0) {
         ratio = getRandomHeightRatio();
         sPositionHeightRatios.append(position, ratio);
      }
      return ratio;
   }

   private double getRandomHeightRatio() {
      return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
   }
}
