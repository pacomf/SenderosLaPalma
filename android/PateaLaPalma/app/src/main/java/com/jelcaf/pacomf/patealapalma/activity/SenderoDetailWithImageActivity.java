package com.jelcaf.pacomf.patealapalma.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.fragment.SenderoDetailFragment;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * @author Jorge Carballo
 *         18/03/15
 */
public class SenderoDetailWithImageActivity extends BaseActivity implements ObservableScrollViewCallbacks {

   private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
   private static final boolean TOOLBAR_IS_STICKY = true;

   private View mToolbar;
   private View mImageView;
   private View mOverlayView;
   private ObservableScrollView mScrollView;
   private TextView mTitleView;
   private View mFab;
   private int mActionBarSize;
   private int mFlexibleSpaceShowFabOffset;
   private int mFlexibleSpaceImageHeight;
   private int mFabMargin;
   private int mToolbarColor;
   private boolean mFabIsShown;

   private Sendero mSendero;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_sendero_detail_with_image);

      // ActiveAndroid
      ActiveAndroid.initialize(this);

      Bundle b = getIntent().getExtras();
      if (b.containsKey(SenderoDetailFragment.ARG_ITEM_ID)) {
         // Load the race content specified by the arguments.
         // In a real-world scenario, use a Loader
         // to load content from a content provider.
         mSendero = Sendero.load(Sendero.class, b.getLong(SenderoDetailFragment.ARG_ITEM_ID));
      }

      setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

      mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.flexible_space_image_height);
      mFlexibleSpaceShowFabOffset = getResources().getDimensionPixelSize(R.dimen.flexible_space_show_fab_offset);
      mActionBarSize = getActionBarSize();
      mToolbarColor = getResources().getColor(R.color.primary);

      // Show the Up button in the action bar.
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      mToolbar = findViewById(R.id.toolbar);
      if (!TOOLBAR_IS_STICKY) {
         mToolbar.setBackgroundColor(Color.TRANSPARENT);
      }
      mImageView = findViewById(R.id.image);
      mOverlayView = findViewById(R.id.overlay);
      mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
      mScrollView.setScrollViewCallbacks(this);
      mTitleView = (TextView) findViewById(R.id.title);
//      mTitleView.setText(getTitle());
      mTitleView.setText(mSendero.getName());
      setTitle(null);
      mFab = findViewById(R.id.fab);
      mFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(SenderoDetailWithImageActivity.this, "FAB is clicked", Toast.LENGTH_SHORT).show();
         }
      });
      mFabMargin = getResources().getDimensionPixelSize(R.dimen.margin_standard);
      ViewHelper.setScaleX(mFab, 0);
      ViewHelper.setScaleY(mFab, 0);

      ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
         @Override
         public void run() {
            mScrollView.scrollTo(0, mFlexibleSpaceImageHeight - mActionBarSize);

            // If you'd like to start from scrollY == 0, don't write like this:
            //mScrollView.scrollTo(0, 0);
            // The initial scrollY is 0, so it won't invoke onScrollChanged().
            // To do this, use the following:
            //onScrollChanged(0, false, false);

            // You can also achieve it with the following codes.
            // This causes scroll change from 1 to 0.
            mScrollView.scrollTo(0, 1);
            mScrollView.scrollTo(0, 0);
         }
      });

      if (savedInstanceState == null) {
         // Create the detail fragment and add it to the activity
         // using a fragment transaction.
         Bundle arguments = new Bundle();
         arguments.putLong(SenderoDetailFragment.ARG_ITEM_ID,
               getIntent().getLongExtra(SenderoDetailFragment.ARG_ITEM_ID, -1));
         SenderoDetailFragment fragment = new SenderoDetailFragment();
         fragment.setArguments(arguments);
         getSupportFragmentManager().beginTransaction()
               .add(R.id.sendero_detail_container, fragment)
               .commit();
      }
   }

   @Override
   public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
      // Translate overlay and image
      float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
      int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
      ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
      ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

      // Change alpha of overlay
      ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

      // Scale title text
      float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
      ViewHelper.setPivotX(mTitleView, 0);
      ViewHelper.setPivotY(mTitleView, 0);
      ViewHelper.setScaleX(mTitleView, scale);
      ViewHelper.setScaleY(mTitleView, scale);

      // Translate title text
      int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale);
      int titleTranslationY = maxTitleTranslationY - scrollY;
      if (TOOLBAR_IS_STICKY) {
         titleTranslationY = Math.max(0, titleTranslationY);
      }
      ViewHelper.setTranslationY(mTitleView, titleTranslationY);
      ViewHelper.setTranslationX(mTitleView, 70);

      // Translate FAB
      int maxFabTranslationY = mFlexibleSpaceImageHeight - mFab.getHeight() / 2;
      float fabTranslationY = ScrollUtils.getFloat(
            -scrollY + mFlexibleSpaceImageHeight - mFab.getHeight() / 2,
            mActionBarSize - mFab.getHeight() / 2,
            maxFabTranslationY);
      if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
         // On pre-honeycomb, ViewHelper.setTranslationX/Y does not set margin,
         // which causes FAB's OnClickListener not working.
         FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFab.getLayoutParams();
         lp.leftMargin = mOverlayView.getWidth() - mFabMargin - mFab.getWidth();
         lp.topMargin = (int) fabTranslationY;
         mFab.requestLayout();
      } else {
         ViewHelper.setTranslationX(mFab, mOverlayView.getWidth() - mFabMargin - mFab.getWidth());
         ViewHelper.setTranslationY(mFab, fabTranslationY);
      }

      // Show/hide FAB
      if (fabTranslationY < mFlexibleSpaceShowFabOffset) {
         hideFab();
      } else {
         showFab();
      }

      if (TOOLBAR_IS_STICKY) {
         // Change alpha of toolbar background
         if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
            mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
         } else {
            mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
         }
      } else {
         // Translate Toolbar
         if (scrollY < mFlexibleSpaceImageHeight) {
            ViewHelper.setTranslationY(mToolbar, 0);
         } else {
            ViewHelper.setTranslationY(mToolbar, -scrollY);
         }
      }
   }

   @Override
   public void onDownMotionEvent() {
   }

   @Override
   public void onUpOrCancelMotionEvent(ScrollState scrollState) {
   }

   private void showFab() {
      if (!mFabIsShown) {
         mFab.setVisibility(View.VISIBLE);
         ViewPropertyAnimator.animate(mFab).cancel();
         ViewPropertyAnimator.animate(mFab).scaleX(1).scaleY(1).setDuration(200).start();
         mFabIsShown = true;
      }
   }

   private void hideFab() {
      if (mFabIsShown) {
         ViewPropertyAnimator.animate(mFab).cancel();
         ViewPropertyAnimator.animate(mFab).scaleX(0).scaleY(0).setDuration(200).start();

         ViewPropertyAnimator.animate(mFab).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
               if (!mFabIsShown) {
                  mFab.setVisibility(View.GONE);
               }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
         });
         mFabIsShown = false;
      }
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();

      switch (id) {
         case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
//            navigateUpTo(new Intent(this, SenderosSwipeActivity.class));
            return true;
      }
      return super.onOptionsItemSelected(item);
   }
}