package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.widget.ImageView;

import com.jelcaf.pacomf.patealapalma.R;

/**
 * Created by Paco on 02/04/2015.
 */
public class Utilities {

    public static void setRating (Context ctx, int rating, ImageView leaf1, ImageView leaf2, ImageView leaf3, ImageView leaf4, ImageView leaf5){
        switch (rating){
            case 1:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                break;
            case 2:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                break;
            case 3:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                break;
            case 4:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                break;
            case 5:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_on));
                break;
            default:
                leaf1.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf2.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf3.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf4.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                leaf5.setImageDrawable(ctx.getResources().getDrawable(R.drawable.leaf_off));
                break;
        }
    }
}
