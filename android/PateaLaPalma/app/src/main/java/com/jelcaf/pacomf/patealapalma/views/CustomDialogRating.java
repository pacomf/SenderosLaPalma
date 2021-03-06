package com.jelcaf.pacomf.patealapalma.views;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;
import com.jelcaf.pacomf.patealapalma.network.Request;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * Created by Paco on 02/04/2015.
 */
public class CustomDialogRating {

    public static DialogPlus popup;
    public static SenderoDetailFragment fragment;
    public static int rating;

    public static void showDialog (final Activity activity, final SenderoDetailFragment fragment, final String idSendero, final int arating) {

        CustomDialogRating.fragment = fragment;
        CustomDialogRating.rating=0;
        Holder holder;
        if (arating>0) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            View vi = inflater.inflate(R.layout.dialog_rating_content, null);
            ImageView l1 = (ImageView) vi.findViewById(R.id.leaf1);
            ImageView l2 = (ImageView) vi.findViewById(R.id.leaf2);
            ImageView l3 = (ImageView) vi.findViewById(R.id.leaf3);
            ImageView l4 = (ImageView) vi.findViewById(R.id.leaf4);
            ImageView l5 = (ImageView) vi.findViewById(R.id.leaf5);
            Utilities.setRating(activity, arating, l1, l2, l3, l4, l5);
            holder = new ViewHolder(vi);

        } else {
            holder = new ViewHolder(R.layout.dialog_rating_content);
        }

        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(DialogPlus dialog, View view) {
                ImageView leaf1 = (ImageView) ((View)view.getParent()).findViewById(R.id.leaf1);
                ImageView leaf2 = (ImageView) ((View)view.getParent()).findViewById(R.id.leaf2);
                ImageView leaf3 = (ImageView) ((View)view.getParent()).findViewById(R.id.leaf3);
                ImageView leaf4 = (ImageView) ((View)view.getParent()).findViewById(R.id.leaf4);
                ImageView leaf5 = (ImageView) ((View)view.getParent()).findViewById(R.id.leaf5);

                switch (view.getId()) {
                    case R.id.leaf1:
                        rating=1;
                        break;
                    case R.id.leaf2:
                        rating=2;
                        break;
                    case R.id.leaf3:
                        rating=3;
                        break;
                    case R.id.leaf4:
                        rating=4;
                        break;
                    case R.id.leaf5:
                        rating=5;
                        break;
                    case R.id.footer_confirm_button:
                        if (LoginMethods.checkLogin(activity)) {
                            if (arating != rating){
                                Request.ratingSenderoPOST(activity, popup, idSendero, LoginMethods.getIdFacebook(activity), rating, com.jelcaf.pacomf.patealapalma.network.Utilities.getProgressDialog(activity, activity.getString(R.string.upload_rating), activity.getString(R.string.procesando)));
                            }
                        }
                        return;
                    case R.id.footer_cancel_button:
                        dialog.dismiss();
                        return;
                    default:
                        return;
                }

                Utilities.setRating(activity, rating, leaf1, leaf2, leaf3, leaf4, leaf5);

            }
        };

        popup = new DialogPlus.Builder(activity)
                .setContentHolder(holder)
                .setHeader(R.layout.dialog_rating_header)
                .setFooter(R.layout.dialog_rating_footer)
                .setCancelable(true)
                .setGravity(DialogPlus.Gravity.CENTER)
                .setOnClickListener(clickListener)
                .create();

        popup.show();

    }

    public static void dismiss(){
        if (popup.isShowing()) {
            popup.dismiss();
            fragment.uploadFragment();
        }
    }
}
