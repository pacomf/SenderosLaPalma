package com.jelcaf.pacomf.patealapalma.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;

import java.util.List;

/**
 * @author Jorge Carballo
 *         26/02/15
 */
public class SenderoAdapter extends BaseAdapter {
   private static final String TAG = SenderoAdapter.class.getCanonicalName();

   private LayoutInflater inflater = null;
   private List<Sendero> senderos;

   public SenderoAdapter(Context context, List<Sendero> senderos) {
      super();

      this.senderos = senderos;
      this.inflater = LayoutInflater.from(context);
   }

   /**
    * Senderos Data ViewHolder
    */
   static class ViewHolder {
      TextView name;
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

         convertView.setTag(holder);
      } else {
         holder = (ViewHolder)convertView.getTag();
      }

      Sendero sendero = senderos.get(position);

      holder.name.setText(sendero.getName());

      return convertView;
   }
}
