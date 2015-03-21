package com.jelcaf.pacomf.patealapalma.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.jelcaf.pacomf.patealapalma.activity.SenderoDetailWithImageActivity;

import org.osmdroid.views.MapView;

/**
 * Created by Paco on 21/03/2015.
 * Personalizada para poder hacer scroll e interactuar en el mapa sin que coja la interaccion del padre que sera un ScrollView
 */
public class CustomMapView extends MapView {

    protected ObservableScrollView scrollViewParent;

    public CustomMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setArguments (ObservableScrollView parentScroll) {
        scrollViewParent = parentScroll;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Disallow ScrollView to intercept touch events.
                scrollViewParent.requestDisallowInterceptTouchEvent(true);
                break;

            case MotionEvent.ACTION_UP:
                // Allow ScrollView to intercept touch events.
                scrollViewParent.requestDisallowInterceptTouchEvent(false);
                break;
        }

        // Handle MapView's touch events.
        super.dispatchTouchEvent(ev);
        return true;
    }

}
