package com.jelcaf.pacomf.patealapalma.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.facebook.widget.ProfilePictureView;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Paco on 01/04/2015.
 */
public class CommentAdapter extends BaseAdapter {

    private List<Comment> comments;
    private Activity activity;
    SimpleDateFormat dt1;


    public CommentAdapter(Activity activity, List<Comment> comments) {
        super();
        this.activity = activity;
        this.comments = comments;
        dt1 = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
    }

    /**
     * Senderos Data ViewHolder
     */
    static class ViewHolder {
        ProfilePictureView profileImage;
        TextView nameOwner;
        TextView comment;
        TextView date;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.comments_list_item, null);
            holder = new ViewHolder();
            holder.nameOwner = (TextView) convertView.findViewById(R.id.name_user);
            holder.comment = (TextView) convertView.findViewById(R.id.comment);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.profileImage = (ProfilePictureView) convertView.findViewById(R.id.profilePicture);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        try {
            Comment comment = comments.get(position);
            holder.nameOwner.setText(comment.getNameowner());
            holder.date.setText(dt1.format(comment.getDate()));
            holder.comment.setText(comment.getDescription());
            holder.profileImage.setProfileId(comment.getOwner());
        } catch (Exception e){}

        return convertView;
    }

}
