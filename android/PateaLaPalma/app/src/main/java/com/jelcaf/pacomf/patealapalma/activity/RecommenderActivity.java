package com.jelcaf.pacomf.patealapalma.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.gc.materialdesign.views.ButtonRectangle;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.adapter.RecomenderQuestionsPagerAdapter;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.binding.dao.SenderosBusquedaGrupo;
import com.jelcaf.pacomf.patealapalma.recommender.RecommenderBaseQuestion;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.enums.SnackbarType;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class RecommenderActivity extends ActionBarActivity {

   private ViewPager mViewPager;
   private CircleIndicator mIndicator;
   private RecomenderQuestionsPagerAdapter mPagerAdapter;

   private ButtonRectangle mPreviousButton;
   private ButtonRectangle mNextButton;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recommender);

      // Toolbar Support
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      ActiveAndroid.initialize(getApplicationContext());

      // Show the Up button in the action bar.
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

      setTitle(R.string.recommender_title);

      initUIVars();

      setUIVars();
   }

   private void initUIVars() {
      mViewPager = (ViewPager) findViewById(R.id.viewpager_default);
      mIndicator = (CircleIndicator) findViewById(R.id.indicator_default);

      mPreviousButton = (ButtonRectangle) findViewById(R.id.previousButton);
      mNextButton = (ButtonRectangle) findViewById(R.id.nextButton);
   }

   public void refreshTextButtons() {
      if (mViewPager.getCurrentItem() < mPagerAdapter.getCount()) {
         mNextButton.setText(getResources().getString(R.string.next_text));
         return;
      }
      mNextButton.setText(getResources().getString(R.string.done_text));
   }

   private void setUIVars() {
      mPagerAdapter = new RecomenderQuestionsPagerAdapter(getSupportFragmentManager(), this);
      mViewPager.setAdapter(mPagerAdapter);
      mIndicator.setViewPager(mViewPager);
      mIndicator.setOnPageChangeListener(new MyChangeListener());

      mPreviousButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            mViewPager.setCurrentItem(getItem(-1), true);
         }
      });

      mNextButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (mNextButton.getText().equals(getResources().getString(R.string.done_text))) {
               validateMandatoryQuestions();
            }
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

      switch (item.getItemId()) {
         case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
      }

      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }

      return super.onOptionsItemSelected(item);
   }

   public class MyChangeListener extends ViewPager.SimpleOnPageChangeListener {
      @Override
      public void onPageSelected(int position) {
         if (position < mPagerAdapter.getCount() - 1) {
            mNextButton.setText(getResources().getString(R.string.next_text));
            return;
         }
         mNextButton.setText(getResources().getString(R.string.done_text));
      }
   }

   public String validateForm () {
      List<RecommenderBaseQuestion> mForm = mPagerAdapter.form.getQuestions();
      for (RecommenderBaseQuestion question :  mForm) {
         if (question.isMandatory() && question.getResponse() == null) {
            return getString(R.string.fail_question) + question.getResumeQuestion();
         }
      }
      return "";
   }

   private void validateMandatoryQuestions() {
      String result = validateForm();
      if (!result.isEmpty()) {
         Snackbar.with(getApplicationContext().getApplicationContext()) // context
               .text(result) // text to display
               .actionLabel(getString(R.string.close)) // action button label
               .actionColor(Color.parseColor("#FF8A80"))
               .type(SnackbarType.MULTI_LINE)
               .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
               .show(this); // activity where it is displayed
      } else {
         // TODO: El formulario es válido debemos filtrar los senderos y establecerlos en una
         // preferencia
         filtrarSenderos();
      }
   }

   private void filtrarSenderos() {
      List<Sendero> listaSenderos = getAllSenderos();
      int total = listaSenderos.size();

      // Para cada una de las preguntas le aplicamos el filtro
      for (RecommenderBaseQuestion question : mPagerAdapter.form.getQuestions()) {
         List<Sendero> filterList = new ArrayList<Sendero>();
         for (Sendero sendero: listaSenderos) {
            if (question.checkSendero(sendero)) {
               filterList.add(sendero);
            }
         }
         listaSenderos = filterList;
      }

      if (listaSenderos.size() == 0) {

         Snackbar.with(getApplicationContext().getApplicationContext()) // context
               .text("No se han encontrado senderos que cumplan las espectativas")
               .actionLabel(getString(R.string.close)) // action button label
               .actionColor(Color.parseColor("#FF8A80"))
               .type(SnackbarType.MULTI_LINE)
               .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
               .show(this); // activity where it is displayed
         return;

      } else {
         Toast.makeText(getApplicationContext(), listaSenderos.size() + " encontrados",
               Toast.LENGTH_LONG).show();
//         Snackbar.with(getApplicationContext().getApplicationContext()) // context
//               .text(listaSenderos.size() + " de " + total + " senderos han pasado el filtro" )
//               .actionLabel("Close") // action button label
//               .actionColor(Color.parseColor("#FF8A80"))
//               .type(SnackbarType.MULTI_LINE)
//               .duration(Snackbar.SnackbarDuration.LENGTH_LONG)
//               .show(this); // activity where it is displayed
      }
      // Guardamos los últimos senderos buscados en la BBDD
      SenderosBusquedaGrupo ultimaBusqueda = new Select().from(SenderosBusquedaGrupo.class).executeSingle();
      if (ultimaBusqueda == null) {
         ultimaBusqueda = new SenderosBusquedaGrupo();
      }

      List<String> listaIds = new ArrayList<String>();
      StringBuilder resultSearchBuilder = new StringBuilder();
      for (Sendero sendero : listaSenderos) {
         listaIds.add(sendero.getServerId());
      }

      ultimaBusqueda.resultSearch = TextUtils.join(",", listaIds);
      ultimaBusqueda.save();

      NavUtils.navigateUpFromSameTask(this);
   }

   private List<Sendero> getAllSenderos() {
      return new Select()
            .from(Sendero.class)
            .execute();
   }

}
