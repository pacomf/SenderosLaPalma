package com.jelcaf.pacomf.patealapalma.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.activeandroid.ActiveAndroid;
import com.jelcaf.pacomf.patealapalma.R;
import com.jelcaf.pacomf.patealapalma.binding.dao.Comment;
import com.jelcaf.pacomf.patealapalma.binding.dao.Sendero;
import com.jelcaf.pacomf.patealapalma.binding.utilities.LoadData;
import com.jelcaf.pacomf.patealapalma.fragment.LoginFragment;
import com.jelcaf.pacomf.patealapalma.login.LoginMethods;

public class LoginActivity extends FacebookBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (LoginMethods.getIdFacebook(this) != null){
            Intent intent = new Intent(this, SenderosSwipeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {

            setContentView(R.layout.activity_login);

            setFragmentsFromBundle(savedInstanceState);
        }

        ActiveAndroid.initialize(this);

        //if (Sendero.isDBEmpty())
        //   LoadData.loadLocalData(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void setFragmentsFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

}
