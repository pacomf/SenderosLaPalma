package com.jelcaf.pacomf.patealapalma.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.RecomenderQuestionsPagerAdapter;

import me.relex.circleindicator.CircleIndicator;

public class RecommenderActivity extends ActionBarActivity {

   private ViewPager mViewPager;
   private CircleIndicator mIndicator;
   private RecomenderQuestionsPagerAdapter mPagerAdapter;

   private Button mPreviousButton;
   private Button mNextButton;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recommender);

      // Toolbar Support
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      // Show the Up button in the action bar.
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      initUIVars();

      setUIVars();

   }

   private void initUIVars() {
      mViewPager = (ViewPager) findViewById(R.id.viewpager_default);
      mIndicator = (CircleIndicator) findViewById(R.id.indicator_default);

      mPreviousButton = (Button) findViewById(R.id.previousButton);
      mNextButton = (Button) findViewById(R.id.nextButton);
   }


   private void setUIVars() {
      mPagerAdapter = new RecomenderQuestionsPagerAdapter(getSupportFragmentManager());
      mViewPager.setAdapter(mPagerAdapter);
      mIndicator.setViewPager(mViewPager);

      mPreviousButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            mViewPager.setCurrentItem(getItem(-1), true);
         }
      });

      mNextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            mViewPager.setCurrentItem(getItem(+1), true);
         }
      });
   }

   private int getItem(int i) {
      return mViewPager.getCurrentItem() + i;
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
//      getMenuInflater().inflate(R.menu.menu_recommender, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }
}
